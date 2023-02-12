package com.hippotech.approval;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ApprovalRequest {
    private @Id
    @GeneratedValue
    Long id;
    private String address1;
    private int purchasePrice;
    private int amountToBorrow;
    private String cardNumber;
    private String cardName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() { return address1; }

    public void setAddress1(String address1) { this.address1 = address1; }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCardName() {
      return cardName;
    }
    
    public int getAmountToBorrow() {
        return amountToBorrow;
    }

    public void setAmountToBorrow(int amountToBorrow) {
        this.amountToBorrow = amountToBorrow;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalRequest that = (ApprovalRequest) o;
        return purchasePrice == that.purchasePrice &&
                amountToBorrow == that.amountToBorrow &&
                Objects.equals(id, that.id) &&
                Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, purchasePrice, amountToBorrow);
    }

    @Override
    public String toString() {
        return "ApprovalRequest{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName=;" + cardName + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", amountToBorrow=" + amountToBorrow +
                '}';
    }
}
