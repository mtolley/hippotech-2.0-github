package com.hippotech.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ApprovalRequest {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String address1;
    private String status;
    private int purchasePrice;
    private int amountToBorrow;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MortgageApprovalEvent> history;

    public List<MortgageApprovalEvent> getHistory() {
        return history;
    }

    public void setHistory(List<MortgageApprovalEvent> history) {
        this.history = history;
    }

    public long getId() {
        return id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getAmountToBorrow() {
        return amountToBorrow;
    }

    public void setAmountToBorrow(int amountToBorrow) {
        this.amountToBorrow = amountToBorrow;
    }
}
