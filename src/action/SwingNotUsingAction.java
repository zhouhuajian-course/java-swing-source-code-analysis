package action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingNotUsingAction {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingNotUsingAction().createAndShowGUI();
            }
        });
    }

    JFrame frame;
    JMenuBar menuBar;
    JToolBar toolBar;
    JTextArea textArea;

    ActionListener actionListener;

    private void createAndShowGUI() {
        frame = new JFrame("SwingNotUsingAction");

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Build Successful!\n");
            }
        };

        menuBar = new JMenuBar();
        JMenu buildMenu = new JMenu("Build");
        JMenuItem buildProjectMenuItem = new JMenuItem("Build Project");
        buildProjectMenuItem.addActionListener(actionListener);
        buildMenu.add(buildProjectMenuItem);
        menuBar.add(buildMenu);
        frame.setJMenuBar(menuBar);

        toolBar = new JToolBar();
        JButton buildProjectButton = new JButton("Build Project");
        buildProjectButton.addActionListener(actionListener);
        buildProjectButton.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
        toolBar.add(buildProjectButton);
        frame.add(toolBar, BorderLayout.NORTH);

        textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 禁用 Build Project
        // buildProjectMenuItem.setEnabled(false);
        // buildProjectButton.setEnabled(false);

        frame.pack();
        frame.setVisible(true);
    }
}
