package org.xine.qtime.client.swing.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Class TimeTable.
 */
public class TimeTable extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // private final double width;
    // private final double height;

    /** The labels. */
    private final JLabel[] labels;

    /**
     * Instantiates a new time table.
     */
    public TimeTable() {
        super();

        // this.width = width;
        // this.height = (3.0D / 4.0D) * this.height;

        setPreferredSize(new Dimension(700, 500));
        setBackground(Color.lightGray);
        setLayout(null);

        this.labels = new JLabel[9];

        this.labels[0] = new JLabel("2ª Feira");
        this.labels[0].setBounds(135, 70, 90, 50);

        this.labels[1] = new JLabel("3ª Feira");
        this.labels[1].setBounds(235, 70, 90, 50);

        this.labels[2] = new JLabel("4ª Feira");
        this.labels[2].setBounds(335, 70, 90, 50);

        this.labels[3] = new JLabel("5ª Feira");
        this.labels[3].setBounds(435, 70, 90, 50);

        this.labels[4] = new JLabel("6ª Feira");
        this.labels[4].setBounds(535, 70, 90, 50);

        // HOurs
        this.labels[5] = new JLabel("8h-10h");
        this.labels[5].setBounds(53, 120, 80, 70);

        this.labels[6] = new JLabel("10h-12h");
        this.labels[6].setBounds(53, 190, 80, 70);

        this.labels[7] = new JLabel("13h-15h");
        this.labels[7].setBounds(53, 280, 80, 70);
        this.labels[8] = new JLabel("15h-17h");
        this.labels[8].setBounds(53, 350, 80, 70);

        for (final JLabel l : this.labels) {
            l.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(l);
        }

    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);

        // Lines vertical Lines
        g.drawLine(50, 70, 50, 420);
        g.drawLine(130, 70, 130, 420);

        // horizontal Lines
        g.drawLine(50, 70, 630, 70);
        g.drawLine(50, 120, 630, 120);
        g.drawLine(50, 190, 630, 190);
        g.drawLine(50, 260, 630, 260);
    }
}
