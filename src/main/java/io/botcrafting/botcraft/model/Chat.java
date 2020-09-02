package io.botcrafting.botcraft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chat {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String title;
    private String type;
    private Boolean allMembersAreAdministrators;
    
    public Boolean getAllMembersAreAdministrators() {
        return allMembersAreAdministrators;
    }

    public void setAllMembersAreAdministrators(Boolean allMembersAreAdministrators) {
        this.allMembersAreAdministrators = allMembersAreAdministrators;
    }
}
