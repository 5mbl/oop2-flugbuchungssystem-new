package CinemaPack;

import FileIO.FileReaderIO;
import FlightPack.FlightModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Flight {   //Cinema Klasse die Alle Filme speichert und abrufbar macht, muss einmal in Main instanziert werden, alle Methoden sind jedoch statisch
    private static AtomicInteger atomicInteger = new AtomicInteger(10000);  //Gibt jedem Film eine einzigartige ID
    private static AtomicInteger BillIDAtomicInteger = new AtomicInteger(10000);
    public static HashMap<Integer, FlightPack.Flight> IDFlightHashMap = new HashMap<Integer, FlightPack.Flight>();         //HashMap um mit einer ID auf den bestimmten Film zuzugreifen. Wichtigste HashMap, hier sind die Instanzen unserer Filme
    public static HashMap<FlightModel, ArrayList<Integer>> MovieIDsHashMap = new HashMap<>();    //Hier kann man auf alle IDs zugreifen die einem gemeinsamen MovieModel zugeteilt werden können aber unterschiedliche Uhrzeiten haben
    public static HashMap<Location, ArrayList<Integer>> LocationIDsHashMap = new HashMap<>();

    public static Location currentLocation; // Reiseziel



    static {
        for(FlightModel model: FlightModel.values()) {         //HashMap wird mit leeren ArrayLists gefüllt, um Fehler vorzubeugen
            MovieIDsHashMap.put(model,new ArrayList<>());
        }
        for(Location location :Location.values()) {
            LocationIDsHashMap.put(location,new ArrayList<>());
        }
    }
    public static ArrayList<Integer> IDList = new ArrayList<>();    //List aller IDs, hilfreich bei Fehlerbehebungen

    
    public static void addFlight(FlightPack.Flight flight) {      //Hier wird ein schon fertig Instanzierter Film benutzt
        int FlightID = atomicInteger.getAndIncrement();
        IDFlightHashMap.put(FlightID, flight);
        IDList.add(FlightID);
        MovieIDsHashMap.get(flight.getModel()).add(FlightID);
    }

    public static void addFlightWithLocation(FlightPack.Flight flight, Location location) {
        int FlightID = atomicInteger.getAndIncrement();
        IDFlightHashMap.put(FlightID, flight);
        IDList.add(FlightID);
        MovieIDsHashMap.get(flight.getModel()).add(FlightID);
        LocationIDsHashMap.get(location).add(FlightID);
    }

    public static void addAllMoviesFromEveryLocation() {
        for(Location location:Location.values()) {
            for(FlightPack.Flight flight :FileReaderIO.ReadAllMoviesFromLocation(location.getDataSheetPath())) {
                addFlightWithLocation(flight,location);
            }
        }
    }

    //Getter Methoden

    public static FlightPack.Flight get(int FlightID) {
        return IDFlightHashMap.get(FlightID);
    }

    public static int getAmountOfMovies() {
        return FlightModel.values().length;
    }

    public static FlightPack.Flight[] getMovies(FlightModel model) { //Bei dieser Methode gibt man ein MovieModel an und kriegt all Movies die mit diesem Model in der IDFlightHashMap existieren
        Integer[] idList = MovieIDsHashMap.get(model).toArray(new Integer[0]);
        FlightPack.Flight[] flightList = new FlightPack.Flight[idList.length];
        for(int i = 0; i<idList.length; i++) {
            flightList[i] = IDFlightHashMap.get(idList[i]);
        }
        return flightList;
    }

    public static Integer getFlightID(FlightPack.Flight flight) {    //Hier gibt man ein Movie an um an die FlightID zu kommen. Hilfreich falls man auf die richtige Instanz des Films in Cinema zugreifen möchte um Beispielsweise die Reservierung zu ändern
        for(Map.Entry<Integer, FlightPack.Flight> entry:IDFlightHashMap.entrySet()) {
            if(entry.getValue().dateTime == flight.dateTime && entry.getValue().getModel() == flight.getModel()) {    //Vergleicht das Datum und das Model
                return entry.getKey();
            }
        }
        return 0;
    }

    public static FlightPack.Flight[] getMoviesFromCurrentLocation(FlightModel model) {
        Integer[] idList = MovieIDsHashMap.get(model).toArray(new Integer[0]);
        ArrayList<FlightPack.Flight> flightList = new ArrayList<>();
        for(int i = 0; i<idList.length; i++) {
            if(LocationIDsHashMap.get(currentLocation).contains(idList[i])) {
                flightList.add(IDFlightHashMap.get(idList[i]));
            }
        }
        return flightList.toArray(new FlightPack.Flight[0]);
    }

    public static void changeCurrentLocation(Location location) {
        currentLocation = location;
    }

    public static int getBillID() {
        return BillIDAtomicInteger.incrementAndGet();
    }
}
