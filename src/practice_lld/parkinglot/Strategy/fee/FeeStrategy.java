package practice_lld.parkinglot.Strategy.fee;

import practice_lld.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {

    double calculateFee(ParkingTicket parkingTicket);
}
