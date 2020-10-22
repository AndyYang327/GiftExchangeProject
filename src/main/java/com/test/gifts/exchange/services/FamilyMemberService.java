package com.test.gifts.exchange.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.exception.FamilyMemberAlreadyPresentException;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FamilyMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyMemberService.class);

    @Autowired
    private Map<String, FamilyMember> familyMemberMap;

    //list the family members
    public List<FamilyMember> listAllFamilyMembers() {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(familyMemberMap.values());
    }

    //get a single family member
    public FamilyMember fetchFamilyMember(String id) throws FamilyMemberNotFoundException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exist with the given id " + id);
        }
        return familyMemberMap.get(id);

    }
    
    //add a family member
    public void addFamilyMember(FamilyMember familyMember) throws FamilyMemberAlreadyPresentException {
        if (familyMember == null || familyMemberMap.containsKey(familyMember.getId())) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberAlreadyPresentException("family member already present with the given id " + familyMember.getId());
        }
        FamilyMember newFamilyMember = new FamilyMember(familyMember.getId(), familyMember.getName());
        familyMemberMap.put(familyMember.getId(), newFamilyMember);
    }

    //update a family member
    public void updateFamilyMember(String id, String name) throws FamilyMemberNotFoundException, IOException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exist with the given id " + id);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(name, HashMap.class);
        String value = (String)hashMap.get("name");
        FamilyMember newFamilyMember = new FamilyMember(id, value);
        familyMemberMap.put(id, newFamilyMember); // Parse Json from name

    }
    
    //delete a family member
    public void removeFamilyMember(String id) throws FamilyMemberNotFoundException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exist with the given id " + id);
        }
        familyMemberMap.remove(id);
    }

    private boolean isFamilyMemberMapNullOrEmpty(Map<String, FamilyMember> familyMemberMap) {
        if (familyMemberMap == null || familyMemberMap.isEmpty()) {
            return true;
        }
        return false;
    }
}
