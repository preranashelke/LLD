package practice_lld.movieticketbookingsystem.strategy.pricing;

import practice_lld.movieticketbookingsystem.entities.Seat;

import java.util.List;

public class WeekdayPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream().mapToDouble(seat->seat.getType().getPrice()).sum();
    }
}
