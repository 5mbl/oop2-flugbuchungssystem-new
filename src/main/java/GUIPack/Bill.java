package GUIPack;

import FlightPack.Airline;
import FlightPack.DepartureLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bill extends JPanel{
    private final JButton EndSession;
    private String nutzerName;
    private final JPanel InfoPanel;
    private final JPanel TopPanel;

    private String[] InfoArray;


    private final JLabel seatLabel;
    private final JLabel flightLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;
    private final JLabel departureLable;
    private final JLabel nutzernameLabel;
    private final JLabel BillLable;



    public Bill(int airlineID, ArrayList<Integer> seatList, String nutzerName,String seatID){
        setLayout(new BorderLayout());
        this.nutzerName= nutzerName;

        EndSession = new JButton("Download the bill and end session ");
        EndSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    // Specify the file path and name for saving the bill
                    String filePath = "bill.txt";

                    // Create a BufferedWriter to write to the file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                    // Write the bill information to the file

                    writer.write(InfoArray[0] + "\n");
                    writer.write(InfoArray[1] + "\n");
                    writer.write(InfoArray[2] + "\n");
                    writer.write(InfoArray[3] + "\n");
                    writer.write(InfoArray[4] + "\n");
                    writer.write(InfoArray[5] + "\n");
                    writer.write(InfoArray[6] + "\n");

                    // Close the writer
                    writer.close();

                    JOptionPane.showMessageDialog(null, "Bill downloaded successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred while downloading the bill.");
                    ex.printStackTrace();
                }


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


        InfoArray = new String[7];
        InfoArray[0] = "Airline Name: "+ Airline.get(airlineID).getName();
        InfoArray[1] = "Departure: "+ DepartureLocation.getSelectedCity();
        InfoArray[2] = "Destination: "+ Airline.currentDestination.getName();
        InfoArray[3] = "Date/Time: "+ Airline.get(airlineID).getTimeString();
        InfoArray[4] = "Seat Number: "+seatID;
        InfoArray[5] = "Name: "+nutzerName;
        InfoArray[6] = "Price: "+String.format("%.2f",((Airline.get(airlineID).getPrice()* Airline.currentDestination.getPaymentFactor())*seatList.size()))+"$";


        flightLabel = new JLabel(InfoArray[0]);
        departureLable = new JLabel(InfoArray[1]);
        locationLabel = new JLabel(InfoArray[2]);
        timeLabel = new JLabel(InfoArray[3]);
        seatLabel = new JLabel(InfoArray[4]);
        nutzernameLabel = new JLabel(InfoArray[5]);
        priceLabel = new JLabel(InfoArray[6]);
        Font billFont = new Font("SansSerif", Font.BOLD, 40);
        Font largerFont = new Font("SansSerif", Font.BOLD, 20); // Adjust the font size (20) as needed
        // Apply the larger font to the labels
        flightLabel.setFont(largerFont);
        locationLabel.setFont(largerFont);
        timeLabel.setFont(largerFont);
        seatLabel.setFont(largerFont);
        priceLabel.setFont(largerFont);
        nutzernameLabel.setFont(largerFont);
        departureLable.setFont(largerFont);
        // Apply billFont to the BillLable
        BillLable = new JLabel("Thanks for your order - Here is your Bill ");
        BillLable.setFont(billFont);


        TopPanel.add(BillLable);
        InfoPanel.add(flightLabel);
        InfoPanel.add(departureLable);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(nutzernameLabel);
        InfoPanel.add(priceLabel);
        TopPanel.add(EndSession);


        add(TopPanel, BorderLayout.NORTH);
        add(InfoPanel, BorderLayout.CENTER);


        revalidate();

    }

    private String nutzerName() {
        return null;
    }

    ;


}