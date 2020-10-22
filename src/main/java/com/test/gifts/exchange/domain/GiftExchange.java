package com.test.gifts.exchange.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftExchange {

	@JsonProperty("member_id")
    private String memberId;

    @JsonProperty("recipient_member_id")
    private String recipientMemberId;
    
    public GiftExchange(String key, String recipientAssigned) {
    	memberId = key;
    	recipientMemberId = recipientAssigned;
    }
    
    public String getMemberId() {
    	return memberId;
    }
    public String getRecipientMemberId() {
    	return recipientMemberId;
    }

}
