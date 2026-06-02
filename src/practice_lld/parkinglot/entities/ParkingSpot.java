package practice_lld.parkinglot.entities;

import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.Vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private Vehicle vehicle;
    private boolean isOccupied;
    private final VehicleSize spotSize;

    public ParkingSpot(String spotId, VehicleSize spotSize){
        this.spotId=spotId;
        this.spotSize=spotSize;
        this.vehicle=null;
        this.isOccupied =false;
    }
    Vehicle getVehicle(){
        return this.vehicle;
    }

    public String getSpotId() {
        return spotId;
    }

    public synchronized boolean isOccupied() {
        return isOccupied;
    }
    public boolean isAvailable(){
        return !isOccupied;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public void parkVehicle(Vehicle vehicle){
        this.isOccupied = true;
        this.vehicle = vehicle;
    }

    public void unParkVehicle(Vehicle vehicle){
        this.isOccupied = false;
        this.vehicle=null;
    }

    boolean canFitVehicle(Vehicle vehicle){

        if(isOccupied){
            return false;
        }

        return switch (vehicle.getVehicleSize()) {
            case SMALL -> spotSize == VehicleSize.SMALL;
            case MEDIUM -> spotSize == VehicleSize.MEDIUM;
            case LARGE -> spotSize == VehicleSize.LARGE;
            default -> false;
        };

    }
}
