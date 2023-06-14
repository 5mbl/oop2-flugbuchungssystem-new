package GUIPack;

import FlightPack.Airline;
import FlightPack.DepartureLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Bill extends JPanel{
    private final JButton EndSession;

    private final JPanel InfoPanel;
    private String[] InfoArray;

    //private final JLabel name;
    private final JLabel seatLabel;
    private final JLabel flightLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;
    private final JLabel departureLable;



    public Bill(int airlineID, ArrayList<Integer> seatList){
        setLayout(new BorderLayout());

        EndSession = new JButton("EndSession");
        EndSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // This exits the application with a status code of 0
            }
    });



        InfoPanel = new JPanel();

        InfoArray = new String[6];
        InfoArray[0] = "Name: "+ Airline.get(airlineID).getName();
        InfoArray[1] = "Destination: "+ Airline.currentDestination.getName();
        InfoArray[2] = "Date/Time: "+ Airline.get(airlineID).getTimeString();
        InfoArray[3] = "Seat Number: "+String.valueOf(seatList);
        InfoArray[4] = "Price: "+String.format("%.2f",((Airline.get(airlineID).getPrice()* Airline.currentDestination.getPaymentFactor())*seatList.size()))+"USD";
        InfoArray[5] = "Departure: "+ DepartureLocation.getSelectedCity();

        flightLabel = new JLabel(InfoArray[0]);
        locationLabel = new JLabel(InfoArray[1]);
        timeLabel = new JLabel(InfoArray[2]);
        seatLabel = new JLabel(InfoArray[3]);
        priceLabel = new JLabel(InfoArray[4]);
        departureLable = new JLabel(InfoArray[5]);

        InfoPanel.add(flightLabel);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(priceLabel);
        InfoPanel.add(departureLable);

        add(InfoPanel,BorderLayout.CENTER);

        revalidate();

    };


}
