package com.npci.settlement.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.npci.settlement.service.FabricSettlementService;

@Component
public class FraudResultListener {

    private final FabricSettlementService settlementService;

    public FraudResultListener(FabricSettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @KafkaListener(topics = "txn-fraud-check", groupId = "settlement-group")
    public void listen(String message) {

        System.out.println("Received fraud result: " + message);

        if (message.contains("SAFE")) {

            String txnId = message.split("\"txnId\":\"")[1].split("\"")[0];

            settlementService.settleTransaction(txnId, "SBI", "HDFC", "5000");
        }
    }
}
