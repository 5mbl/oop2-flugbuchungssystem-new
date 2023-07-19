package GUIPack;

import FlightPack.Airline;
import FlightPack.Flight;
import FlightPack.FlightModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimeslotSelect extends JPanel {
    private final ArrayList<JButton> buttonList = new ArrayList<>();
    private final JButton backButton;

    private final JPanel masterPanel;

    public TimeslotSelect(FlightModel model) {
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridBagLayout()); // Verwende ein GridBagLayout für das Master-Panel um die Buttonposition zu bestimmen

        // Erzeuge GridBagConstraints-Objekt
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Setzt die grid x-Position auf 0 (erste Spalte)
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0); // Setze den Abstand von 10 Pixeln oben

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new AirlineSelectGUI());
                worker.execute();
            }
        });

        // Füge den backButton mit den GridBagConstraints zum masterPanel hinzu
        masterPanel.add(backButton, gbc);

        int gridY = 1; // Starte mit gridY=1 für die nächste Zeile der Buttons

        for (Flight flight : Airline.getFlightFromCurrentLocation(model)) {
            JButton button = new JButton(flight.getTimeString());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyWorker worker = new MyWorker(new FlightDescriptionGUI(Airline.getFlightID(flight)));
                    worker.execute();
                }
            });
            buttonList.add(button);

            gbc.gridy = gridY++; // Aktualisiere die grid y-Position für die nächste Zeile der Buttons

            // Füge den button mit den GridBagConstraints zum masterPanel hinzu
            masterPanel.add(button, gbc);
        }

        // Setze alle Buttons auf die gleiche Größe
        Dimension buttonSize = new Dimension(200, 100 / FlightModel.values().length);
        for (JButton button : buttonList) {
            button.setPreferredSize(buttonSize);
        }
        backButton.setPreferredSize(new Dimension(200, 32)); // Anpassung der Back-Button-Größe

        add(masterPanel);

        revalidate();
    }
}
