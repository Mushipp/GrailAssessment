package com.participant.information.system.service.impl;

import com.participant.information.system.model.Participant;
import com.participant.information.system.repository.ParticipantRepository;
import com.participant.information.system.service.ParticipantService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author praveenmushipatla
 */
@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;
    private Object InvalidPropertyException;

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant findByParticipantReferenceId(String refId) {
        return participantRepository.findByParticipantReferenceId(refId);
    }

    @Override
    public Participant findByPhoneNumber(long participantEmail) {
        return participantRepository.findByPhoneNumber(participantEmail);
    }

    @Override
    public Participant saveOrUpdateParticipant(Participant participant) throws Throwable {
        if(null == participant.getParticipantReferenceId()){
            throw (Throwable) InvalidPropertyException;
        }
        Participant existingParticipant = findByParticipantReferenceId(participant.getParticipantReferenceId());
        if(existingParticipant != null){
            participant.setParticipantId(existingParticipant.getParticipantId());
        }
        return participantRepository.save(participant);
    }

    @Override
    public void deleteParticipantById(String id) {
        participantRepository.deleteById(id);
    }
}
