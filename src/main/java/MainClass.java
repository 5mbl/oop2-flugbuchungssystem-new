import FlightPack.Airline;
import GUIPack.GUIUpdater;

public class  MainClass {
    public static void main(String[] args) {
        Airline airline = new Airline();                      //Cinema muss einmal instanziert werden um auf die statischen Methoden zuzugreifen
        Airline.addAllFlightsFromEveryLocation();            //Die Filme vom DataSheet werden geladen
        GUIUpdater guiUpdater = new GUIUpdater();          //Das gleiche wie bei Cinema, hiermit wird auch unser JFrame geöffnet
    }
}