package org.xine.qtime.client.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.xine.qtime.client.swing.controls.TimeTable;

/**
 * The Class App.
 */
public final class App {

    /**
     * Instantiates a new app.
     */
    private App() {
        super();
    }

    /**
     * The main method.
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final TimeTable timeTable = new TimeTable();
                final JFrame frame = new JFrame("SDApp");
                frame.setSize(700, 500);
                frame.setContentPane(timeTable);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }
}
