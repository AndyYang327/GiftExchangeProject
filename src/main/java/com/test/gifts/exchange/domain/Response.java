package com.test.gifts.exchange.domain;

public class Response {
   
	public Status status;
    private String description;
    
    public Response(Status failure, String string) {
	}

    public enum Status {
        SUCCESS, FAILURE
    }
}
