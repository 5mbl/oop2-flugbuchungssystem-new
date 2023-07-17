package FlightPack;

import FileIO.FileReaderIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Airline {   //Airline Klasse die Alle Flüge speichert und abrufbar macht, muss einmal in Main instanziert werden, alle Methoden sind jedoch statisch
    private static AtomicInteger atomicInteger = new AtomicInteger(10000);  //Gibt jedem Flug eine einzigartige ID
    private static AtomicInteger BillIDAtomicInteger = new AtomicInteger(10000);
    public static HashMap<Integer, FlightPack.Flight> IDFlightHashMap = new HashMap<Integer, FlightPack.Flight>();         //HashMap um mit einer ID auf den bestimmten Flug zuzugreifen. Wichtigste HashMap, hier sind die Instanzen unserer Flüge
    public static HashMap<FlightModel, ArrayList<Integer>> FlightIDsHashMap = new HashMap<>();    //Hier kann man auf alle IDs zugreifen die einem gemeinsamen MovieModel zugeteilt werden können aber unterschiedliche Uhrzeiten haben
    public static HashMap<Destination, ArrayList<Integer>> LocationIDsHashMap = new HashMap<>();

    public static Destination currentDestination; // Reiseziel



    static {
        for(FlightModel model: FlightModel.values()) {         //HashMap wird mit leeren ArrayLists gefüllt, um Fehler vorzubeugen
            FlightIDsHashMap.put(model,new ArrayList<>());
        }
        for(Destination destination : Destination.values()) {
            LocationIDsHashMap.put(destination,new ArrayList<>());
        }
    }
    public static ArrayList<Integer> IDList = new ArrayList<>();    //List aller IDs, hilfreich bei Fehlerbehebungen

    
    public static void addFlight(FlightPack.Flight flight) {      //Hier wird ein schon fertig Instanzierter Flug benutzt
        int FlightID = atomicInteger.getAndIncrement();
        IDFlightHashMap.put(FlightID, flight);
        IDList.add(FlightID);
        FlightIDsHashMap.get(flight.getModel()).add(FlightID);
    }

    public static void addAllFlightsFromEveryLocation() {
        for(Destination destination : Destination.values()) {
            for(FlightPack.Flight flight :FileReaderIO.ReadAllFlightsFromLocation(destination.getDataSheetPath())) {
                addFlightWithLocation(flight, destination);
            }
        }
    }

    public static void addFlightWithLocation(FlightPack.Flight flight, Destination destination) {
        int FlightID = atomicInteger.getAndIncrement();
        IDFlightHashMap.put(FlightID, flight);
        IDList.add(FlightID);
        FlightIDsHashMap.get(flight.getModel()).add(FlightID);
        LocationIDsHashMap.get(destination).add(FlightID);
    }


    // getter-Methods
    public static FlightPack.Flight get(int FlightID) {
        return IDFlightHashMap.get(FlightID);
    }

    public static Integer getFlightID(FlightPack.Flight flight) {    //Hier gibt man ein Flug an um an die FlightID zu kommen. Hilfreich falls man auf die richtige Instanz des Flugs in Cinema zugreifen möchte um Beispielsweise die Reservierung zu ändern
        for(Map.Entry<Integer, FlightPack.Flight> entry:IDFlightHashMap.entrySet()) {
            if(entry.getValue().dateTime == flight.dateTime && entry.getValue().getModel() == flight.getModel()) {    //Vergleicht das Datum und das Model
                return entry.getKey();
            }
        }
        return 0;
    }

    public static FlightPack.Flight[] getFlightFromCurrentLocation(FlightModel model) {
        Integer[] idList = FlightIDsHashMap.get(model).toArray(new Integer[0]);
        ArrayList<FlightPack.Flight> flightList = new ArrayList<>();
        for(int i = 0; i<idList.length; i++) {
            if(LocationIDsHashMap.get(currentDestination).contains(idList[i])) {
                flightList.add(IDFlightHashMap.get(idList[i]));
            }
        }
        return flightList.toArray(new FlightPack.Flight[0]);
    }

    public static void changeCurrentLocation(Destination destination) {
        currentDestination = destination;
    }

    public static int getBillID() {
        return BillIDAtomicInteger.incrementAndGet();
    }
}
