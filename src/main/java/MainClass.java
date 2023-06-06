import CinemaPack.Cinema;
import CinemaPack.Location;
import FileIO.FileWriterIO;
import GUIPack.GUIUpdater;
import MoviePack.Movie;
import MoviePack.MovieModel;

public class  MainClass {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();                      //Cinema muss einmal instanziert werden um auf die statischen Methoden zuzugreifen
        Cinema.addAllMoviesFromEveryLocation();            //Die Filme vom DataSheet werden geladen
        GUIUpdater guiUpdater = new GUIUpdater();          //Das gleiche wie bei Cinema, hiermit wird auch unser JFrame geöffnet
    }
}