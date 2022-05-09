package com.participant.information.system.service;

import com.participant.information.system.dto.Address;
import com.participant.information.system.model.Participant;
import com.participant.information.system.repository.ParticipantRepository;
import com.participant.information.system.service.impl.ParticipantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author praveenmushipatla
 */
@RunWith(SpringRunner.class)
public class ParticipantServiceTest {

    @TestConfiguration
    static class ParticipantServiceImplTestContextConfiguration {
        @Bean
        public ParticipantService participantService() {
            return new ParticipantServiceImpl();
        }
    }

    @Autowired
    private ParticipantService participantService;

    @MockBean
    private ParticipantRepository participantRepository;

    private Participant praveenmushipatla;
    private Participant yigit;

    private final String praveenmushipatlaRefId = "KFG-23";
    private final String yigitRefId = "YYR=22";
    private final List<Participant> participants = new ArrayList<>();

    @Before
    public void setup() {
        praveenmushipatla = new Participant();
        praveenmushipatla.setParticipantReferenceId(praveenmushipatlaRefId);
        praveenmushipatla.setName("Praveen Mushipatla");
        Address adr1 = new Address("23 Main St.", "", "122", "Raliegh", "North Carolina", 27525);
        praveenmushipatla.setAddress(adr1);
        praveenmushipatla.setPhoneNumber(8881218888L);

        yigit = new Participant();
        yigit.setParticipantReferenceId(yigitRefId);
        yigit.setName("yigit AAA");
        Address adr2 = new Address("22 Main St.", "", "22", "Raliegh", "North Carolina", 27525);
        praveenmushipatla.setAddress(adr1);
        praveenmushipatla.setPhoneNumber(9991219999L);

        participants.add(praveenmushipatla);
        participants.add(yigit);

        Mockito.when(participantRepository.findAll()).thenReturn(participants);

        Mockito.when(participantRepository.findByParticipantReferenceId(praveenmushipatlaRefId))
                .thenReturn(praveenmushipatla);

        Mockito.when(participantRepository.findByPhoneNumber(yigit.getPhoneNumber()))
                .thenReturn(yigit);

        Mockito.when(participantRepository.save(praveenmushipatla)).thenReturn(praveenmushipatla);
    }

    @Test
    public void testFindAll_thenParticipantListShouldBeReturned() {
        List<Participant> foundParticipants = participantService.findAll();

        assertNotNull(foundParticipants);
        assertEquals(2, foundParticipants.size());
    }

    @Test
    public void testFindByParticipantNumber23_thenpraveenmushipatlaShouldBeReturned() {
        Participant found = participantService.findByParticipantReferenceId(praveenmushipatlaRefId);

        assertNotNull(found);
        assertEquals(praveenmushipatla.getName(), found.getName());
        assertEquals(praveenmushipatla.getParticipantReferenceId(), found.getParticipantReferenceId());
    }

    @Test
    public void testFindByPhoneNumber_thenYigitShouldBeReturned() {
        Participant found = participantService.findByPhoneNumber(yigit.getPhoneNumber());

        assertNotNull(found);
        assertEquals(yigit.getName(), found.getName());
        assertEquals(yigit.getParticipantReferenceId(), found.getParticipantReferenceId());
    }


    @Test
    public void testSaveOrUpdateParticipant_thenParticipantShouldBeReturned() {
        Participant found = null;
        try {
            found = participantService.saveOrUpdateParticipant(praveenmushipatla);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        assertNotNull(found);
        assertEquals(praveenmushipatla.getName(), found.getName());
        assertEquals(praveenmushipatla.getParticipantReferenceId(), found.getParticipantReferenceId());
    }

    @Test
    public void testDeleteParticipantById() {
        participantService.deleteParticipantById(praveenmushipatla.getParticipantReferenceId());

        Mockito.verify(participantRepository, Mockito.times(1))
                .deleteById(praveenmushipatla.getParticipantReferenceId());
    }

}
