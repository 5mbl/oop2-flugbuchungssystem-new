package GUIPack;

import FlightPack.Airline;
import FlightPack.Flight;
import FlightPack.FlightModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimeslotSelect extends JPanel {                                                             //Hier wählt man aus an welchem Datum, man den Film schauen will
    private final ArrayList<JButton> buttonList = new ArrayList<>();                                    //buttonList für alle verschiedenen Daten die in Cinema zur verfügung stehen
    private final JButton backButton;

    private final JPanel MasterPanel;
    public TimeslotSelect(FlightModel Model) {
        MasterPanel = new JPanel();
        MasterPanel.setLayout(new GridLayout(FlightModel.values().length, 1));

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {                                             //Um auf die vorherige Seite zurückzukehren
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new AirlineSelectGUI());                                   //Wird benötigt um ein EDT Error zu umgehen siehe MyWorker Class
                worker.execute();
            }
        });

        MasterPanel.add(backButton);

        for(Flight flight : Airline.getFlightFromCurrentLocation(Model)) {                                                     //Sucht jeden Movie mit dem gleichen Model in Cinema um die verschiedenen Daten zu kriegen
            JButton button = new JButton(flight.getTimeString()); // getTime String
            button.addActionListener(new ActionListener() {                                             //ActionListener geht zur nächsten Seite
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyWorker worker = new MyWorker(new FlightDescriptionGUI(Airline.getFlightID(flight))); //Wird benötigt um ein EDT Error zu umgehen siehe MyWorker Class
                    worker.execute();
                }
            });
            buttonList.add(button);
        }

        for(JButton button:buttonList) {
            button.setSize(1280/5,1080/ FlightModel.values().length);                         //Buttons werden gesized
            MasterPanel.add(button);                                                                    //Alle buttons werden zum Panel geadded
        }

        add(MasterPanel);

        revalidate();
    }



}
