package FileIO;

import CinemaPack.Location;
import MoviePack.Movie;
import MoviePack.MovieModel;
import com.opencsv.CSVWriter;
import org.joda.time.LocalDateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileWriterIO {   //Mit dieser Klasse kann man mit der writeRandomMovieWithDates() das MovieDataTimeSheet füllen

    private static final String[] LocationPaths = new String[Location.values().length];     // Path des DataSheets
    static {
        for(int i = 0; i<LocationPaths.length; i++) {       //Paths werden durchgegeben
            LocationPaths[i] = Location.values()[i].getDataSheetPath();
        }
    }

    private static void MovieWriter(MovieModel Model, int[] timeArray,String path){  //Methode um einen Film zu schreiben
        CSVWriter writer = null;    //verwendet Maven dependency openCSV für leichteres arbeiten mit csv Dateien
        try{
            writer = new CSVWriter(new FileWriter(path,true),   //CSVWriter wird erstellt mit argumenten die Fehler vorbeugen
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
            String[] values = new String[6];
            values[0] = String.valueOf(Model.getID());      //ints werden zu Strings umgewandelt
            for(int i = 0; i<timeArray.length; i++) {
                values[i+1] = String.valueOf(timeArray[i]);
            }
            writer.writeNext(values);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeRandomMovieWithDates() {    //verwende diese Methode, wenn du einen neuen Film in der MovieModel Klasse hinzugefügt hast
        for(String path:LocationPaths) {
            try {
                CSVWriter write = new CSVWriter(new FileWriter(path, false));    //Sollte des DataSheet clearen, noch nicht getestet, sonst bleiben die alten Daten da
                write.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MovieModel[] movieModels = MovieModel.values();
            Random random = new Random();
            for (MovieModel model : movieModels) {                             //Für jedes MovieModel werden random Movies erstellt
                for (int i = 0; i < random.nextInt(1, 10); i++) {    //Zwischen 1 und 10 mal wird ein Film mit dem Model erstellt
                    int year = 2023;                                        //Standard Jahr
                    int month = random.nextInt((12 - 1) + 1) + 1;   //Random Monat zwischen 12 und 1
                    int day = random.nextInt((28 - 1) + 1) + 1;     //Random Datum zwischen 28 und 1, 28 um potenzielle Fehler zu vermeiden
                    int hour = random.nextInt((22 - 7) + 1) + 7;    //Stunde zwischen 22 und 7
                    int[] array = new int[10];                            //Array mit Minuten im fünfer Takt für realismus
                    int count = 5;
                    for (int j = 0; j < array.length; j++) {                 //Array wird gefüllt
                        array[j] = count;
                        count += 5;
                    }
                    int minute = array[random.nextInt(array.length)];   //zufällige Minute wird aus dem Array genommen
                    MovieWriter(model, new int[]{year, month, day, hour, minute},path);  //Daten werden auf das DataSheet geschrieben
                }
            }
        }
    }
}
