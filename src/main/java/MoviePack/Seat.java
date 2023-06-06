package MoviePack;

public class Seat {
    public boolean isReserved;
    public Seat() {
        isReserved = false;
    }
    public void changeReservationStatus() {     //ändert isReserved zum Gegenteil
        isReserved = !isReserved;
    }
    public void reserveSeat() {
        isReserved = true;
    }
}
