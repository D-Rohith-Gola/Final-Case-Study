package com.npci.transaction.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.npci.transaction.service.ITransactionService;
import com.npci.transaction.repository.*;
import com.npci.transaction.entity.*;
import com.npci.transaction.exception.InsufficientBalanceException;
import com.npci.transaction.exception.UserNotFoundException;

import org.springframework.kafka.core.KafkaTemplate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final UserRepository userRepo;
    private final TransactionRepository txnRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionServiceImpl(UserRepository userRepo,
                                  TransactionRepository txnRepo,
                                  KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepo = userRepo;
        this.txnRepo = txnRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public String transfer(String senderName, String receiverName, Double amount) {

        User sender = userRepo.findByName(senderName);
        User receiver = userRepo.findByName(receiverName);

        if (sender == null) {
            throw new UserNotFoundException("Sender not found");
        }

        if (receiver == null) {
            throw new UserNotFoundException("Receiver not found");
        }

        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepo.save(sender);
        userRepo.save(receiver);

        PaymentTransaction txn = new PaymentTransaction();
        txn.setTxnId(UUID.randomUUID().toString());
        txn.setPayerName(senderName);
        txn.setPayeeName(receiverName);
        txn.setAmount(amount);
        txn.setPaymentTime(LocalDateTime.now());

        txnRepo.save(txn);

        kafkaTemplate.send("txn-init", txn.getTxnId());

        return "Transaction Successful";
    }
}
