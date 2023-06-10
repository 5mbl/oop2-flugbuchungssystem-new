package GUIPack;

import CinemaPack.Cinema;
import com.beust.ah.A;
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

public class CheckoutGUI extends JPanel {
    private final JButton endProgram;

    private final JLabel BillLabel;
    private final JLabel QRCodeImage;

    private File QRFile;
    public CheckoutGUI(String PaymentOption, String Name, String[] infoArray) {
        System.out.println(PaymentOption+Name);

        setLayout(new BorderLayout());

        endProgram = new JButton("Finish session");
        endProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyWorker worker = new MyWorker(new WelcomeGUI());
                worker.execute();
            }
        });

        StringBuilder builder = new StringBuilder();
        builder.append(Cinema.getBillID());
        builder.append(System.getProperty("line.separator"));
        builder.append(Name);
        builder.append(System.getProperty("line.separator"));
        builder.append(PaymentOption);
        builder.append(System.getProperty("line.separator"));
        builder.append(infoArray[0]);

        for(int i = 1; i<infoArray.length; i++) {
            builder.append(System.getProperty("line.separator"));
            builder.append(infoArray[i]);
        }

        File file = QRCode.from(builder.toString()).to(ImageType.PNG)
                .withSize(400, 400)
                .file();

        String fileName = "src/main/resources/qrgen-qrcode.png";

        Path path = Paths.get(fileName);
        if ( Files.exists(path)){
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.copy(file.toPath(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        QRCodeImage = new JLabel(new ImageIcon("src/main/resources/qrgen-qrcode.png"));

        BillLabel = new JLabel("Bill:");
        BillLabel.setFont(new Font("Bold",Font.BOLD,30));

        add(BillLabel,BorderLayout.PAGE_START);
        add(QRCodeImage,BorderLayout.CENTER);
        add(endProgram,BorderLayout.PAGE_END);

        revalidate();
    }
}
