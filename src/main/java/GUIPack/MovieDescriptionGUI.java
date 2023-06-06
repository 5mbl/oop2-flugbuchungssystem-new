package GUIPack;

import CinemaPack.Cinema;
import MoviePack.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieDescriptionGUI extends JPanel {
    private final JPanel ImagePanel;
    private final JPanel InfoMasterPanel;
    private final JPanel TimeAndReservationPanel;
    private final JPanel TextPanel;
    private final JPanel HeaderPanel;

    private final JLabel ImageLabel;
    private final JLabel Time;

    private final JTextArea MovieDescription;

    private final JButton reservationButton;
    private final JButton backButton;

    private final Movie selectedMovie;
    private final int cinemaID;

    public MovieDescriptionGUI(int cinemaID) {                  //Hier sieht man die Beschreibung des Films
        selectedMovie = Cinema.IDMovieHashMap.get(cinemaID);    //Holt den ausgesuchten Film von Cinema
        this.cinemaID = cinemaID;

        setLayout(new BorderLayout());

        //Die Panels werden für das Layout verwendet

        InfoMasterPanel = new JPanel();
        TimeAndReservationPanel = new JPanel();
        ImagePanel = new JPanel();
        TextPanel = new JPanel();
        HeaderPanel = new JPanel();


        TimeAndReservationPanel.setLayout(new GridLayout(3,1));

        ImageLabel = new JLabel(new ImageIcon(selectedMovie.getImagePath()));   //Holt sich den Path vom Bild des Filmes
        Time = new JLabel("Time: "+selectedMovie.getTimeString());      //Holt sich die Zeit des Films
        Time.setFont(new Font("Bold",Font.BOLD,30));

        String text = selectedMovie.getDescription();
        int maxWordsPerLine = 7;
        String[] words = text.split(" ");
        StringBuilder wrappedText = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            wrappedText.append(words[i]);
            wrappedText.append(" ");
            if ((i + 1) % maxWordsPerLine == 0) {
                wrappedText.append("\n");
            }
        }
        MovieDescription = new JTextArea(wrappedText.toString());       //Beschreibung des Films
        MovieDescription.setFont(new Font("Arial", Font.PLAIN, 25));

        reservationButton = new JButton("Reserve");
        backButton = new JButton("Back");

        reservationButton.addActionListener(new ActionListener() {  //Geht zum Reservation Screen
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new ReservationGUI(cinemaID)); //Wird benötigt, um ein EDT Error zu umgehen, siehe MyWorker Class
                worker.execute();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new TimePickerGUI(selectedMovie.getModel()));    //Wird benötigt, um ein EDT Error zu umgehen, siehe MyWorker Class
                worker.execute();
            }
        });

        //Hier werden einfach alle Panels hinzugefügt

        ImagePanel.add(ImageLabel);

        InfoMasterPanel.add(TextPanel);

        TextPanel.add(MovieDescription);

        HeaderPanel.add(backButton);
        HeaderPanel.add(reservationButton);
        HeaderPanel.add(Time);

        add(HeaderPanel,BorderLayout.PAGE_START);
        add(InfoMasterPanel,BorderLayout.CENTER);
        add(ImagePanel,BorderLayout.LINE_START);


        setPreferredSize(new Dimension(1280,1080));
        setSize(new Dimension(1280,1080));

        revalidate();
    }
}
