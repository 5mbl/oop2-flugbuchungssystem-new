package GUIPack;

import FlightPack.Airline;
import FlightPack.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class ReservationGUI extends JPanel {

    private final JPanel midPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;

    //  private final ArrayList<JRadioButton> checkBoxArrayList = new ArrayList<JRadioButton>();
    ButtonGroup seats=new ButtonGroup();
    private final JButton confirm;
    private final JButton continueButton;
    private final JButton backButton;

    private String selectedSeatNumber;
    private final Flight selectedFlight;


    public ReservationGUI(int airlineID) {  //Hier kann man reservieren
        ArrayList<Integer> reservedSeats = new ArrayList<>(); /// only one

        selectedFlight = Airline.IDFlightHashMap.get(airlineID);

        midPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        midPanel.setLayout(new GridLayout(4,36));

        bottomPanel.setLayout(new FlowLayout());


        for (int j = 0; j < 40; j++) {
            int row = j % 10 + 1;
            int column = j / 10 + 1;

            String seatLabel = getColumnLetter(column) + getRowLetter(row);
            JRadioButton radioButton = new JRadioButton(seatLabel);
            seats.add(radioButton);
            midPanel.add(radioButton);

        }

        confirm = new JButton("Confirm");
        continueButton = new JButton("Continue");
        backButton = new JButton("Back");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enumeration<AbstractButton> selectedButtons = seats.getElements();
                while (selectedButtons.hasMoreElements()) {
                    AbstractButton button = selectedButtons.nextElement();
                    if (button.isSelected()) {
                        String seatLabel = button.getText();
                        selectedSeatNumber = getSeatNumberFromLabel(seatLabel);
                        break;
                    }
                }
                if (selectedSeatNumber != null) {
                    // The seat is selected, you can use the selectedSeatNumber variable
                    System.out.println("Selected seat number: " + selectedSeatNumber);
                } else {
                    GUIUpdater.showDialog("Please select and confirm one seat!");
                }
            }
        });




        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedSeatNumber !=null) { // check truthy
                    MyWorker worker = new MyWorker(new PaymentGUI(airlineID,reservedSeats, selectedSeatNumber));
                    worker.execute();
                } else {
                    GUIUpdater.showDialog("Please select and confirm one seat!");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {     //Kehrt zur vorherigen Seite zurück
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new FlightDescriptionGUI(airlineID));  //Wird benötigt um ein EDT Error zu umgehen siehe MyWorker Class
                worker.execute();
            }
        });



        setLayout(new BorderLayout());              //Dieses Layout erlaubt mir die Panels so anzulegen

        topPanel.add(backButton);
        bottomPanel.add(confirm);
        bottomPanel.add(continueButton);

        //Hier werden die Panels an die richtige Stelle angelegt. Layout funktioniert noch nicht ganz richtig!

        add(topPanel,BorderLayout.PAGE_START);

        add(midPanel,BorderLayout.CENTER);

        add(bottomPanel,BorderLayout.PAGE_END);

        setSize(1280,400);
        setPreferredSize(new Dimension(1280,400));

        revalidate();
    }
    private String getSeatNumberFromLabel(String seatLabel) {
        return seatLabel;
    }

    private String getColumnLetter(int column) {

        return String.valueOf((char) ('A' + (column - 1)));
    }

    private String getRowLetter(int row) {
        return String.valueOf((char) ('1' + (row - 1)));
    }
}

