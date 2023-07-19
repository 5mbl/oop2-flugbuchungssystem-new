package GUIPack;

import FlightPack.FlightModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AirlineSelectGUI extends JPanel {
    private final ArrayList<JButton> buttonList = new ArrayList<>();
    private final JButton backButton;
    private final JPanel masterPanel;

    public AirlineSelectGUI() {
        setLayout(new BorderLayout());
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridBagLayout());

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new DestinyLocationPickerGUI());
                worker.execute();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints(); //wird verwendet, um anzugeben, wie eine Komponente innerhalb eines GridBag-Layout positioniert und gestreckt werden soll.
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0); //Fügt einen Abstand von 10 Pixeln oben hinzu
        masterPanel.add(backButton, gbc);

        int gridY = 1;
        for (FlightModel model : FlightModel.values()) {  // Iteriere über jede FlightModel-Wert
            JButton button = new JButton(model.getName());  // Erstellt einen neuen JButton mit dem Namen des FlightModel
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyWorker worker = new MyWorker(new TimeslotSelectGUI(model));
                    worker.execute();
                }
            });
            buttonList.add(button);
            gbc.gridy = gridY++;
            masterPanel.add(button, gbc);
        }

        for (JButton button : buttonList) {
            button.setPreferredSize(new Dimension(200, 50));
        }
        backButton.setPreferredSize(new Dimension(200, 50)); // Anpassung der Back-Button-Größe


        add(masterPanel, BorderLayout.CENTER);

        revalidate();
    }
}
