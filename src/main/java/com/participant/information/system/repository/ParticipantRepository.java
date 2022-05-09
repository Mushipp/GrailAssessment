package com.participant.information.system.repository;

import com.participant.information.system.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author praveenmushipatla
 */

// No need implementation, just one interface, and you have CRUD, thanks Spring Data!

public interface ParticipantRepository extends MongoRepository<Participant, String> {

    Participant findByParticipantReferenceId(String participantReferenceId);

    Participant findByName(String name);

    Participant findByPhoneNumber(long phoneNumber);

    List<Participant> findAll();

}
