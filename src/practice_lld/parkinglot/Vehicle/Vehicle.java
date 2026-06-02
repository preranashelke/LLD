package practice_lld.parkinglot.Vehicle;

public abstract class Vehicle {

    private final VehicleSize vehicleSize;
    private final String licenceNumber;

    public Vehicle(String licenceNumber, VehicleSize vehicleSize){
        this.vehicleSize = vehicleSize;
        this.licenceNumber = licenceNumber;
    }

    public VehicleSize getVehicleSize(){
        return vehicleSize;
    }

    public String getLicenseNumber() { return licenceNumber; }
}
