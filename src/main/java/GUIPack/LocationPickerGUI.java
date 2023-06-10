package GUIPack;

import CinemaPack.Cinema;
import CinemaPack.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationPickerGUI extends JPanel {
    private final JLabel InstructionLabel;

    private final JButton BerlinButton;
    private final JButton HamburgButton;
    private final JButton DresdenButton;
    private final JButton backButton;

    public LocationPickerGUI() {
        setLayout(new BorderLayout());

        InstructionLabel = new JLabel("Select your Destination");
        InstructionLabel.setPreferredSize(new Dimension(800,250));
        InstructionLabel.setSize(new Dimension(800,250));
        InstructionLabel.setFont(new Font("Bold",Font.BOLD,50));
        InstructionLabel.setHorizontalTextPosition(JLabel.CENTER);
        InstructionLabel.setHorizontalAlignment(JLabel.CENTER);

        BerlinButton = new JButton("Berlin");
        HamburgButton = new JButton("Hamburg");
        DresdenButton = new JButton("Dresden");
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
                Cinema.changeCurrentLocation(Location.BERLIN);
                MyWorker worker = new MyWorker(new MoviePickerGUI());
                worker.execute();
            }
            if(e.getSource().equals(HamburgButton)) {
                Cinema.changeCurrentLocation(Location.HAMBURG);
                MyWorker worker = new MyWorker(new MoviePickerGUI());
                worker.execute();
            }
            if(e.getSource().equals(DresdenButton)) {
                Cinema.changeCurrentLocation(Location.DRESDEN);
                MyWorker worker = new MyWorker(new MoviePickerGUI());
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
