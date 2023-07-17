package GUIPack;

import FlightPack.DepartureLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DepartureGUI extends JPanel {
    private final JLabel DepartureLabel;
    private final JButton backButton;
    private final JButton confirmButton;


    public DepartureGUI(){
        DepartureLabel = new JLabel("Select your Departure Location");
        DepartureLabel.setPreferredSize(new Dimension(800,250));
        DepartureLabel.setSize(new Dimension(800,250));
        DepartureLabel.setFont(new Font("Bold",Font.BOLD,30));
        DepartureLabel.setHorizontalTextPosition(JLabel.CENTER);
        DepartureLabel.setHorizontalAlignment(JLabel.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(new DepartureGUI.BackActionListener());
        confirmButton = new JButton("confirm Departure");


        // switch screen on confirm
        confirmButton.addActionListener(new ActionListener() {  //Geht zum Reservation Screen
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new DestinyLocationPickerGUI()); //Wird benötigt, um ein EDT Error zu umgehen, siehe MyWorker Class
                worker.execute();
            }
        });

        // die DepartureLocation wird hier instanziert
        DepartureLocation departureLocation = new DepartureLocation();

        List<String> departureCities = departureLocation.getDepartureCities();





        // Konvertiere die departureCities in ein Array
        String[] citiesArray = departureCities.toArray(new String[0]);

        //  JComboBox und füge die Departure Cities hinzu
        JComboBox<String> comboBox = new JComboBox<>(citiesArray);


        // DEFAULT
        String defaultValue = "Tokyo"; //  default value
        comboBox.setSelectedItem(defaultValue);
        departureLocation.setSelectedCity(defaultValue);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) comboBox.getSelectedItem();
                departureLocation.setSelectedCity(selectedCity);
                System.out.println("selected departure point:  " + selectedCity);
            }
        });





        add(comboBox);
        add(backButton);
        add(confirmButton);
        add(DepartureLabel);


    }
    // Annahme: Du hast bereits ein GUI-Fenster erstellt und möchtest die Abflugorte anzeigen



// Verwende departureCities, um die Abflugorte in deinem GUI anzuzeigen oder anderweitig zu verwenden

    private class BackActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyWorker worker = new MyWorker(new WelcomeGUI());
            worker.execute();
        }
    }
}
