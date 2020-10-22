//This tests on an edge case when there is no member in the family.
//It would throw an exception: "No Members Present"
package com.test.gifts.exchange.services;

import com.test.gifts.exchange.dao.GiftRepository;
import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.domain.GiftExchange;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftExchangeServiceTest2 {

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private Map<String, FamilyMember> threeYearRecipientTrackerMap;

    @InjectMocks
    private GiftExchangeService giftExchangeService;

    @Before
    public void setUp() throws Exception {
        when(giftRepository.fetchFamilyMemberMap()).thenReturn(setupStubData());

    }

    @Test
    public void shouldfetchGiftExchangeDetails() throws FamilyMemberNotFoundException {

        Set<GiftExchange> giftExchanges = giftExchangeService.fetchGiftExchangeDetails();
        assertNotNull(giftExchanges);

    }

    private Map<String, FamilyMember> setupStubData() {


        Map<String, FamilyMember>  familyMemberConcurrentHashMap = new ConcurrentHashMap<>();
        

        return familyMemberConcurrentHashMap;
    }
}