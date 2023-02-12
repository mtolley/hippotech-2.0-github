package com.hippotech.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class NewApprovalRequestDto {
        @NotBlank(message = "address1 must not be empty")
        private String address1;

        @Min(value = 200000, message = "purchasePrice must be at least 200000 ($200k)")
        @Max(value = 1000000, message = "purchasePrice can be a maximum of 1000000 ($1M")
        private int purchasePrice;

        @Min(value = 100000, message = "amountToBorrow must be at least 100000 ($100k)")
        @Max(value = 900000, message = "amountToBorrow can be a maximum of 900000 ($900k")
        private int amountToBorrow;

        private String cardNumber;

        private String cardName;

        private String zip;

        public String getZip() { return zip; }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
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

        public String getCardNumber() { return cardNumber; }

        public String getCardName() { return cardName; }

        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

        public void setCardName(String cardName) { this.cardName = cardName; }
    }