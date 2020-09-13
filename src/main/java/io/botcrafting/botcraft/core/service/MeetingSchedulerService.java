package io.botcrafting.botcraft.core.service;

import java.util.List;

import io.botcrafting.botcraft.core.model.Meeting;

public interface MeetingSchedulerService {
	public boolean scheduleMeeting(Meeting meeting);
	public List<Meeting> getAllScheduledMeetings();
	public boolean sendReminder(Meeting meeting);
	public boolean checkForMeetingsDaily();
}
