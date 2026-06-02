package practice_lld.parkinglot.Strategy.fee;

import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.Vehicle.VehicleSize;
import practice_lld.parkinglot.entities.ParkingTicket;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy{
    private static final Map<VehicleSize, Double> HOUR_RATE= Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.MEDIUM, 20.0,
            VehicleSize.LARGE, 30.0

    );
    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExistTimesStamp() - parkingTicket.getEntryTimeStamp();
        long hours = (duration/(1000*60*60))+1;

        return  hours * HOUR_RATE.get(parkingTicket.getVehicle().getVehicleSize());
    }
}
