package io.botcrafting.botcraft.core.model;

import java.util.List;

import com.google.api.client.util.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Meeting {
	private DateTime start;
	private DateTime end;
	private String summary;
	private String description;
	private String location;
	private String meetingUrl;
	private boolean presential;
	private List<String> emailsToNotify;
}
