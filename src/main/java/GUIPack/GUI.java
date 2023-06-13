package GUIPack;

import javax.swing.*;

public class GUI extends JFrame {   //Das ist unser MainFrame
    public GUI() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Program endet wenn das Fenster geschlossen wird
        setSize(800,350);   //Size für den WelcomeScreen

        setContentPane(new WelcomeGUI());  //Standard Panel -> Willkommensbildschirm als erstes angezeigt wird.
        pack();
    }

}
