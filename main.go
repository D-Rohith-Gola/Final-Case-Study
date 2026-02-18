package main

import (
	"context"
	"encoding/json"
	"log"
	"os"
	"time"

	"github.com/segmentio/kafka-go"
)

type FraudResult struct {
	TxnID   string    `json:"txnId"`
	Status  string    `json:"status"`
	Checked time.Time `json:"checkedAt"`
}

func main() {

	kafkaBroker := os.Getenv("KAFKA_BROKER")
	if kafkaBroker == "" {
		kafkaBroker = "kafka:9092"
	}

	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{kafkaBroker},
		Topic:   "txn-init",
		GroupID: "fraud-group",
	})

	writer := kafka.NewWriter(kafka.WriterConfig{
		Brokers: []string{kafkaBroker},
		Topic:   "txn-fraud-check",
	})

	log.Println("Fraud Service Started...")

	for {
		msg, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Println("Error reading message:", err)
			continue
		}

		txnID := string(msg.Value)

		log.Println("Processing Fraud Check for:", txnID)

		// Simulated fraud checks
		time.Sleep(100 * time.Millisecond)

		result := FraudResult{
			TxnID:   txnID,
			Status:  "SAFE",
			Checked: time.Now(),
		}

		jsonResult, _ := json.Marshal(result)

		err = writer.WriteMessages(context.Background(),
			kafka.Message{
				Key:   []byte(txnID),
				Value: jsonResult,
			},
		)

		if err != nil {
			log.Println("Error publishing fraud result:", err)
		} else {
			log.Println("Fraud Check Passed:", txnID)
		}
	}
}
