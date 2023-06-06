package CinemaPack;

import FileIO.FileReaderIO;
import MoviePack.Movie;
import MoviePack.MovieModel;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Cinema {   //Cinema Klasse die Alle Filme speichert und abrufbar macht, muss einmal in Main instanziert werden, alle Methoden sind jedoch statisch
    private static AtomicInteger atomicInteger = new AtomicInteger(10000);  //Gibt jedem Film eine einzigartige ID
    private static AtomicInteger BillIDAtomicInteger = new AtomicInteger(10000);
    public static HashMap<Integer, Movie> IDMovieHashMap = new HashMap<Integer, Movie>();         //HashMap um mit einer ID auf den bestimmten Film zuzugreifen. Wichtigste HashMap, hier sind die Instanzen unserer Filme
    public static HashMap<MovieModel, ArrayList<Integer>> MovieIDsHashMap = new HashMap<>();    //Hier kann man auf alle IDs zugreifen die einem gemeinsamen MovieModel zugeteilt werden können aber unterschiedliche Uhrzeiten haben
    public static HashMap<Location, ArrayList<Integer>> LocationIDsHashMap = new HashMap<>();

    public static Location currentLocation;

    static {
        for(MovieModel model:MovieModel.values()) {         //HashMap wird mit leeren ArrayLists gefüllt, um Fehler vorzubeugen
            MovieIDsHashMap.put(model,new ArrayList<>());
        }
        for(Location location :Location.values()) {
            LocationIDsHashMap.put(location,new ArrayList<>());
        }
    }
    public static ArrayList<Integer> IDList = new ArrayList<>();    //List aller IDs, hilfreich bei Fehlerbehebungen


    /* kann gelöcht werden
    public static void addMovie(int MovieID, LocalDateTime dateTime) {  //Methode wird nicht genutzt, zur Fehlerbehebung da wenn man schnell einen Film testen will und ihn Manuel hinzufügt
        Movie movie = new Movie(MovieModel.getEnum(MovieID), dateTime);
        int CinemaID = atomicInteger.getAndIncrement();     //cinemaID des Films wird bestimmt
        IDMovieHashMap.put(CinemaID,movie);     //cinemaID mit dem Film kommen in die HashMaps
        IDList.add(CinemaID);
        MovieIDsHashMap.get(MovieModel.getEnum(MovieID)).add(CinemaID);
    } */

    public static void addMovie(Movie movie) {      //Hier wird ein schon fertig Instanzierter Film benutzt
        int CinemaID = atomicInteger.getAndIncrement();
        IDMovieHashMap.put(CinemaID,movie);
        IDList.add(CinemaID);
        MovieIDsHashMap.get(movie.getModel()).add(CinemaID);
    }

    public static void addMovieWithLocation(Movie movie,Location location) {
        int CinemaID = atomicInteger.getAndIncrement();
        IDMovieHashMap.put(CinemaID,movie);
        IDList.add(CinemaID);
        MovieIDsHashMap.get(movie.getModel()).add(CinemaID);
        LocationIDsHashMap.get(location).add(CinemaID);
    }

    public static void addAllMoviesFromSheet() {    //Alte Methode wo es nur eine csv Datei gab
        for(Movie movie : FileReaderIO.ReadAllMovies()) {   //Alle Filme werden gelesen
            addMovie(movie);
        }
    }

    public static void addAllMoviesFromEveryLocation() {
        for(Location location:Location.values()) {
            for(Movie movie:FileReaderIO.ReadAllMoviesFromLocation(location.getDataSheetPath())) {
                addMovieWithLocation(movie,location);
            }
        }
    }

    //Getter Methoden

    public static Movie get(int cinemaID) {
        return IDMovieHashMap.get(cinemaID);
    }

    public static int getAmountOfMovies() {
        return MovieModel.values().length;
    }

    public static Movie[] getMovies(MovieModel model) { //Bei dieser Methode gibt man ein MovieModel an und kriegt all Movies die mit diesem Model in der IDMovieHashMap existieren
        Integer[] idList = MovieIDsHashMap.get(model).toArray(new Integer[0]);
        Movie[] movieList = new Movie[idList.length];
        for(int i = 0; i<idList.length; i++) {
            movieList[i] = IDMovieHashMap.get(idList[i]);
        }
        return movieList;
    }

    public static Integer getCinemaID(Movie movie) {    //Hier gibt man ein Movie an um an die cinemaID zu kommen. Hilfreich falls man auf die richtige Instanz des Films in Cinema zugreifen möchte um Beispielsweise die Reservierung zu ändern
        for(Map.Entry<Integer, Movie> entry:IDMovieHashMap.entrySet()) {
            if(entry.getValue().dateTime == movie.dateTime && entry.getValue().getModel() == movie.getModel()) {    //Vergleicht das Datum und das Model
                return entry.getKey();
            }
        }
        return 0;
    }

    public static Movie[] getMoviesFromCurrentLocation(MovieModel model) {
        Integer[] idList = MovieIDsHashMap.get(model).toArray(new Integer[0]);
        ArrayList<Movie> movieList = new ArrayList<>();
        for(int i = 0; i<idList.length; i++) {
            if(LocationIDsHashMap.get(currentLocation).contains(idList[i])) {
                movieList.add(IDMovieHashMap.get(idList[i]));
            }
        }
        return movieList.toArray(new Movie[0]);
    }

    public static void changeCurrentLocation(Location location) {
        currentLocation = location;
    }

    public static int getBillID() {
        return BillIDAtomicInteger.incrementAndGet();
    }
}
