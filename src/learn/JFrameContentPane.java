package learn;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义JFrame的ContentPane
 * setContentPane
 */
public class JFrameContentPane {
    public static void main(String[] args) {
        JFrame topLevelContainer = new JFrame();
        //Create a panel and add components to it.
        JPanel contentPane = new JPanel(new BorderLayout());
        //contentPane.setBorder(someBorder);
        //contentPane.add(someComponent, BorderLayout.CENTER);
        //contentPane.add(anotherComponent, BorderLayout.PAGE_END);
        contentPane.add(new JTextArea(30, 100), BorderLayout.CENTER);
        contentPane.add(new JButton("Test"), BorderLayout.PAGE_END);

        topLevelContainer.setContentPane(contentPane);
        topLevelContainer.pack();
        topLevelContainer.setVisible(true);
    }
}
