package cmu.edu.gui;

import cmu.edu.core.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.io.IOException;

public class GUI {

    private static final String TITLE = "Circle Painter";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createAndShowStartPainterPanel);
    }

    public static void createAndShowStartPainterPanel() {

        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //startGamePanel
        JPanel startGamePanel = new JPanel();
        startGamePanel.setLayout(new GridLayout(5, 3));
        JLabel titleLabel = new JLabel(TITLE);
        JButton digitizerButton = new JButton("Digitize");
        JButton generatorButton = new JButton("Generate");

        digitizerButton.addActionListener(e -> {
            System.out.println("digitizer button clicked\n");
            CirclePainter painter = new CircleDigitizer();
            PainterPanel panel = new PainterPanel(painter);
            panel.setOpaque(true);
            frame.setContentPane(panel);
            frame.setSize(400, 400);
            frame.setResizable(false);
//            frame.pack();
            frame.setVisible(true);
        });

        generatorButton.addActionListener(e -> {
            System.out.println("generator button clicked\n");
            CirclePainter painter = new CircleGenerator();
            PainterPanel panel = new PainterPanel(painter);
            panel.setOpaque(true);
            frame.setContentPane(panel);
            frame.setSize(400, 400);
            frame.setResizable(false);
//            frame.pack();
            frame.setVisible(true);
        });

        startGamePanel.add(new JPanel());
        startGamePanel.add(new JPanel());
        startGamePanel.add(new JPanel());
        startGamePanel.add(new JPanel());
        startGamePanel.add(titleLabel);
        startGamePanel.add(new JPanel());
        startGamePanel.add(new JPanel());
        startGamePanel.add(digitizerButton);
        startGamePanel.add(new JPanel());
        startGamePanel.add(new JPanel());
        startGamePanel.add(generatorButton);

        frame.setContentPane(startGamePanel);

        frame.pack();
        frame.setVisible(true);

    }
}
