package practice_lld.parkinglot.Strategy.parking;

import practice_lld.parkinglot.entities.ParkingFloor;
import practice_lld.parkinglot.entities.ParkingSpot;
import practice_lld.parkinglot.Vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotStrategy {

    Optional<ParkingSpot> getParkingSpot(Vehicle vehicle, List<ParkingFloor> floors);
}
