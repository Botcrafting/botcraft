package io.botcrafting.botcraft.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private User user;
    private long chatId;
    private int date;
    private String text;
    
    
    public String getFullName() {
    	return this.getUser().getFirstName() + " " + this.getUser().getLastName();
    }
    
    public String getLoweredText() {
    	return this.getText().toLowerCase();
    }
}
