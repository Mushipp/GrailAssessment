package com.participant.information.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.participant.information.system.dto.Address;
import com.participant.information.system.dto.ParticipantDTO;
import com.participant.information.system.model.Participant;
import com.participant.information.system.service.ParticipantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author praveenmushipatla
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ParticipantRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParticipantService participantService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Participant praveenmushipatla;
    private Participant yigit;
    private Participant yigitBadRequest;

    private final String praveenmushipatlaRefId = "PRM-25";
    private final String yigitRefId = "YYY-25";
    private Object InvalidPropertyException;

    @Before
    public void setup() {
        praveenmushipatla = new Participant();
        praveenmushipatla.setParticipantReferenceId(praveenmushipatlaRefId);
        praveenmushipatla.setParticipantId("dadfasdf333");
        praveenmushipatla.setName("Praveen Mushipatla");
        Address adr1 = new Address("23 Main St.", "", "122", "Raliegh", "North Carolina", 27525);
        praveenmushipatla.setAddress(adr1);
        praveenmushipatla.setPhoneNumber(8881218888L);

        yigit = new Participant();
        yigit.setParticipantId("dfasdfa42323dfas");
        yigit.setParticipantReferenceId(yigitRefId);
        yigit.setName("yigit AAA");
        Address adr2 = new Address("22 Main St.", "", "22", "Raliegh", "North Carolina", 27525);
        yigit.setAddress(adr1);
        yigit.setPhoneNumber(9991219999L);

        yigitBadRequest = new Participant();
        yigitBadRequest.setParticipantId("dfasdfa42323dfas");
        yigitBadRequest.setName("yigit AAA");
        Address adr3 = new Address("22 Main St.", "", "22", "Raliegh", "North Carolina", 27525);
        yigitBadRequest.setAddress(adr1);
        yigitBadRequest.setPhoneNumber(9991219999L);

    }

    @Test
    public void givenparticipants_whenGetAllparticipants_thenReturnJsonArray() throws Exception {
        given(participantService.findAll()).willReturn(Arrays.asList(praveenmushipatla));

        mvc.perform(get("/participants/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(praveenmushipatla.getName())));
    }

    @Test
    public void givenParticipant_whenFindByParticipantNumber_thenReturnJsonArray() throws Exception {
        given(participantService.findByParticipantReferenceId(praveenmushipatlaRefId)).willReturn(praveenmushipatla);

        mvc.perform(get("/participants/byParticipantId/{participantReferenceId}", praveenmushipatlaRefId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(praveenmushipatla.getName())));
    }

    @Test
    public void saveParticipant_itShouldReturnStatusOk() throws Exception {
        try {
            given(participantService.saveOrUpdateParticipant(any(Participant.class))).willReturn(yigit);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        String jsonString = objectMapper.writeValueAsString(yigit);

        mvc.perform(post("/participants/save/")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void saveParticipant_itShouldReturnStatusBadRequest() throws Exception {
        try {
            given(participantService.saveOrUpdateParticipant(any(Participant.class))).willThrow((Throwable) new InvalidPropertyException(ParticipantDTO.class, "participantReferenceId", "Should Not Be Null"));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        String jsonString = objectMapper.writeValueAsString(yigitBadRequest);

        mvc.perform(post("/participants/save/")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteByParticipantRefIf_itShouldReturnStatusOk() throws Exception {

        given(participantService.findByParticipantReferenceId(praveenmushipatlaRefId)).willReturn(praveenmushipatla);
        Mockito.doNothing().when(participantService).deleteParticipantById(any(String.class));

        mvc.perform(delete("/participants/delete/{participantReferenceId}", praveenmushipatlaRefId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
