package learn;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class AWTEventDispatchThread {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Runnable doHelloWorld = new Runnable() {
            public void run() {

                System.out.println("Hello World from " + Thread.currentThread());
            }
        };

        SwingUtilities.invokeLater(doHelloWorld);
        // SwingUtilities.invokeAndWait(doHelloWorld);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }
        System.out.println("Hello World from " + Thread.currentThread());
    }
}
