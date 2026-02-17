package com.npci.transaction.controller;

import org.springframework.web.bind.annotation.*;
import com.npci.transaction.service.ITransactionService;
import com.npci.transaction.repository.UserRepository;
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
    public String transfer(@RequestParam String sender,
                           @RequestParam String receiver,
                           @RequestParam Double amount) {
        return service.transfer(sender, receiver, amount);
    }
}
