package mvc;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MVCTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createMainWindowAndShow();
        });
    }

    private static void createMainWindowAndShow() {
        FlatLightLaf.installLafInfo();

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        JButton button = new JButton("test");
        JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(300, 300));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panel = new JPanel();
        panel.add(button);
        frame.add(label);
        frame.add(panel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ButtonModel buttonModel = button.getModel();
                // buttonModel.setEnabled(false);
                label.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }).start();
    }
}
