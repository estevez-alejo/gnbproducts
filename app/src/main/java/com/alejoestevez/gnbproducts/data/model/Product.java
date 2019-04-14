package com.alejoestevez.gnbproducts.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    @SerializedName("sku")
    public String sku;

    @SerializedName("transactions")
    public List<Transaction> transactions = new ArrayList<>();

    public List<Rate> rates = new ArrayList<>();

    public Product(String sku) {
        this.sku = sku;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product c = (Product) o;
        return this.sku.equals(c.sku);
    }

    public BigDecimal getDirectRate(String from, String to) {
        for (Rate rate : rates) {
            if (rate.from.equals(from) && rate.to.equals(to)) {
                return rate.getRate();
            }
        }
        return null;
    }

    public BigDecimal retrieveRate(String from, String to) {
        BigDecimal finalrate = getDirectRate(from, "EUR");
        if (finalrate == null) {
            Rate rate = getDerivedRateFrom(from, to);
            return retrieveRate(rate.to, from).multiply(rate.getRate());
        } else {
            return finalrate;
        }
    }

    public Rate getDerivedRateFrom(String from, String not) {
        for (Rate rate : rates) {
            if (rate.from.equals(from) && !rate.to.equals(not)) {
                return rate;
            }
        }
        return null;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal total = new BigDecimal("0.00").setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
        for (Transaction transaction : transactions) {
            if (transaction.currency.equals("EUR")) {
                total = total.add(transaction.getAmount());
            } else {
                BigDecimal directConversion = getDirectRate(transaction.currency, "EUR");
                if (directConversion != null) {
                    total = total.add(directConversion.multiply(transaction.getAmount()));
                } else {
                    total = total.add(retrieveRate(transaction.currency, "EUR").multiply(transaction.getAmount()));
                }
            }
        }
        return total.setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
    }

    public String getCountTransactions() {
        return String.valueOf(transactions.size());
    }
}
