package GUIPack;

import FlightPack.Airline;
import FlightPack.Destination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DestinyLocationPickerGUI extends JPanel {
    private final JLabel InstructionLabel;

    private final JButton BerlinButton;
    private final JButton HamburgButton;
    private final JButton DresdenButton;
    private final JButton backButton;

    public DestinyLocationPickerGUI() {
        setLayout(new BorderLayout());

        InstructionLabel = new JLabel("Select your Destination");
        InstructionLabel.setPreferredSize(new Dimension(800,250));
        InstructionLabel.setSize(new Dimension(800,250));
        InstructionLabel.setFont(new Font("Bold",Font.BOLD,50));
        InstructionLabel.setHorizontalTextPosition(JLabel.CENTER);
        InstructionLabel.setHorizontalAlignment(JLabel.CENTER);

        BerlinButton = new JButton("BER AIRPORT");
        HamburgButton = new JButton("Hamburg Airport");
        DresdenButton = new JButton("Dresden Airport");
        backButton = new JButton("Back");

        BerlinButton.setPreferredSize(new Dimension(800/3,100));
        BerlinButton.setSize(800/3,100);
        HamburgButton.setPreferredSize(new Dimension(800/3,100));
        HamburgButton.setSize(800/3,100);
        DresdenButton.setPreferredSize(new Dimension(800/3,100));
        DresdenButton.setSize(800/3,100);
        backButton.setPreferredSize(new Dimension(800,50));
        backButton.setSize(800,50);

        BerlinButton.addActionListener(new LocationActionListener());
        HamburgButton.addActionListener(new LocationActionListener());
        DresdenButton.addActionListener(new LocationActionListener());
        backButton.addActionListener(new BackActionListener());

        add(InstructionLabel,BorderLayout.PAGE_START);
        add(BerlinButton,BorderLayout.LINE_START);
        add(HamburgButton,BorderLayout.CENTER);
        add(DresdenButton,BorderLayout.LINE_END);
        add(backButton,BorderLayout.PAGE_END);

        revalidate();
    }

    private class LocationActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(BerlinButton)) {
                Airline.changeCurrentLocation(Destination.BERLIN);
                MyWorker worker = new MyWorker(new AirlineSelectGUI());
                worker.execute();
            }
            if(e.getSource().equals(HamburgButton)) {
                Airline.changeCurrentLocation(Destination.HAMBURG);
                MyWorker worker = new MyWorker(new AirlineSelectGUI());
                worker.execute();
            }
            if(e.getSource().equals(DresdenButton)) {
                Airline.changeCurrentLocation(Destination.DRESDEN);
                MyWorker worker = new MyWorker(new AirlineSelectGUI());
                worker.execute();
            }
        }
    }

    private class BackActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyWorker worker = new MyWorker(new WelcomeGUI());
            worker.execute();
        }
    }
}
