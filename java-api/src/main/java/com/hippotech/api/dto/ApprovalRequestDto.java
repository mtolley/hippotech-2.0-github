package com.hippotech.api.dto;

import com.hippotech.api.model.MortgageApprovalEvent;

import javax.persistence.*;
import java.util.List;

public class ApprovalRequestDto {
        private String address1;
        private String status;
        private int purchasePrice;
        private int amountToBorrow;

        private List<MortgageApprovalEvent> history;

        public List<MortgageApprovalEvent> getHistory() {
            return history;
        }

        public void setHistory(List<MortgageApprovalEvent> history) {
            this.history = history;
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