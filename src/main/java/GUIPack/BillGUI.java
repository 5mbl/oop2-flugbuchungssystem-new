/*package GUIPack;

import FlightPack.Airline;
import FlightPack.DepartureLocation;
import FlightPack.Flight;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BillGUI extends JPanel {
    private final JButton endProgram;

    private final JLabel BillLabel;

    private File QRFile;
    public BillGUI(String PaymentOption, String Name, String[] infoArray) {

       // Flight departureLocation = new Flight();


        endProgram = new JButton("Finish session");
        endProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new WelcomeGUI());
                worker.execute();
            }
        });

        BillLabel = new JLabel("Bill:");
        BillLabel.setFont(new Font("Bold",Font.BOLD,30));

        add(BillLabel,BorderLayout.PAGE_START);
        add(endProgram,BorderLayout.PAGE_END);

        revalidate();
    }
}*/