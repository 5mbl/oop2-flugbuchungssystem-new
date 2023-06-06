package GUIPack;

import CinemaPack.Cinema;

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
    private final JLabel movieLabel;
    private final JLabel locationLabel;
    private final JLabel timeLabel;
    private final JLabel priceLabel;

    private String[] InfoArray;

    public PaymentGUI(int cinemaID, ArrayList<Integer> seatList) {
        setLayout(new BorderLayout());

        Abort = new JButton("Abort");
        checkOut = new JButton("Check-Out");

        Abort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i<Cinema.get(cinemaID).seatList.length; i++) {
                    for(Integer integer:seatList) {
                        if(integer == i) {
                            Cinema.get(cinemaID).seatList[i].changeReservationStatus();
                        }
                    }
                }
                MyWorker worker = new MyWorker(new LocationPickerGUI());
                worker.execute();
            }
        });
        checkOut.addActionListener(new CheckOutActionListener());

        PaymentBox = new JComboBox(new String[] {"Visa","MasterCard","PayPal"});
        PaymentBox.setSelectedItem(null);

        nameInstruction = new JLabel("-Enter your Name-");
        nameInstruction.setHorizontalAlignment(JLabel.RIGHT);
        nameField = new JTextField();

        InfoPanel = new JPanel();
        BottomPanel = new JPanel();

        InfoArray = new String[5];
        InfoArray[0] = "Name: "+Cinema.get(cinemaID).getName();
        InfoArray[1] = "Location: "+Cinema.currentLocation.getName();
        InfoArray[2] = "Date/Time: "+Cinema.get(cinemaID).getTimeString();
        InfoArray[3] = "Seats: "+String.valueOf(seatList);
        InfoArray[4] = "Price: "+String.format("%.2f",((Cinema.get(cinemaID).getPrice()*Cinema.currentLocation.getPaymentFactor())*seatList.size()))+"USD";

        movieLabel = new JLabel(InfoArray[0]);
        locationLabel = new JLabel(InfoArray[1]);
        timeLabel = new JLabel(InfoArray[2]);
        seatLabel = new JLabel(InfoArray[3]);
        priceLabel = new JLabel(InfoArray[4]);

        InfoPanel.setLayout(new GridLayout(4,1));

        InfoPanel.add(movieLabel);
        InfoPanel.add(locationLabel);
        InfoPanel.add(timeLabel);
        InfoPanel.add(seatLabel);
        InfoPanel.add(priceLabel);

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
                MyWorker worker = new MyWorker(new CheckoutGUI(PaymentBox.getSelectedItem().toString(),nameField.getText(),InfoArray));
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
