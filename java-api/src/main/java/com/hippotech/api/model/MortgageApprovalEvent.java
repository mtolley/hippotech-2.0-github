package com.hippotech.api.model;

import javax.persistence.*;

@Entity
public class MortgageApprovalEvent {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String party;
    private String date;
    private String event;
    private String details;

    public long getId() { return id; }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
