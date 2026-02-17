package com.npci.transaction.service;

public interface ITransactionService {
    String transfer(String senderName, String receiverName, Double amount);
}
