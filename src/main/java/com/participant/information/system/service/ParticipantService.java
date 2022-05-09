package com.participant.information.system.service;

import com.participant.information.system.model.Participant;

import java.util.List;

/**
 * @author regcrix
 */
public interface ParticipantService {

    List<Participant> findAll();

    Participant findByParticipantReferenceId(String participantReferenceId);

    Participant findByPhoneNumber(long phoneNumber);

    Participant saveOrUpdateParticipant(Participant Participant) throws Throwable;

    void deleteParticipantById(String participantReferenceId);

}