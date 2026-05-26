package practice_lld.movieticketbookingsystem.strategy.payment;

import practice_lld.movieticketbookingsystem.entities.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}
