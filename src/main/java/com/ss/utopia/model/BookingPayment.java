package com.ss.utopia.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookingPayment {
    @Column (nullable = false)
    private boolean refunded;
    @Column (nullable = false)
    private String stripeId;

    public BookingPayment(boolean refunded, String stripeId) {
        this.refunded = refunded;
        this.stripeId = stripeId;
    }

    public BookingPayment() {
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

}
