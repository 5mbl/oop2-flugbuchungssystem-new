package GUIPack;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

class MyWorker extends SwingWorker<JPanel, Void> {  //SwingWorker erlaubt es zu einem Task im Background durchzuführen und das Programm nicht zu unterbrechen
    private final JPanel panel;

    public MyWorker(JPanel panel) {     //Hier geben wir das Panel durch, dass wir haben wollen
        this.panel = panel;
    }

    @Override
    protected JPanel doInBackground() throws Exception {    //Zuerst wird unser Frame gecleared und unser Panel durchgegeben
        GUIUpdater.clearRootPanel();
        return panel;
    }

    @Override
    protected void done() {     //Wenn der doInBackground() fertig ist, wird das Panel hinzugefügt
        try {
            JPanel panel = get();
            GUIUpdater.updateFrame(panel);  //Wenn man das einfach so beim ActionListener machen würde, kann sich das Programm bei jedem Panel switch aufhängen
        } catch (InterruptedException | ExecutionException e) {
            // Handle exception
        }
    }
}
