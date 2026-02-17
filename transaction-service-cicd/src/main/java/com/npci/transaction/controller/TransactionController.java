package com.npci.transaction.controller;

import org.springframework.web.bind.annotation.*;
import com.npci.transaction.service.ITransactionService;

import jakarta.validation.Valid;

import com.npci.transaction.repository.UserRepository;
import com.npci.transaction.dto.TransactionResponse;
import com.npci.transaction.dto.TransferRequest;
import com.npci.transaction.entity.User;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final ITransactionService service;
    private final UserRepository userRepo;

    public TransactionController(ITransactionService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(@Valid @RequestBody TransferRequest request) {

        return service.transfer(
                request.getSender(),
                request.getReceiver(),
                request.getAmount()
        );
    }
}
