package com.npci.settlement.service;

import org.hyperledger.fabric.gateway.*;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FabricSettlementService {

    public void settleTransaction(String txnId,
                                  String senderBank,
                                  String receiverBank,
                                  String amount) {

        try {

            Path walletPath = Paths.get("wallet");
            Wallet wallet = Wallets.newFileSystemWallet(walletPath);

            Gateway.Builder builder = Gateway.createBuilder()
                    .identity(wallet, "appUser")
                    .networkConfig(Paths.get("connection.json"));

            try (Gateway gateway = builder.connect()) {

                Network network = gateway.getNetwork("mychannel");
                Contract contract = network.getContract("settlement");

                contract.submitTransaction(
                        "CreateSettlement",
                        txnId,
                        senderBank,
                        receiverBank,
                        amount
                );

                System.out.println("Settlement recorded on blockchain: " + txnId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
