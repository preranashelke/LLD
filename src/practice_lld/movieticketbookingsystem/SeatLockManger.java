package practice_lld.movieticketbookingsystem;

import practice_lld.movieticketbookingsystem.entities.Seat;
import practice_lld.movieticketbookingsystem.entities.Show;
import practice_lld.movieticketbookingsystem.enums.SeatStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SeatLockManger {
    private final Map<Show, Map<Seat, String>> lockedSeats = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final long LOCK_TIMEOUT = 500;

    public void lockSeats(Show show, String userId, List<Seat> seats) {
        synchronized (show) {
            for (Seat seat : seats) {
                if (seat.getStatus() != SeatStatus.AVAILABLE) {
                    System.out.println("Seat " + seat.getId() + " is not available.");
                    return;
                }
            }
            for (Seat seat : seats) {
                seat.setStatus(SeatStatus.LOCKED);
            }

            lockedSeats.computeIfAbsent(show, k -> new ConcurrentHashMap<>());
            for (Seat seat : seats) {
                lockedSeats.get(show).put(seat, userId);
            }
            scheduler.schedule(() -> unlockedSeats(show, seats, userId), LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
            System.out.println("Locked seats: " + seats.stream().map(seat -> seat.getId()).toList() + "for user: " + userId);

        }
    }

    public  void unlockedSeats(Show show, List<Seat> seats, String userId){
        synchronized (show){
            Map<Seat, String> showLocks= lockedSeats.get(show);
            if(showLocks!=null){
                for(Seat seat:seats){
                    if(showLocks.containsKey(seat)&&showLocks.get(seat).equals(userId)){
                        showLocks.remove(seat);
                        if(seat.getStatus() == SeatStatus.LOCKED){
                            seat.setStatus(SeatStatus.AVAILABLE);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to timeout.");
                        }else {
                            showLocks.remove(seat);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to booking completion.");
                        }
                    }
                }
                if(showLocks.isEmpty()){
                    lockedSeats.remove(show);
                }
            }
        }
    }
    public void shutdown(){
        System.out.println("shutting down seatLockerProvider scheduler");
        scheduler.shutdown();
        try {
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
        } catch ( InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}


