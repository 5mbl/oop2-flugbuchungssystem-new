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
    private final JPanel TopPanel;

    private String[] InfoArray;

    //private final JLabel name;
    private final JLabel seatLabel;
    private final JLabel flightLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;
    private final JLabel departureLable;
    private final JLabel BillLable;



    public Bill(int airlineID, ArrayList<Integer> seatList){
        setLayout(new BorderLayout());

        EndSession = new JButton("EndSession");
        EndSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // This exits the application with a status code of 0
            }
    });


        TopPanel = new JPanel();
        InfoPanel = new JPanel();



        // layout
        // Set layout manager for TopPanel
        TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TopPanel.setPreferredSize(new Dimension(800, 100)); // Set desired size for TopPanel

        // Set layout manager for InfoPanel
        InfoPanel.setLayout(new GridLayout(6, 1, 0, 10)); // Adjust grid layout parameters as per your desired spacing
        InfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding to the InfoPanel


        InfoArray = new String[6];
        InfoArray[0] = "Airline Name: "+ Airline.get(airlineID).getName();
        InfoArray[1] = "Departure: "+ DepartureLocation.getSelectedCity();
        InfoArray[2] = "Destination: "+ Airline.currentDestination.getName();
        InfoArray[3] = "Date/Time: "+ Airline.get(airlineID).getTimeString();
        InfoArray[4] = "Seat Number: "+String.valueOf(seatList);
        InfoArray[5] = "Price: "+String.format("%.2f",((Airline.get(airlineID).getPrice()* Airline.currentDestination.getPaymentFactor())*seatList.size()))+"USD";

        flightLabel = new JLabel(InfoArray[0]);
        departureLable = new JLabel(InfoArray[1]);
        locationLabel = new JLabel(InfoArray[2]);
        timeLabel = new JLabel(InfoArray[3]);
        seatLabel = new JLabel(InfoArray[4]);
        priceLabel = new JLabel(InfoArray[5]);


        Font billFont = new Font("SansSerif", Font.BOLD, 40);
        Font largerFont = new Font("SansSerif", Font.BOLD, 20); // Adjust the font size (20) as needed
        // Apply the larger font to the labels
        flightLabel.setFont(largerFont);
        locationLabel.setFont(largerFont);
        timeLabel.setFont(largerFont);
        seatLabel.setFont(largerFont);
        priceLabel.setFont(largerFont);
        departureLable.setFont(largerFont);
        // Apply billFont to the BillLable
        BillLable = new JLabel("Thanks for your order - Here is your Bill ");
        BillLable.setFont(billFont);


        TopPanel.add(BillLable);
        InfoPanel.add(flightLabel);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(priceLabel);
        InfoPanel.add(departureLable);

        add(TopPanel, BorderLayout.NORTH);
        add(InfoPanel, BorderLayout.CENTER);


        revalidate();

    };


}
