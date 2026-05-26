package practice_lld.movieticketbookingsystem.strategy.pricing;

import practice_lld.movieticketbookingsystem.entities.Seat;

import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{
    private static final double WEEKEND_SURCHARGE = 1.2;
    @Override
    public double calculatePrice(List<Seat> seats) {
        double price = seats.stream().mapToDouble(seat -> seat.getType().getPrice()).sum();

        return price*WEEKEND_SURCHARGE;
    }
}
