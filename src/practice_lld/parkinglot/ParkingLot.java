package practice_lld.parkinglot;

import practice_lld.parkinglot.Strategy.fee.FeeStrategy;
import practice_lld.parkinglot.Strategy.fee.FlatRateFeeStrategy;
import practice_lld.parkinglot.Strategy.parking.BestFitParkingSpot;
import practice_lld.parkinglot.Strategy.parking.ParkingSpotStrategy;
import practice_lld.parkinglot.Vehicle.Vehicle;
import practice_lld.parkinglot.entities.ParkingFloor;
import practice_lld.parkinglot.entities.ParkingSpot;
import practice_lld.parkinglot.entities.ParkingTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();
    private ParkingSpotStrategy parkingSpotStrategy;
    private FeeStrategy feeStrategy;
    private final Map<String, ParkingTicket> activeTickets;

    private ParkingLot(){
        activeTickets = new ConcurrentHashMap<>();
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingSpotStrategy =  new BestFitParkingSpot();
    }

    void addFloor(ParkingFloor floor){
        parkingFloors.add(floor);
    }

    public static synchronized ParkingLot getInstance(){
        if(instance == null){
            instance =  new ParkingLot();
        }
        return  instance;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy = feeStrategy;
    }

    public void setParkingSpotStrategy(ParkingSpotStrategy parkingSpotStrategy){
        this.parkingSpotStrategy = parkingSpotStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle){
        //get parking spot
        Optional<ParkingSpot> parkingSpot = parkingSpotStrategy.getParkingSpot(vehicle, parkingFloors);

        if(parkingSpot.isPresent()){
            ParkingSpot spot = parkingSpot.get();
            spot.parkVehicle(vehicle);

            //create parking ticket
            ParkingTicket parkingTicket = new ParkingTicket(spot, vehicle);
            activeTickets.put(vehicle.getLicenseNumber(), parkingTicket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(), spot.getSpotId(), parkingTicket.getTicketId());
            return Optional.of(parkingTicket);
        }
        System.out.println("No Spot is available for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unParkVehicle(Vehicle vehicle){

        ParkingTicket ticket = activeTickets.remove(vehicle.getLicenseNumber());

        if (ticket == null){
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExistTimesStamp();
        ParkingSpot parkingSpot = ticket.getSpot();
        parkingSpot.unParkVehicle(vehicle);

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);

    }


}
