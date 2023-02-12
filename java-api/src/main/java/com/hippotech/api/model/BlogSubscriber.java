package com.hippotech.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class BlogSubscriber {
    @Id
    private String email;
    private boolean partnersIncluded;

    public String getEmail() {
        return this.email;
    }
    public void setDate(String email) {
        this.email = email;
    }

    public boolean getPartnersIncluded() {
        return this.partnersIncluded;
    }
    public void setPartnersIncluded(boolean partnersIncluded) {
        this.partnersIncluded = partnersIncluded;
    }
}
