package GUIPack;

import FlightPack.Airline;
import FlightPack.DepartureLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaymentGUI extends JPanel {
    private final JButton Abort;
    private final JButton checkOut;

    private final JComboBox PaymentBox;

    private final JLabel nameInstruction;
    private final JTextField nameField;

    private final JPanel InfoPanel;
    private final JPanel BottomPanel;

    private final JLabel seatLabel;
    private final JLabel flightLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;
    private final JLabel departureLable;

    private String[] InfoArray;

    private final String seatID; // airlineID
    private final int airlineID; // airlineID
    ArrayList<Integer> reservedSeats = new ArrayList<>(); /// only one




    public PaymentGUI(int airlineID, ArrayList<Integer> seatList, String selectedSeatNumber) {
        this.airlineID = airlineID; // airline wird als param von der vorherigen Klassen weitergegeben. Diese setten wir mit unserer Privaten Variable (benötigt für Bill Klasse)
        this.reservedSeats = seatList; // reservierte Sitzplätze wird als param weitergegeben. Wir fangen diese auf und setzten es wieder zu unserer Privaten Var. (benötigt für Bill Klasse)
        this.seatID=selectedSeatNumber;
        setLayout(new BorderLayout());

        Abort = new JButton("Abort");
        checkOut = new JButton("Check-Out");

        Abort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i< Airline.get(airlineID).seatList.length; i++) {
                    for(Integer integer:seatList) {
                        if(integer == i) {
                            Airline.get(airlineID).seatList[i].changeReservationStatus();
                        }
                    }
                }
                MyWorker worker = new MyWorker(new DestinyLocationPickerGUI());
                worker.execute();
            }
        });
        checkOut.addActionListener(new CheckOutActionListener());

        PaymentBox = new JComboBox(new String[] {"Visa","MasterCard","PayPal"});
        PaymentBox.setSelectedItem(null);

        nameInstruction = new JLabel("-Enter your Name-");
        nameInstruction.setHorizontalAlignment(JLabel.RIGHT);
        nameField = new JTextField();
        //NutzerName =nameField.getText();

        InfoPanel = new JPanel();
        BottomPanel = new JPanel();

        InfoArray = new String[6];
        InfoArray[0] = "Name: "+ Airline.get(airlineID).getName();
        InfoArray[1] = "Destination: "+ Airline.currentDestination.getName();
        InfoArray[2] = "Date/Time: "+ Airline.get(airlineID).getTimeString();
        InfoArray[3] = "Seat Number: "+ seatID;
        double totalPrice = Airline.get(airlineID).getPrice() * Airline.currentDestination.getPaymentFactor();
        InfoArray[4] = "Price: " + String.format("%.2f USD", totalPrice);
        InfoArray[5] = "Departure: "+ DepartureLocation.getSelectedCity();

        flightLabel = new JLabel(InfoArray[0]);
        locationLabel = new JLabel(InfoArray[1]);
        timeLabel = new JLabel(InfoArray[2]);
        seatLabel = new JLabel(InfoArray[3]);
        priceLabel = new JLabel(InfoArray[4]);
        departureLable = new JLabel(InfoArray[5]);

        InfoPanel.setLayout(new GridLayout(4,1));

        InfoPanel.add(flightLabel);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(priceLabel);
        InfoPanel.add(departureLable);

        BottomPanel.setLayout(new GridLayout(1,4));

        BottomPanel.add(PaymentBox);
        BottomPanel.add(nameInstruction);
        BottomPanel.add(nameField);
        BottomPanel.add(checkOut);

        add(Abort,BorderLayout.PAGE_START);
        add(InfoPanel,BorderLayout.CENTER);
        add(BottomPanel,BorderLayout.PAGE_END);

        revalidate();
    }


    private class CheckOutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(PaymentBox.getSelectedItem() != null && !nameField.getText().isEmpty()) {
                //MyWorker worker = new MyWorker(new CheckoutGUI(PaymentBox.getSelectedItem().toString(),nameField.getText(),InfoArray));

                MyWorker worker = new MyWorker(new Bill(airlineID,reservedSeats, nameField.getText(),seatID));
                System.out.println(nameField.getText());
                worker.execute();
            } else {
                if(PaymentBox.getSelectedItem() == null) {
                    PaymentBox.setBorder(BorderFactory.createLineBorder(Color.red,3));
                } else {
                    PaymentBox.setBorder(BorderFactory.createLineBorder(Color.green,3));
                }
                if(nameField.getText().isEmpty()) {
                    nameField.setBorder(BorderFactory.createLineBorder(Color.red,3));
                } else {
                    nameField.setBorder(BorderFactory.createLineBorder(Color.green,3));
                }
            }
        }
    }
}
