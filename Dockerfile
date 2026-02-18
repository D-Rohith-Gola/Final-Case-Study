FROM golang:1.21-alpine AS builder
WORKDIR /app
COPY go.mod .
RUN go mod download
COPY . .
RUN go build -o fraud-service

FROM alpine:latest
WORKDIR /app
COPY --from=builder /app/fraud-service .
EXPOSE 8764
CMD ["./fraud-service"]
