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
    private final JPanel BottomPanel;


    private String[] InfoArray;

    private final JLabel nameLabel;
    private final JLabel seatLabel;
    private final JLabel flightLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;
    private final JLabel departureLable;
    private final JLabel BillLable;



    public Bill(int airlineID, ArrayList<Integer> seatList, String name){
        setLayout(new BorderLayout());

        EndSession = new JButton("End Session");
        EndSession.setPreferredSize(new Dimension(100, 30));

        EndSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // This exits the application with a status code of 0
            }
    });


        TopPanel = new JPanel();
        InfoPanel = new JPanel();
        BottomPanel = new JPanel();



        // layout
        // Set layout manager for TopPanel
        TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TopPanel.setPreferredSize(new Dimension(800, 100)); // Set desired size for TopPanel

        // Set layout manager for InfoPanel
        InfoPanel.setLayout(new GridLayout(6, 1, 0, 10)); // Adjust grid layout parameters as per your desired spacing
        InfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding to the InfoPanel


        BottomPanel.setLayout(new BorderLayout());
        BottomPanel.setPreferredSize(new Dimension(200, 20));


        InfoArray = new String[7];
        InfoArray[0] = "Name " + name;
        InfoArray[1] = "Airline Name: "+ Airline.get(airlineID).getName();
        InfoArray[2] = "Departure: "+ DepartureLocation.getSelectedCity();
        InfoArray[3] = "Destination: "+ Airline.currentDestination.getName();
        InfoArray[4] = "Date/Time: "+ Airline.get(airlineID).getTimeString();
        InfoArray[5] = "Seat Number: "+String.valueOf(seatList);
        InfoArray[6] = "Price: "+String.format("%.2f",((Airline.get(airlineID).getPrice()* Airline.currentDestination.getPaymentFactor())*seatList.size()))+"USD";

        nameLabel = new JLabel(InfoArray[0]);
        flightLabel = new JLabel(InfoArray[1]);
        departureLable = new JLabel(InfoArray[2]);
        locationLabel = new JLabel(InfoArray[3]);
        timeLabel = new JLabel(InfoArray[4]);
        seatLabel = new JLabel(InfoArray[5]);
        priceLabel = new JLabel(InfoArray[6]);



        Font billFont = new Font("SansSerif", Font.BOLD, 35);
        Font largerFont = new Font("SansSerif", Font.BOLD, 20); // Adjust the font size (20) as needed
        // Apply the larger font to the labels
        flightLabel.setFont(largerFont);
        locationLabel.setFont(largerFont);
        timeLabel.setFont(largerFont);
        seatLabel.setFont(largerFont);
        priceLabel.setFont(largerFont);
        departureLable.setFont(largerFont);
        nameLabel.setFont(largerFont);

        // Apply billFont to the BillLable
        BillLable = new JLabel("Thanks  " + name + "   for your order - Here is your Bill ");
        BillLable.setFont(billFont);


        TopPanel.add(BillLable);
        InfoPanel.add(flightLabel);

        InfoPanel.add(nameLabel);
        InfoPanel.add(flightLabel);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(priceLabel);
        InfoPanel.add(departureLable);
        BottomPanel.add(EndSession);


        add(TopPanel, BorderLayout.NORTH);
        add(InfoPanel, BorderLayout.CENTER);
        add(BottomPanel, BorderLayout.EAST);

        revalidate();

    };


}
