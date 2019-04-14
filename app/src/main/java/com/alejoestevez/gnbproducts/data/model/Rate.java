package com.alejoestevez.gnbproducts.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class Rate implements Serializable {

    @SerializedName("from")
    public String from;
    @SerializedName("to")
    public String to;
    @SerializedName("rate")
    public String rate;

    public BigDecimal getRate() {
        return new BigDecimal(rate).setScale(3, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Rate)) {
            return false;
        }

        Rate r = (Rate) o;
        return this.from.equals(r.from) && this.to.equals(r.to);
    }
}
