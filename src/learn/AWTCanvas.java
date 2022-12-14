package learn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTCanvas extends Frame {
    public static void main(String[] args) {
        new AWTCanvas().setVisible(true);
    }

    boolean startDraw = false;

    class ACanvas extends Canvas {
        @Override
        public void paint(Graphics g) {
            if (startDraw) {
                g.setColor(Color.RED);
                g.drawLine(20, 20, 100, 100);
            }
        }
    }
    Canvas canvas;
    // 绘画按钮
    Button drawButton;

    AWTCanvas() {
        canvas = new ACanvas();
        canvas.setPreferredSize(new Dimension(300, 200));
        drawButton = new Button("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDraw = true;
                canvas.repaint();
            }
        });
        Panel panel = new Panel();
        panel.add(drawButton);
        add(canvas);
        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
