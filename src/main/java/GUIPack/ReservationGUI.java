package GUIPack;

import FlightPack.Airline;
import FlightPack.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationGUI extends JPanel {

    private final JPanel midPanel;

    private final JPanel topPanel;
    private final JPanel bottomPanel;

    private final ArrayList<JCheckBox> checkBoxArrayList = new ArrayList<>();
    JRadioButton rb1 = new JRadioButton("squirrel");
    private final JButton confirm;
    private final JButton continueButton;
    private final JButton backButton;
    private JRadioButton economyRadioButton;
    private JRadioButton businessRadioButton;
    private JButton reserveButton;
    private int selectedSeatNumber;
    private final Flight selectedFlight;
    private boolean foundSelectedCheckbox;

    public ReservationGUI(int airlineID) {  //Hier kann man reservieren
        ArrayList<Integer> reservedSeats = new ArrayList<>(); /// only one

        selectedFlight = Airline.IDFlightHashMap.get(airlineID);


        midPanel = new JPanel();

        topPanel = new JPanel();
        bottomPanel = new JPanel();

        midPanel.setLayout(new GridLayout(4,36));

        bottomPanel.setLayout(new FlowLayout());
        int numRows = 6; // Количество строк
        int numColumns = 20; // Количество столбцов
        int numSeatsPerColumn = 6; // Количество мест в каждом столбце
        JRadioButton rb1 = new JRadioButton("squirrel");
        for (int j = 0; j < selectedFlight.seatList.length; j++) {
            int row = j % numRows + 1; // Вычисление номера строки
            int column = j / numRows + 1; // Вычисление номера столбца

            // Получение буквенного обозначения места
            String seatLabel = getColumnLetter(column) + getRowLetter(row);

            JCheckBox checkBox = new JCheckBox(seatLabel);
            if(selectedFlight.seatList[j].isReserved) {                                                      //Wenn Sitz schon reserviert ist, wird die CheckBox disabled
                checkBox.setEnabled(false);
            }
            checkBoxArrayList.add(checkBox);
        }

        for (int j = 0; j < 40; j++) {
            int row = j % 10 + 1;
            int column = j / 10 + 1;

            String seatLabel = getColumnLetter(column) + getRowLetter(row);

            JCheckBox checkBox = new JCheckBox(seatLabel);
            checkBoxArrayList.add(checkBox);
            midPanel.add(checkBox);

        }

        confirm = new JButton("Confirm");
        continueButton = new JButton("Continue");
        backButton = new JButton("Back");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSeatNumber = getSelectedSeatNumber();
                reservedSeats.add(selectedSeatNumber);
                //System.out.println(selectedSeatNumber);
            }
        });





        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedSeatNumber != -1) { // check truthy
                    MyWorker worker = new MyWorker(new PaymentGUI(airlineID,reservedSeats));
                    worker.execute();
                } else {
                    GUIUpdater.showDialog("Please select and confirm one seat!");
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
    private int getSelectedSeatNumber() {
        for (int i = 0; i < checkBoxArrayList.size(); i++) {
            JCheckBox checkBox = checkBoxArrayList.get(i);
            if (checkBox.isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private String getColumnLetter(int column) {

        return String.valueOf((char) ('A' + (column - 1)));
    }

    private String getRowLetter(int row) {
        return String.valueOf((char) ('1' + (row - 1)));
    }
}