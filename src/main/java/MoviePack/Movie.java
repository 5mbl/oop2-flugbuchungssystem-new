package MoviePack;

import org.joda.time.LocalDateTime;                                             //Joda time ist ein Maven plugin um Daten und Uhrzeiten leichter zu verwalten
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Movie {
    private final MovieModel Model;                                             //Dieses Model bestimmt den Film
    public final LocalDateTime dateTime;                                        //Datum und Uhrzeit des Films
    public final Seat[] seatList;                                               //Seat Liste dieses Films

    public Movie(MovieModel Model, LocalDateTime dateTime) {                    //Model und Datum wird angegeben
        this.Model = Model;
        this.dateTime = dateTime;
        seatList = new Seat[30];
        for(int i = 0; i<seatList.length; i++) {                                //seatList wird gefüllt
            seatList[i] = new Seat();
        }
    }

    //Getter Methoden um auf die Values des Films zuzugreifen

    public String getName() {
        return Model.getName();
    }

    public String getDescription() {
        return Model.getDescription();
    }

    public String getImagePath() {
        return Model.getImagePath();
    }

    public MovieModel getModel() {
        return Model;
    }

    //Datum wird formatiert um lesbar zu sein

    public String getTimeString() { // returnt das datum an dem der flug startet
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm");
        return dtf.print(dateTime);
    }

    public void reserveSeat(int Index) {
        seatList[Index].reserveSeat();
    }

    public double getPrice() {
        return Model.getPrice();
    }
}
