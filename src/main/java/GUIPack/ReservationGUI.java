package GUIPack;

import FlightPack.Airline;
import FlightPack.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationGUI extends JPanel {
    private final JPanel leftPanel;
    private final JPanel midPanel;
    private final JPanel rightPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;

    private final ArrayList<JCheckBox> checkBoxArrayList = new ArrayList<>();

    private final JButton confirm;
    private final JButton continueButton;
    private final JButton backButton;

    private final JLabel screenLabel;
    private final Flight selectedFlight;
    private boolean foundSelectedCheckbox;

    public ReservationGUI(int airlineID) {  //Hier kann man reservieren
        ArrayList<Integer> reservedSeats = new ArrayList<>();

        selectedFlight = Airline.IDFlightHashMap.get(airlineID);

        leftPanel = new JPanel();
        midPanel = new JPanel();
        rightPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        leftPanel.setLayout(new GridLayout(2,5));
        midPanel.setLayout(new GridLayout(2,5));
        rightPanel.setLayout(new GridLayout(2,5));
        bottomPanel.setLayout(new FlowLayout());

        for(int j = 0; j< selectedFlight.seatList.length; j++) {                                              //Loop um Checkboxes zu erstellen
            JCheckBox checkBox = new JCheckBox(String.valueOf(j));
            //checkBox.setSelectedIcon(new ImageIcon("src/main/java/GUIPack/Images/Selected.png"));   //Icons werden festgelegt
            //checkBox.setIcon(new ImageIcon("src/main/java/GUIPack/Images/Unselected.png"));
            if(selectedFlight.seatList[j].isReserved) {                                                      //Wenn Sitz schon reserviert ist, wird die CheckBox disabled
                checkBox.setEnabled(false);
            }
            checkBoxArrayList.add(checkBox);
        }

        for(int i = 0; i<10; i++) {                     //Die Checkboxes werden zu den Panels hinzugefügt
            leftPanel.add(checkBoxArrayList.get(i));
            midPanel.add(checkBoxArrayList.get(i+10));
            rightPanel.add(checkBoxArrayList.get(i+20));
        }

        confirm = new JButton("Confirm");
        continueButton = new JButton("Continue");
        backButton = new JButton("Back");
        confirm.addActionListener(new ActionListener() {        //Hier werden alle Checkboxes überprüft und wenn sie gecheckt sind, werden sie reserviert
            @Override
            public void actionPerformed(ActionEvent e) {
                foundSelectedCheckbox = false;
                for(Component checkbox:leftPanel.getComponents()) {
                    if(checkbox.getClass() == JCheckBox.class) {
                        if (((JCheckBox) checkbox).isSelected()) {
                            Airline.IDFlightHashMap.get(airlineID).reserveSeat(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            checkbox.setEnabled(false);
                            reservedSeats.add(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            foundSelectedCheckbox = true;
                        }
                    }
                }
                for(Component checkbox:midPanel.getComponents()) {
                    if(checkbox.getClass() == JCheckBox.class) {
                        if (((JCheckBox) checkbox).isSelected()) {
                            Airline.IDFlightHashMap.get(airlineID).reserveSeat(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            checkbox.setEnabled(false);
                            reservedSeats.add(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            foundSelectedCheckbox = true;
                        }
                    }
                }
                for(Component checkbox:rightPanel.getComponents()) {
                    if(checkbox.getClass() == JCheckBox.class) {
                        if(((JCheckBox) checkbox).isSelected()) {
                            Airline.IDFlightHashMap.get(airlineID).reserveSeat(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            checkbox.setEnabled(false);
                            reservedSeats.add(Integer.parseInt(((JCheckBox) checkbox).getLabel()));
                            foundSelectedCheckbox = true;
                        }
                    }
                }
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(foundSelectedCheckbox) {
                    MyWorker worker = new MyWorker(new PaymentGUI(airlineID,reservedSeats));
                    worker.execute();
                } else {
                    GUIUpdater.showDialog("Please select and confirm at least one seat!");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {     //Kehrt zur vorherigen Seite zurück
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!reservedSeats.isEmpty()) {
                    for(Integer integer:reservedSeats) {
                        Airline.IDFlightHashMap.get(airlineID).seatList[integer].changeReservationStatus();
                    }
                }
                MyWorker worker = new MyWorker(new FlightDescriptionGUI(airlineID));  //Wird benötigt um ein EDT Error zu umgehen siehe MyWorker Class
                worker.execute();
            }
        });

        screenLabel = new JLabel("Screen");
        screenLabel.setBackground(Color.orange);
        screenLabel.setPreferredSize(new Dimension(650,50));
        screenLabel.setSize(650,50);
        screenLabel.setHorizontalAlignment(JLabel.CENTER);
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));

        setLayout(new BorderLayout());              //Dieses Layout erlaubt mir die Panels so anzulegen

        topPanel.add(backButton);
        topPanel.add(screenLabel);

        bottomPanel.add(confirm);
        bottomPanel.add(continueButton);

        //Hier werden die Panels an die richtige Stelle angelegt. Layout funktioniert noch nicht ganz richtig!

        add(topPanel,BorderLayout.PAGE_START);
        add(leftPanel,BorderLayout.LINE_START);
        add(midPanel,BorderLayout.CENTER);
        add(rightPanel,BorderLayout.LINE_END);
        add(bottomPanel,BorderLayout.PAGE_END);

        setSize(1280,400);
        setPreferredSize(new Dimension(1280,400));

        revalidate();
    }
}
