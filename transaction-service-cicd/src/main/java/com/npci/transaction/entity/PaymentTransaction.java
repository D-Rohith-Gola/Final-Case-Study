package com.npci.transaction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PaymentTransaction {

    @Id
    private String txnId;

    private String payerName;
    private String payeeName;
    private Double amount;
    private LocalDateTime paymentTime;

    public String getTxnId() { return txnId; }
    public void setTxnId(String txnId) { this.txnId = txnId; }

    public String getPayerName() { return payerName; }
    public void setPayerName(String payerName) { this.payerName = payerName; }

    public String getPayeeName() { return payeeName; }
    public void setPayeeName(String payeeName) { this.payeeName = payeeName; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getPaymentTime() { return paymentTime; }
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; }
}
