package practice_lld.parkinglot.Strategy.parking;

import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.entities.ParkingFloor;
import practice_lld.parkinglot.entities.ParkingSpot;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarthestParkingSpot implements ParkingSpotStrategy{
    @Override
    public Optional<ParkingSpot> getParkingSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        Collections.reverse(floors);
        for(ParkingFloor floor: floors){
            Optional<ParkingSpot> spot = floor.getAvailableSpot(vehicle);
            if(spot.isPresent()){
                return spot;
            }
        }
        return Optional.empty();
    }
}
