package practice_lld.movieticketbookingsystem.strategy.payment;

import practice_lld.movieticketbookingsystem.entities.Payment;
import practice_lld.movieticketbookingsystem.enums.PaymentStatus;

import java.util.UUID;

public class CreditCardPaymentStrategy implements PaymentStrategy{

    private final String cardNumber;
    private final String cvv;

    public CreditCardPaymentStrategy(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }


    @Override
    public Payment pay(double amount) {
        System.out.println("processing credit card payment " + amount);
        return new Payment(PaymentStatus.SUCCESS, "TXN"+ UUID.randomUUID(), amount);
    }
}
