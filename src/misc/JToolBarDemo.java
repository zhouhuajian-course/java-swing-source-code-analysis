package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JToolBarDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JToolBarDemo().createAndShowGUI());
    }

    JTextArea textArea;

    class LeftAction extends AbstractAction {

        LeftAction() {
            putValue(NAME, "L");
            putValue(ACTION_COMMAND_KEY, "L");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append(e.getActionCommand());
        }
    }

    class MiddleAction extends AbstractAction {

        MiddleAction() {
            putValue(NAME, "M");
            putValue(ACTION_COMMAND_KEY, "M");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append(e.getActionCommand());
        }
    }

    class RightAction extends AbstractAction {

        RightAction() {
            putValue(NAME, "R");
            putValue(ACTION_COMMAND_KEY, "R");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append(e.getActionCommand());
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame();

        Action leftAction = new LeftAction();
        Action middleAction = new MiddleAction();
        Action rightAction = new RightAction();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("菜单");
        JMenuItem menuItem1 = new JMenuItem(leftAction);
        JMenuItem menuItem2 = new JMenuItem(middleAction);
        JMenuItem menuItem3 = new JMenuItem(rightAction);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);

        JToolBar toolBar = new JToolBar();
        JButton button1 = new JButton(leftAction);
        JButton button2 = new JButton(middleAction);
        JButton button3 = new JButton(rightAction);
        JButton[] buttons = {button1, button2, button3};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("", 0, 8));
            toolBar.add(buttons[i]);
        }

        textArea = new JTextArea(8, 30);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
        frame.getContentPane().add(textArea, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
