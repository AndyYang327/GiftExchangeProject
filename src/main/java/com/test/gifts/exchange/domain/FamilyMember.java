package com.test.gifts.exchange.domain;

public class FamilyMember {
    
	private String id;

    private String name;
    
    public FamilyMember(String string, String string2) {
    	id = string;
    	name = string2;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
