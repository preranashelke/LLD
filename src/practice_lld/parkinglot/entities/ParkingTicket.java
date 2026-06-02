package practice_lld.parkinglot.entities;

import practice_lld.parkinglot.Vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final Vehicle vehicle;
    private final long entryTimeStamp;
    private long existTimesStamp;


    public ParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle){
        this.ticketId = UUID.randomUUID().toString();
        this.spot = parkingSpot;
        this.vehicle = vehicle;
        this.entryTimeStamp = new Date().getTime();
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public long getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public long getExistTimesStamp() {
        return existTimesStamp;
    }
    public void setExistTimesStamp(){
        this.existTimesStamp = new Date().getTime();
    }
}
