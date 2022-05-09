package com.participant.information.system.controller;

import com.participant.information.system.dto.ParticipantDTO;
import com.participant.information.system.model.Participant;
import com.participant.information.system.service.ParticipantService;
import com.participant.information.system.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author praveenmushipatla
 */
@RestController
@RequestMapping("/participants")
public class ParticipantRegistryRestController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping(value = "/")
    public List<ParticipantDTO> getAllParticipants() {
        return ObjectMapperUtils.mapAll(participantService.findAll(), ParticipantDTO.class);
    }

    @GetMapping(value = "/byParticipantId/{participantReferenceId}")
    public ParticipantDTO getParticipantByParticipantNumber(@PathVariable("participantReferenceId") String refId) {
        return ObjectMapperUtils.map(participantService.findByParticipantReferenceId(refId), ParticipantDTO.class);
    }

    @GetMapping(value = "/byParticipantPhoneNumber/{phoneNumber}")
    public ParticipantDTO getParticipantByPhoneNumber(@PathVariable("phoneNumber") Long phoneNumber) {
        return ObjectMapperUtils.map(participantService.findByPhoneNumber(phoneNumber), ParticipantDTO.class);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateParticipant(@RequestBody ParticipantDTO participantDTO) {
        try {
            participantService.saveOrUpdateParticipant(ObjectMapperUtils.map(participantDTO, Participant.class));
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity("Participant Reference Number Required", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Participant added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{participantReferenceId}")
    public ResponseEntity<?> deleteParticipantByParticipantReferenceId(@PathVariable("participantReferenceId") String refId) {
        if (null != participantService.findByParticipantReferenceId(refId))
        {
            if (null != participantService.findByParticipantReferenceId(refId).getParticipantId()) {
                participantService.deleteParticipantById(participantService.findByParticipantReferenceId(refId).getParticipantId());
                return new ResponseEntity("Participant deleted successfully", HttpStatus.OK);
            }
        }

        return new ResponseEntity("No Matching Participant Found", HttpStatus.NO_CONTENT);
    }

}
