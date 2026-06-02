package practice_lld.parkinglot.Strategy.parking;

import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.entities.ParkingFloor;
import practice_lld.parkinglot.entities.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class BestFitParkingSpot implements ParkingSpotStrategy{
    @Override
    public Optional<ParkingSpot> getParkingSpot(Vehicle vehicle, List<ParkingFloor> floors) {

        Optional<ParkingSpot> bestSpot = Optional.empty();

        for(ParkingFloor floor : floors){
            Optional<ParkingSpot> spot = floor.getAvailableSpot(vehicle);
            if(spot.isPresent()){
                if(bestSpot.isEmpty()){
                    bestSpot = spot;
                } else {
                    if(spot.get().getSpotSize().ordinal()<bestSpot.get().getSpotSize().ordinal()){
                        bestSpot = spot;
                    }
                }

            }
        }

        return bestSpot;
    }
}
