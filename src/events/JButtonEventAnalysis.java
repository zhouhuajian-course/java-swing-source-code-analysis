package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonEventAnalysis {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JButtonEventAnalysis().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JButton button =  new JButton("Test");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.printf("Button Clicked!");
            }
        });
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
