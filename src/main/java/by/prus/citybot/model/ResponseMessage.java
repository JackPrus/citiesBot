package by.prus.citybot.model;

public enum ResponseMessage {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    NO_RECORD_FOUND("Record is not found"),

    DELETE_OPERATION("DELETE OPERATION"),

    STATUS_SUCCESS("Success result"),
    STATUS_ERROR("ERROR");

    private String responseMessage;

    ResponseMessage(String errorMessage) {
        this.responseMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage the errorMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
