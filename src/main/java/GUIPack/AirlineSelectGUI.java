package GUIPack;

import FlightPack.FlightModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AirlineSelectGUI extends JPanel {                            //StartPanel also kein backButton
    private final ArrayList<JButton> buttonList = new ArrayList<>();
    private final JButton backButton;
    private final JPanel MasterPanel;

    public AirlineSelectGUI() {
        MasterPanel = new JPanel();
        MasterPanel.setLayout(new GridLayout(FlightModel.values().length, 1));

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new DestinyLocationPickerGUI());
                worker.execute();
            }
        });

        MasterPanel.add(backButton);

        for(FlightModel model: FlightModel.values()) {                     //Für alle unterschiedlichen Filme in MovieModel wird ein Button erzeugt
            JButton button = new JButton(model.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyWorker worker = new MyWorker(new TimeslotSelect(model)); //Wird benötigt, um ein EDT Error zu umgehen, siehe MyWorker Class
                    worker.execute();
                }
            });
            buttonList.add(button); // alle film buttons werden in buttonlist hinzugefügt
        }


        for(JButton button:buttonList) { // buttonlist wird geloopt und im Jpanel angezeigt
            button.setSize(800/5,350/ FlightModel.values().length);
            MasterPanel.add(button);
        }

        add(MasterPanel);

        revalidate();
    }
}
