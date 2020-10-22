package com.test.gifts.exchange.config;

import com.test.gifts.exchange.domain.FamilyMember;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
	//Use ConcurrentHashMap to handle cases when multiple members are viewing and changing at the same time
    private Map<String, FamilyMember>  familyMemberConcurrentHashMap = new ConcurrentHashMap<>();
    private Map<String, Queue<String>> threeYearRecipientTrackerMap  = new ConcurrentHashMap<>();

    @Bean
    public Map<String, FamilyMember> familyMemberMap() {

        FamilyMember familyMember1 = new FamilyMember("1", "Andy");
        FamilyMember familyMember2 = new FamilyMember("2", "Andy Father");
        FamilyMember familyMember3 = new FamilyMember("3", "Andy Mother");
        FamilyMember familyMember4 = new FamilyMember("4", "Andy Uncle");
        FamilyMember familyMember5 = new FamilyMember("5", "Andy Aunt");
        FamilyMember familyMember6 = new FamilyMember("6", "Andy GrandMother");
        FamilyMember familyMember7 = new FamilyMember("7", "Andy GrandFather");
        FamilyMember familyMember8 = new FamilyMember("8", "Andy Son");
        FamilyMember familyMember9 = new FamilyMember("9", "Andy Daughter");

        familyMemberConcurrentHashMap.put(familyMember1.getId(), familyMember1);
        familyMemberConcurrentHashMap.put(familyMember2.getId(), familyMember2);
        familyMemberConcurrentHashMap.put(familyMember3.getId(), familyMember3);
        familyMemberConcurrentHashMap.put(familyMember4.getId(), familyMember4);
        familyMemberConcurrentHashMap.put(familyMember5.getId(), familyMember5);
        familyMemberConcurrentHashMap.put(familyMember6.getId(), familyMember6);
        familyMemberConcurrentHashMap.put(familyMember7.getId(), familyMember7);
        familyMemberConcurrentHashMap.put(familyMember8.getId(), familyMember8);
        familyMemberConcurrentHashMap.put(familyMember9.getId(), familyMember9);

        return familyMemberConcurrentHashMap;

    }


    @Bean
    public Map<String, Queue<String>> threeYearRecipientTrackerMap() {
        return threeYearRecipientTrackerMap;
    }




}
