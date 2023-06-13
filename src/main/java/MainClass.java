import CinemaPack.Flight;
import GUIPack.GUIUpdater;

public class  MainClass {
    public static void main(String[] args) {
        Flight flight = new Flight();                      //Cinema muss einmal instanziert werden um auf die statischen Methoden zuzugreifen
        Flight.addAllMoviesFromEveryLocation();            //Die Filme vom DataSheet werden geladen
        GUIUpdater guiUpdater = new GUIUpdater();          //Das gleiche wie bei Cinema, hiermit wird auch unser JFrame geöffnet
    }
}