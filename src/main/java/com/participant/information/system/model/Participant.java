package com.participant.information.system.model;

import com.participant.information.system.dto.Address;
import org.springframework.data.annotation.Id;

/**
 * @author PraveenM
 */
public class Participant
{
    @Id
    private String participantId;

    private String participantReferenceId;

    private String name;
    private long phoneNumber;
    private Address address;

    public Participant() {
    }

    public Participant(String participantId, String participantReferenceId, String name, long phoneNumber, Address address) {
        this.participantId = participantId;
        this.participantReferenceId = participantReferenceId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipantReferenceId() {
        return participantReferenceId;
    }

    public void setParticipantReferenceId(String participantReferenceId) {
        this.participantReferenceId = participantReferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantId='" + participantId + '\'' +
                ", participantReferenceId='" + participantReferenceId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address=" + address +
                '}';
    }
}
