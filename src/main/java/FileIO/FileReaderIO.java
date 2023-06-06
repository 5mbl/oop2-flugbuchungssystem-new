
package FileIO;

import MoviePack.Movie;
import MoviePack.MovieModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.joda.time.LocalDateTime;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderIO {   //Mit dieser Klasse werden die csv Dateien der Locations gelesen
    public static ArrayList<Movie> ReadAllMovies() {    //Alte Methode mit nur einer csv Datei ohne unterschiedliche Locations
        CSVReader reader = null;    //CSVReader von openCSV maven Dependency (siehe pom.xml Datei)
        String path = "src/main/resources/MovieDateTimeSheet.csv";
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {
                    int ID = Integer.parseInt(line[0]);
                    LocalDateTime dateTime = new LocalDateTime(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line[5]));  //Hier wird jodaTime verwendet (siehe pom.xml)
                    movieArrayList.add(new Movie(MovieModel.getEnum(ID),dateTime));
                }
                return (movieArrayList);
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Movie> ReadAllMoviesFromLocation(String locationPath) {    //Liest jede CSV Datei
        CSVReader reader = null;    //CSVReader von openCSV maven Dependency (siehe pom.xml Datei)
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(locationPath));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {
                    int ID = Integer.parseInt(line[0]);
                    LocalDateTime dateTime = new LocalDateTime(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line[5]));  //Hier wird jodaTime verwendet (siehe pom.xml)
                    movieArrayList.add(new Movie(MovieModel.getEnum(ID),dateTime));
                }
                return (movieArrayList);
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
