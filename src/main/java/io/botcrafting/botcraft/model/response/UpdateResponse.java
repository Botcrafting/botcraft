package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateResponse {
    private int updateId;
    private @JsonProperty("message") MessageResponse messageResponse;

    public UpdateResponse(){}

    public static Builder newBuilder(){
        return new Builder();
    }

    public String getMessageText(){
        if(this.messageResponse != null){
            return this.messageResponse.getText();
        } else{
            return "";
        }
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public MessageResponse getMessage() {
        return messageResponse;
    }

    public void setMessage(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public static final class Builder{
        private int updateId;
        private MessageResponse messageResponse;

        public Builder withUpdateId(int updateId){
            this.updateId = updateId;
            return this;
        }

        public Builder withMessage(MessageResponse messageResponse){
            this.messageResponse = messageResponse;
            return this;
        }

        public UpdateResponse build(){
            UpdateResponse telegramUpdateResponse = new UpdateResponse();
            telegramUpdateResponse.updateId = this.updateId;
            telegramUpdateResponse.messageResponse = this.messageResponse;
            return telegramUpdateResponse;
        }
    }

}
