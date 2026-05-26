package practice_lld.movieticketbookingsystem.strategy.pricing;

import practice_lld.movieticketbookingsystem.entities.Seat;

import java.util.List;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}
