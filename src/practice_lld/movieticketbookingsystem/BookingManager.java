package practice_lld.movieticketbookingsystem;

import practice_lld.movieticketbookingsystem.entities.*;
import practice_lld.movieticketbookingsystem.enums.PaymentStatus;
import practice_lld.movieticketbookingsystem.strategy.payment.PaymentStrategy;

import java.util.List;
import java.util.Optional;

public class BookingManager {
    private final SeatLockManger seatLockManger;

    public BookingManager(SeatLockManger seatLockManger) {
        this.seatLockManger = seatLockManger;
    }
    public Optional<Booking> createBooking(User user, List<Seat> seats, Show show, PaymentStrategy paymentStrategy){

        //lock seats
        seatLockManger.lockSeats(show, user.getId(), seats);

        //calculate total price
        double amount = show.getPricingStrategy().calculatePrice(seats);

        //process payment
        Payment payment = paymentStrategy.pay(amount);

        //if payment successful create booking
        if(payment.getStatus()== PaymentStatus.SUCCESS){
            Booking booking = new Booking.BookingBuilder()
                    .setUser(user)
                    .setShow(show)
                    .setSeats(seats)
                    .setTotalAmount(amount)
                    .setPayment(payment)
                    .build();

            booking.confirmBooking();

            seatLockManger.unlockedSeats(show, seats, user.getId());

            return  Optional.of(booking);
        } else{
            System.out.println("Payment failed. Please try again.");
            return Optional.empty();
        }
    }
}
