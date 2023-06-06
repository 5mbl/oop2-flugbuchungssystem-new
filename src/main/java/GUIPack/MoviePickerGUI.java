package GUIPack;

import MoviePack.MovieModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoviePickerGUI extends JPanel {                            //StartPanel also kein backButton
    private final ArrayList<JButton> buttonList = new ArrayList<>();
    private final JButton backButton;
    private final JPanel MasterPanel;

    public MoviePickerGUI() {
        MasterPanel = new JPanel();
        MasterPanel.setLayout(new GridLayout(MovieModel.values().length, 1));

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new LocationPickerGUI());
                worker.execute();
            }
        });

        MasterPanel.add(backButton);

        for(MovieModel model:MovieModel.values()) {                     //Für alle unterschiedlichen Filme in MovieModel wird ein Button erzeugt
            JButton button = new JButton(model.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyWorker worker = new MyWorker(new TimePickerGUI(model)); //Wird benötigt, um ein EDT Error zu umgehen, siehe MyWorker Class
                    worker.execute();
                }
            });
            buttonList.add(button); // alle film buttons werden in buttonlist hinzugefügt
        }


        for(JButton button:buttonList) { // buttonlist wird geloopt und im Jpanel angezeigt
            button.setSize(800/5,350/MovieModel.values().length);
            MasterPanel.add(button);
        }

        add(MasterPanel);

        revalidate();
    }
}
