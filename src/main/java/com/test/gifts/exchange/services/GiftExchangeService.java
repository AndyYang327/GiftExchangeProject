package com.test.gifts.exchange.services;

import com.test.gifts.exchange.dao.GiftRepository;
import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.domain.GiftExchange;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GiftExchangeService {

    private GiftRepository giftRepository;
    private Map<String, Queue<String>> threeYearRecipientTrackerMap;

    @Autowired
    public GiftExchangeService(GiftRepository giftRepository, Map<String, Queue<String>> threeYearRecipientTrackerMap) {
        this.giftRepository = giftRepository;
        this.threeYearRecipientTrackerMap = threeYearRecipientTrackerMap;
    }

    public Set<GiftExchange> fetchGiftExchangeDetails() throws FamilyMemberNotFoundException {
        Map<String, FamilyMember> map = giftRepository.fetchFamilyMemberMap();
        if(map.isEmpty()) {
            throw new FamilyMemberNotFoundException("No Members Present");
        }
        Set<GiftExchange> giftExchangeSet = new HashSet<>();
        List<Integer> usedAssignments = new ArrayList<>();
        List<String> assignments = new ArrayList<>();
        List<String> keys = new ArrayList<>(map.keySet());
        for(String key : keys) {
            Queue<String> previousRecipientTracker = threeYearRecipientTrackerMap.get(key);

            Random rand = new Random();
            int value = -1;
            value = rand.nextInt(keys.size());
            while((usedAssignments.contains(value) && assignments.size() < keys.size())
                    || value == keys.indexOf(key) 
                    || (previousRecipientTracker != null && previousRecipientTracker.contains(key))) {
                value = rand.nextInt(keys.size());
            }
            String recipientAssigned = keys.get(value);
            usedAssignments.add(value);
            assignments.add(key);

             // No gift from same person every 3 years
             if(previousRecipientTracker == null) {
                Queue<String> initialRecipientQueue = new LinkedList<String>();
                initialRecipientQueue.add(recipientAssigned);
                threeYearRecipientTrackerMap.put(key, initialRecipientQueue);
             } else {
                previousRecipientTracker.add(recipientAssigned);
                if(previousRecipientTracker.size() > 3) {
                    previousRecipientTracker.poll();
                }
                threeYearRecipientTrackerMap.put(key, previousRecipientTracker);
             }
             GiftExchange giftExchange = new GiftExchange(key, recipientAssigned);
             giftExchangeSet.add(giftExchange);
        }
        return giftExchangeSet;
    }

}
