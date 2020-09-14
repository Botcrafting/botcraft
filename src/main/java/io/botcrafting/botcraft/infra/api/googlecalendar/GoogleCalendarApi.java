package io.botcrafting.botcraft.infra.api.googlecalendar;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import io.botcrafting.botcraft.core.model.Meeting;
import io.botcrafting.botcraft.core.service.MeetingSchedulerService;
import lombok.extern.java.Log;

@Log
public class GoogleCalendarApi implements MeetingSchedulerService{
	
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	@Override
	public boolean scheduleMeeting(Meeting meeting) {
		Calendar service = getService();
		if(meeting.isPresential()) {
			return schedulePresentialMeeting(meeting, service);
		}
		return scheduleRemoteMeeting(meeting, service);
		
		
	}
	
	private boolean schedulePresentialMeeting(Meeting meeting, Calendar service) {
		try {
			Event event = new Event();
			event.setSummary(meeting.getSummary());
			event.setLocation(meeting.getLocation());
			event.setDescription(meeting.getDescription());
			EventDateTime start = getEventDateTime(meeting.getStart());
			EventDateTime end = getEventDateTime(meeting.getEnd());
			event.setStart(start);
			event.setEnd(end);
			EventAttendee[] attendees = getAttendees();
			event.setAttendees(Arrays.asList(attendees));
			event = service.events().insert("primary", event).setSendNotifications(true).execute();
			return true;
		}catch (IOException io) {
			log.info("Failed to create presential meeting");
			log.info(io.getMessage());
			io.printStackTrace();
		}
		return false;
		
	}
	
	private boolean scheduleRemoteMeeting(Meeting meeting, Calendar service) {
		try {
			Event event = new Event();
			event.setSummary(meeting.getSummary());
			event.setDescription(meeting.getDescription());
			EventDateTime start = getEventDateTime(meeting.getStart());
			EventDateTime end = getEventDateTime(meeting.getEnd());
			event.setStart(start);
			event.setEnd(end);
			EventAttendee[] attendees = getAttendees();
			event.setAttendees(Arrays.asList(attendees));
			event = service.events().insert("primary", event).setSendNotifications(true).execute();
			meeting.setMeetingUrl(event.getHangoutLink());
			return true;
		}catch (IOException io) {
			log.info("Failed to create remote meeting");
			log.info(io.getMessage());
			io.printStackTrace();
		}
		return false;
	}
	
	private EventAttendee[] getAttendees() {
		EventAttendee[] attendees = new EventAttendee[] {
			    new EventAttendee().setEmail("mullerthiago8@gmail.com"),
			    //new EventAttendee().setEmail("pat.ag85@gmail.com"),
			    //new EventAttendee().setEmail("rafaelcarnelvis@gmail.com"),
			    //new EventAttendee().setEmail("carolinersan@gmail.com"),
			    //new EventAttendee().setEmail("bezerrarho@gmail.com"),
			    //new EventAttendee().setEmail("nita.nishi@gmail.com"),
			    //new EventAttendee().setEmail("allanpab@gmail.com"),
		};
		return attendees;
	}
	
	private EventDateTime getEventDateTime(DateTime dateTime) {
		EventDateTime eventDateTime = new EventDateTime()
				.setDateTime(dateTime)
				.setTimeZone("America/Sao_Paulo");
		return eventDateTime;
	}
	
	@Override
	public List<Meeting> getAllScheduledMeetings() {
		Calendar service = getService();
		try {
			Events events = service.events().list("primary")
					.setOrderBy("startTime")
					.setSingleEvents(true)
					.execute();
			List<Event>  items = events.getItems();
			List<Meeting> meetings = fromEventListToCoreMeetingList(items);
			return meetings;
		}catch (IOException io) {
			log.info("Failed to fetch all scheduled meetings");
			log.info(io.getMessage());
			io.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean sendReminder(Meeting meeting) {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public boolean checkForMeetingsDaily() {
		// TODO Auto-generated method stub
		return false;
		
	}
	
	private List<Meeting> fromEventListToCoreMeetingList(List<Event> events){
		List<Meeting> meetings = new ArrayList<>();
		for(Event event : events) {
			Meeting meeting = toCoreMeeting(event);
			meetings.add(meeting);
		}
		return meetings;
	}
	
	private Meeting toCoreMeeting(Event event) {
		Meeting meeting = new Meeting();
		meeting.setDescription(event.getDescription().isEmpty() ? "" : event.getDescription());
		if(!event.getAttendees().isEmpty()) {
			setEmaislToNotifyOnGivenMeeting(meeting, event.getAttendees());
		}
		meeting.setLocation(event.getLocation().isEmpty() ? "" : event.getLocation());
		meeting.setMeetingUrl(event.getHangoutLink().isEmpty() ? "" : event.getHangoutLink());
		meeting.setPresential(event.getHangoutLink().isEmpty() ? false : true);
		meeting.setSummary(event.getSummary().isEmpty() ? "" : event.getSummary());
		meeting.setStart(event.getStart().getDate() != null ? event.getStart().getDate() : null);
		meeting.setEnd(event.getEnd().getDate() != null ? event.getEnd().getDate() : null);
		return meeting;
	}
	
	private void setEmaislToNotifyOnGivenMeeting(Meeting meeting, List<EventAttendee> attendees) {
		for(EventAttendee attendee : attendees) {
			if(!attendee.getEmail().isEmpty()) {
				meeting.getEmailsToNotify().add(attendee.getEmail());
			}
		}
	}
	
	private NetHttpTransport getHttpTransport() {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		}catch (IOException io) {
			log.info("Coundn't get HTTP Transport for google calendar api");
			log.info(io.getMessage());
			io.printStackTrace();
		}catch (GeneralSecurityException securityEx) {
			log.info("Caught security exception with google calendar api");
			log.info(securityEx.getMessage());
			securityEx.printStackTrace();
		}
		return null;
	}
	
	private GoogleCredential getCredential() {
		try {
			GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("/home/credentials.json"))
					.createScoped(Collections.singleton(CalendarScopes.CALENDAR_EVENTS));
			return credential;
		}catch (IOException io) {
			log.info("Coundn't get credentials for google calendar api");
			log.info(io.getMessage());
			io.printStackTrace();
		}
		return null;
	}
	
	private Calendar getService() {
		GoogleCredential credential = getCredential();
		NetHttpTransport httpTransport = getHttpTransport();
		Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName("Botcraft")
				.build();
		return service;
	}
	
	
		

}
