package com.alejoestevez.gnbproducts.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transaction extends Product implements Serializable {

    @SerializedName("amount")
    public String amount;
    @SerializedName("currency")
    public String currency;

    public Transaction(String sku) {
        super(sku);
    }

    @Override
    public String toString() {
        return "amount='" + amount + '\'' +
                ", currency='" + currency + '\'';
    }

    public BigDecimal getAmount() {
        return new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
