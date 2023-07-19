package GUIPack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeGUI extends JPanel {
    private final JLabel welcomeLabel;
    private final JButton continueButton;
    private final ImageIcon backgroundImage;
    BufferedImage image;

    protected void paintComponent(Graphics g) {
        try {
            image = ImageIO.read(new File("src/main/resources/backround.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, this);
    }

    public WelcomeGUI() {
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon("src/main/resources/backround.jpg");

        welcomeLabel = new JLabel("Willkommen auf Flugbuchung24");
        welcomeLabel.setPreferredSize(new Dimension(800,250));
        welcomeLabel.setSize(new Dimension(800,250));
        welcomeLabel.setFont(new Font("Bold",Font.BOLD,50));
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);

        continueButton = new JButton("Starten");
        continueButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        continueButton.setHorizontalTextPosition(JLabel.CENTER);
        continueButton.addActionListener(new Continue());
        continueButton.setFont(new Font("Bold", Font.BOLD, 24));
        continueButton.setPreferredSize(new Dimension(150, 75));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(continueButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
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
