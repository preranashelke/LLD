package practice_lld.parkinglot.Strategy.fee;

import practice_lld.parkinglot.entities.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy{
    private static final double HOUR_RATE = 10.0;
    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExistTimesStamp() - parkingTicket.getEntryTimeStamp();
        long hours = (duration/(1000*60*60))+1;

        return  hours * HOUR_RATE;
    }
}
