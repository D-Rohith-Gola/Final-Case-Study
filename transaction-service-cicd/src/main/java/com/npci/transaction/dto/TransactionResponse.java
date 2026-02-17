package com.npci.transaction.dto;

import java.time.LocalDateTime;

public class TransactionResponse {

    private String message;

    private String txnId;
    private Double amount;
    private String payerName;
    private String payerBankName;
    private String payeeName;
    private String payeeBankName;
    private LocalDateTime paymentTime;

    private Double payerOldBalance;
    private Double payerNewBalance;

    private Double receiverOldBalance;
    private Double receiverNewBalance;

    public TransactionResponse(String message,
                               String txnId,
                               Double amount,
                               String payerName,
                               String payerBankName,
                               String payeeName,
                               String payeeBankName,
                               LocalDateTime paymentTime,
                               Double payerOldBalance,
                               Double payerNewBalance,
                               Double receiverOldBalance,
                               Double receiverNewBalance) {

        this.message = message;
        this.txnId = txnId;
        this.amount = amount;
        this.payerName = payerName;
        this.payerBankName = payeeBankName;
        this.payeeName = payeeName;
        this.payeeBankName = payeeBankName;
        this.paymentTime = paymentTime;
        this.payerOldBalance = payerOldBalance;
        this.payerNewBalance = payerNewBalance;
        this.receiverOldBalance = receiverOldBalance;
        this.receiverNewBalance = receiverNewBalance;
    }

    public String getMessage() { return message; }
    public String getTxnId() { return txnId; }
    public Double getAmount() { return amount; }
    public String getPayerName() { return payerName; }
    public String getPayerBankName() {return payerBankName; }
    public String getPayeeName() { return payeeName; }
    public String getPayeeBankName() {return payeeBankName; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public Double getPayerOldBalance() { return payerOldBalance; }
    public Double getPayerNewBalance() { return payerNewBalance; }
    public Double getReceiverOldBalance() { return receiverOldBalance; }
    public Double getReceiverNewBalance() { return receiverNewBalance; }
}
