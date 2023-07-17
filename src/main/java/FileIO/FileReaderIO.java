package FileIO;

import FlightPack.Flight;
import FlightPack.FlightModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.joda.time.LocalDateTime;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderIO {   //Mit dieser Klasse werden die csv Dateien der Locations gelesen


    public static ArrayList<Flight> ReadAllFlightsFromLocation(String locationPath) {    //Liest jede CSV Datei
        CSVReader reader = null;
        ArrayList<Flight> flightArrayList = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(locationPath));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {
                    int ID = Integer.parseInt(line[0]);
                    LocalDateTime dateTime = new LocalDateTime(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line[5]));  //Hier wird jodaTime verwendet (siehe pom.xml)
                    flightArrayList.add(new Flight(FlightModel.getEnum(ID),dateTime));
                }
                return (flightArrayList);
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
