package GUIPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI extends JPanel {
    private final JLabel welcomeLabel;

    private final JButton continueButton;


    public WelcomeGUI() {
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Willkommen auf Flugbuchung24");
        welcomeLabel.setPreferredSize(new Dimension(800,250));
        welcomeLabel.setSize(new Dimension(800,250));
        welcomeLabel.setFont(new Font("Bold",Font.BOLD,50));
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);

        continueButton = new JButton("Weiter");
        continueButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,10));
        continueButton.setHorizontalTextPosition(JLabel.CENTER);
        continueButton.addActionListener(new Continue());
        continueButton.setPreferredSize(new Dimension(200,100));
        continueButton.setSize(new Dimension(200,100));

        add(welcomeLabel,BorderLayout.PAGE_START);
        add(continueButton,BorderLayout.CENTER);

        revalidate();
    }

    private class Continue implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //MyWorker worker = new MyWorker(new LocationPickerGUI());
            MyWorker worker = new MyWorker(new DepartureGUI());
            worker.execute();
        }
    }
}
