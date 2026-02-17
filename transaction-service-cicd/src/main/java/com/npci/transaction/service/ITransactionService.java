package com.npci.transaction.service;

import com.npci.transaction.dto.TransactionResponse;

public interface ITransactionService {
	TransactionResponse transfer(String senderName, String receiverName, Double amount);
}
