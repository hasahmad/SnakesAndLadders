package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // Constants for such as setting the layout for window/frame
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 725;
    private static final int MIN_WIDTH = 950;
    private static final int MIN_HEIGHT = 700;

    private String[] playerNames;

    // Swing components
    private GamePanel gamePanel;
    private ControlPanel controlPanel;

    private JTextField playerTextField[];
    private Object[] message;

    public MainFrame() {
        super("Snakes and Ladders");

        playerNames = new String[2];
        playerTextField = new JTextField[2];
        playerTextField[0] = new JTextField();
        playerTextField[1] = new JTextField();

        message = new Object[]{
                "Player 1:", playerTextField[0],
                "Player 2:", playerTextField[1]
        };

        createDialogView();
        createBoardView();
    }

    /**
     * Creates and the Dialog view which asks users to enter names of players 1 and 2
     * When names are entered and clicked ok, user will be redirected to the Game
     * if not names entered, users will be asked to exit the game
     */
    private void createDialogView() {
        int option = JOptionPane.showConfirmDialog(null, message, "Add Players", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // If more players, use loop to check for validation
            if (!(playerTextField[0].getText().length() > 0 && playerTextField[1].getText().length() > 0)) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit?",
                        "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else {
                    // If the OK button is not clicked when exiting application, then invoke this method again
                    createDialogView();
                }
            }

            // If more players, use loop to assign values
            playerNames[0] = playerTextField[0].getText();
            playerNames[1] = playerTextField[1].getText();

        } else {
            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Do you really want to exit?",
                    "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                System.exit(0);
            } else {
                // If the OK button is not clicked when exiting application, then invoke this method again
                createDialogView();
            }
        }
    }

    private void createBoardView() {
        // Set layout to a BorderLayout
        setLayout(new BorderLayout());

        // Instantiate Swing components
        gamePanel = new GamePanel();
        controlPanel = new ControlPanel();

        add(controlPanel, BorderLayout.EAST);
        add(gamePanel, BorderLayout.WEST);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void exitGame() {
        dispose();
        System.exit(0);
    }

    public GameRules getGameRules() {
        GameRules.getInstance().setVisible(true);
        return GameRules.getInstance();
    }

    public void resetGame() {
        dispose();
        remove(gamePanel);

        add(gamePanel, BorderLayout.WEST);
        gamePanel.repaint();
        revalidate();
        setVisible(true);
    }
}
