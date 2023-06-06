package GUIPack;

import javax.swing.*;
import java.awt.*;

public class GUIUpdater {       //Hier wird unser GUI Frame instanziert um auf ihn zugreifen zu können
    public static GUI MainFrame;    //GUI Instanz

    public static Dimension SmallerDimension = new Dimension(850,400);
    public static Dimension StandardDimension = new Dimension(1280,1080);

    public GUIUpdater() {
        MainFrame = new GUI();
    }   //GUI wird geöffnet, sobald GUIUpdater instanziert wird wie in Main

    public static void clearRootPanel() {           //GUI Frame wird gecleared
        MainFrame.getContentPane().removeAll();
        MainFrame.revalidate();
    }

    public static void updateFrame(JPanel Content) {    //Neues JPanel wird als ContentPane gesetzt
        if(Content.getClass() != MovieDescriptionGUI.class) {    //Wenn das Panel eine ReservationGUI ist, werden die Dimensionen des Frames geändert
            if(Content.getClass() == CheckoutGUI.class) {
                MainFrame.setPreferredSize(new Dimension(650,600));
                MainFrame.setSize(new Dimension(650,600));
            } else {
                MainFrame.setPreferredSize(SmallerDimension);
                MainFrame.setSize(SmallerDimension);
            }

        } else {
            MainFrame.setPreferredSize(StandardDimension);
            MainFrame.setSize(StandardDimension);
        }
        MainFrame.setContentPane(Content);
        MainFrame.validate();
    }

    public static void showDialog(String message) {
        JOptionPane.showMessageDialog(MainFrame,message);
    }
}
