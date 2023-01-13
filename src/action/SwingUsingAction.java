package action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class SwingUsingAction {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingUsingAction().createAndShowGUI();
            }
        });
    }

    JFrame frame;
    JMenuBar menuBar;
    JToolBar toolBar;
    JTextArea textArea;

    Action buildAction;

    class BuildAction extends AbstractAction {
        BuildAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("Build Successful!\n");
        }
    }


    private void createAndShowGUI() {
        frame = new JFrame("SwingUsingAction");

        buildAction = new BuildAction("Build Project");

        menuBar = new JMenuBar();
        JMenu buildMenu = new JMenu("Build");
        buildMenu.add(buildAction);
        menuBar.add(buildMenu);
        frame.setJMenuBar(menuBar);

        toolBar = new JToolBar();
        JButton buildProjectButton = toolBar.add(buildAction);
        buildProjectButton.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
        frame.add(toolBar, BorderLayout.NORTH);

        textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 禁用 Build Project
        // buildAction.setEnabled(false);

        frame.pack();
        frame.setVisible(true);
    }
}
