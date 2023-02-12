package com.hippotech.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class FraudCheck {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String name;
    private String address1;
    private String zip;
    private int loanValue;

    public long getId() {
        return id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(int loanValue) {
        this.loanValue = loanValue;
    }
}
