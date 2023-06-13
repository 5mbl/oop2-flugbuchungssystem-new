package FlightPack;

import java.util.ArrayList;
import java.util.List;

public class DepartureLocation {
    private static String selectedCity;
    private List<String> departureCities;

    //private String selectedCity;

    public DepartureLocation() {
        departureCities = new ArrayList<>();
        initializeDepartureCities();
    }

    private void initializeDepartureCities() {
        // Hinzufügen der verschiedenen Abflugorte
        departureCities.add("New York");
        departureCities.add("London");
        departureCities.add("Tokyo");
        departureCities.add("Paris");
        departureCities.add("Berlin");
        // Weitere Abflugorte können hier hinzugefügt werden
    }

    public List<String> getDepartureCities() {
        return departureCities;
    }

    // die ausgewählte city aus der combobox wird in selectedCity gespeichert (wird auch im Payment für die Bezahlübersicht wieder herangezogen)
    public void setSelectedCity(String departureCity) {
        this.selectedCity = departureCity;
    }

    public static String getSelectedCity() {
        return selectedCity;
    }
}
