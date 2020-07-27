package com.oocl.cultivation.exception;

public enum ExceptionMessage {
    NOT_POSITION("Not enough position."),
    HAS_USED("Unrecognized parking ticket."),
    NULL_TICKET("Please provide your parking ticket.");

    private String errorMsg;
    private ExceptionMessage(String errorMsg){
        this.errorMsg = errorMsg;
    }

    String getErrorMsg(){
        return this.errorMsg;
    }
}
