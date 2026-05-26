package practice_lld.movieticketbookingsystem.entities;


import practice_lld.movieticketbookingsystem.enums.SeatStatus;
import practice_lld.movieticketbookingsystem.enums.SeatType;

public class Seat {
    private final String id;
    private final SeatType type;
    private final int col;
    private final int row;
    private SeatStatus status;

    public Seat(String id, SeatType type, int col, int row) {
        this.id = id;
        this.type = type;
        this.col = col;
        this.row = row;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public SeatType getType() {
        return type;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status){
        this.status=status;
    }

}
