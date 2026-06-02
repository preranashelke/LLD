package practice_lld.parkinglot.entities;


import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.Vehicle.VehicleSize;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber){
        this.floorNumber=floorNumber;
        this.spots=new ConcurrentHashMap<>();
    }

    public void addSpot(ParkingSpot spot){
        spots.put(spot.getSpotId(), spot);
    }

    public synchronized Optional<ParkingSpot> getAvailableSpot(Vehicle vehicle){

        return spots.values().stream()
                .filter(spot-> !spot.isOccupied()&&spot.canFitVehicle(vehicle))
                .sorted(Comparator.comparing(ParkingSpot::getSpotSize))
                .findFirst();

    }
    public void displayAvailability(){
        System.out.printf("-----Floor %d availability----\n", floorNumber);

        Map<VehicleSize, Long> availableCounts = spots.values().stream()
                .filter(parkingSpot -> !parkingSpot.isOccupied())
                .collect(Collectors.groupingBy(ParkingSpot::getSpotSize, Collectors.counting()));

        for(VehicleSize vehicleSize: VehicleSize.values()){
            System.out.printf(" %s spots: %d\n", vehicleSize, availableCounts.getOrDefault(vehicleSize, 0L));

        }
    }

}
