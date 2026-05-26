package practice_lld.movieticketbookingsystem.entities;

import practice_lld.movieticketbookingsystem.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private final String id;
    private final PaymentStatus status;
    private final String transactionId;
    private final double amount;

    public Payment(PaymentStatus status, String transactionId, double amount) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
