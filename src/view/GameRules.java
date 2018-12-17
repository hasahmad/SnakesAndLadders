package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameRules extends JFrame {

    private static GameRules INSTANCE = new GameRules();

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final int MIN_WIDTH = 500;
    private static final int MIN_HEIGHT = 400;

    private JLabel title;
    private JLabel body;

    private GameRules() {
        super("Game Rules");

        createBoardView();
    }

    private void createBoardView() {
        // Set layout to a BorderLayout
        setLayout(new BorderLayout());

        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border border = BorderFactory.createLineBorder(Color.GRAY);

        title = new JLabel("<html><h1>How to play snakes and Ladders</h1></html>");
        title.setFont(new Font("Myriad Pro",Font.PLAIN,15));
        title.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));

        body = new JLabel("<html><b>Objective</b>"
                + "<p>The object of the game is to be the first player to reach the end by moving across the board from square one to the final square. Most boards wrap back and forth, so you move left to right across first row, then move up to the second and move right to left, and so on.</p>"
                + "<ul>"
                + "<li>Each player puts their counter before 1.</li>"
                + "<li>Take it in turns to roll the dice. Move your counter forward the number of spaces shown on the dice.</li>"
                + "<li>If the number on the dice is 6, move forward and you get to roll the dice again.</li>"
                + "<li>If your counter lands at the bottom of a ladder, you can move up to the top of the ladder.</li>"
                + "<li>If your counter lands on the head of a snake, you must slide down to the bottom of the snake.</li>"
                + "<li>The first player to get to the 100th position is the winner.</li>"
                + "</ul></html>");
        body.setFont(new Font("Myriad Pro",Font.PLAIN,13));
        body.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));

        add(title, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static GameRules getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameRules();
        }
        return INSTANCE;
    }
}
