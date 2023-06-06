package GUIPack;

import MoviePack.MovieModel;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI extends JFrame {   //Das ist unser MainFrame
    public GUI() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Program endet wenn das Fenster geschlossen wird
        setSize(800,350);   //Size für den WelcomeScreen

        setContentPane(new WelcomeGUI());  //Standard Panel -> Willkommensbildschirm als erstes angezeigt wird.
        pack();
    }

    /* Löchen
    public static JPanel getPanel(int ID, int cinemaID, MovieModel model) { //Methode hab ich doch nicht gebraucht
        switch(ID) {
            case 0 -> new MoviePickerGUI();
            case 1 -> new TimePickerGUI(model);
            case 2 -> new MovieDescriptionGUI(cinemaID);
            default -> new ReservationGUI(cinemaID);
        }
        return null;
    } */
}
