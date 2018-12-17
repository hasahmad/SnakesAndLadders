package controller;

import model.*;
import model.helpers.PlayerNotAddedException;
import view.ControlPanel;
import view.GamePanel;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller implements BaseController {

    // View
    private MainFrame mainFrame;
    private ControlPanel controlPanel;
    private GamePanel gamePanel;

    // Model
    private BaseModel model;
    private Database db;
    private Dice dice;
    private Ladder ladder;
    private Snake snake;
    private SerializeToDisk serializeToDisk;

    private Player highScorePlayer;

    private String fileName = "players.ser";

    private static int diceNum;
    public static int playerSelected = 0;
    public static int playerPosition = 0;
    public static int playerPrevPosition = 0;
    public static int turnCount = 0;

    public Controller(MainFrame view, BaseModel model) {
        // Views
        mainFrame = view;
        controlPanel = view.getControlPanel();
        gamePanel = view.getGamePanel();

        // Models
        this.model = model;
        this.db = model.getDatabaseModel();
        this.dice = model.getDiceModel();
        this.ladder = model.getLadderModel();
        this.snake = model.getSnakesModel();
        this.serializeToDisk = new SerializeToDisk();

        // Register players to the database
        registerPlayers(mainFrame.getPlayerNames());

        try {
            checkPlayerFile(fileName, false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Action Listeners
        controlPanel.addRollDiceListener(new RollDiceActionListener());
        controlPanel.addMoveForwardListener(new GoForwardActionListener());
        controlPanel.addGoBackListener(new GoBackwardActionListener());
        controlPanel.addRulesListener(new GameRuleActionListener());
        controlPanel.addExitListener(new ExitGameActionListener());
        controlPanel.addNewGameListener(new RestartGameActionListener());
    }

    /**
     * Instantiate Player objects
     * Register players to the database
     *
     * @param names
     *          player name
     */
    @Override
    public void registerPlayers(String[] names) {
        for (int i=0; i<names.length; i++) {
            String name = names[i];
            try {
                db.addPlayers( model.getPlayerModel(name));
                controlPanel.setPlayerName(name, i);
            } catch (PlayerNotAddedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Return all the player from the database
     * @return all the player from the database
     */
    @Override
    public List<Player> getPlayers() {
        return db.getPlayers();
    }

    /**
     * When Move Forward Button is clicked
     */
    private class GoForwardActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkIsSelected();
            setPlayerIcon();

            // Set Player Previous Position
            getPlayers().get(playerSelected).setPrevPosition(getPlayers().get(playerSelected).getCurrentPosition());
            playerPrevPosition = getPlayers().get(playerSelected).getPrevPosition();

            // Set Player Current Position
            getPlayers().get(playerSelected).setCurrentPosition(playerPrevPosition + diceNum);
            playerPosition = getPlayers().get(playerSelected).getCurrentPosition();

            if(getPlayers().get(playerSelected).getCurrentPosition() > 100) {
                controlPanel.showMessageDialog("You cannot move forward");
                getPlayers().get(playerSelected).setCurrentPosition(getPlayers().get(playerSelected).getPrevPosition());
                controlPanel.enableDiceForwardBackBtn(controlPanel.isRollDiceBtnEnabled(), false, true);
            } else if(getPlayers().get(playerSelected).getCurrentPosition() == 100) {
                controlPanel.showMessageDialog(String.format("Player %d Win!", (playerSelected+1)));

                try {
                    checkPlayerFile(fileName, true);
                    savePlayerToDisk(true);
                } catch (IOException eIO) {
                    eIO.printStackTrace();
                } catch (ClassNotFoundException eNF) {
                    eNF.printStackTrace();
                }

                new RestartGameActionListener();
            } else {
                movePlayer();
            }
        }
    }

    /**
     * When Move Backward Button is clicked
     */
    private class GoBackwardActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkIsSelected();
            setPlayerIcon();

            // Set Player Previous Position
            getPlayers().get(playerSelected).setPrevPosition(getPlayers().get(playerSelected).getCurrentPosition());
            playerPrevPosition = getPlayers().get(playerSelected).getPrevPosition();

            // Set Player Current Position
            getPlayers().get(playerSelected).setCurrentPosition(playerPrevPosition - diceNum);
            playerPosition = getPlayers().get(playerSelected).getCurrentPosition();

            if(getPlayers().get(playerSelected).getCurrentPosition() < 1) {
                controlPanel.showMessageDialog("You cannot move backward");
                getPlayers().get(playerSelected).setCurrentPosition(getPlayers().get(playerSelected).getPrevPosition());
                controlPanel.enableDiceForwardBackBtn(controlPanel.isRollDiceBtnEnabled(), true, false);
            } else {
                movePlayer();
            }
        }
    }

    /**
     * When Roll Dice Button is clicked
     */
    private class RollDiceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkIsSelected();

            diceNum = dice.roll(getPlayers().get(playerSelected));
            controlPanel.setDiceLabel("" + diceNum);
            controlPanel.setDiceNumberIcon(diceNum);
            controlPanel.enableDiceForwardBackBtn(false, true, true);
        }
    }

    /**
     * View the GameRules Window
     */
    private class GameRuleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getGameRules();
        }
    }

    /**
     * When the Restart game is clicked or someone finished the game
     * It will reset all the player's positions.
     */
    private class RestartGameActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            db.resetPositions();
            mainFrame.resetGame();

            playerSelected = 0;
            playerPosition = getPlayers().get(playerSelected).getCurrentPosition();
            playerPrevPosition = getPlayers().get(playerSelected).getPrevPosition();

            System.out.println(playerPosition);
            System.out.println(playerPrevPosition);

            try {
                checkPlayerFile(fileName, true);
            } catch (IOException eIO) {
                eIO.printStackTrace();
            } catch (ClassNotFoundException eNF) {
                eNF.printStackTrace();
            }
        }
    }

    /**
     * Used when exiting the game
     */
    private class ExitGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.exitGame();
        }
    }

    /**
     * Move the player in forward or backward position
     * Check the ladder and snake position on current player's position
     */
    private void movePlayer() {
        ladder.checkLadder(getPlayers().get(playerSelected));
        snake.checkSnake(getPlayers().get(playerSelected));
        playerPosition = getPlayers().get(playerSelected).getCurrentPosition();

        int x = 0;
        int y = 0;

        while (x < 10) {
            y = 0;
            while (y < 10) {
                int tempPos = (int) Double.parseDouble(gamePanel.getSquareLabel()[x][y].getText());
                String temp = gamePanel.getSquareLabel()[x][y].getText();
                String temp2 = playerPosition + "";
                String temp3 = playerPrevPosition + "";
                if (temp.equals(temp3)) {
                    gamePanel.setLabelPosition(x, y, "/resources/pics/" + playerPrevPosition + ".jpg");
                } else if (temp.equals(temp2)) {
                    gamePanel.setLabelPosition(x, y, "/resources/pics/" + controlPanel.getPlayerIcon());
                }
                y++;
            }
            x++;
        }

        controlPanel.enableDiceForwardBackBtn(true, false, false);

        diceRoll();
        turnCount();
    }

    /**
     * Sets each player icon to a different picture
     */
    private void setPlayerIcon() {
        switch (playerSelected) {
            case 0:
                controlPanel.setPlayerIcon("x1.jpg");
                break;
            case 1:
                controlPanel.setPlayerIcon("y1.jpg");
                break;
            default:
                break;
        }
    }

    /**
     * Check which player is selected and assign value to the playerSelected variable
     */
    private void checkIsSelected() {
        for(int i=0; i<getPlayers().size(); i++) {
            boolean playerRadio = controlPanel.getPlayerRadios()[i].isSelected();
            getPlayers().get(i).setSelected(playerRadio);
            if(playerRadio) {
                playerSelected = getPlayers().get(i).getId();
            }
        }
    }

    /**
     * If the dice rolled number is less than 6, its the other player's turn
     * else if dice rolled number is equal to 6, then the same player gets to roll dice again
     */
    private void diceRoll() {

        if (diceNum < 6) {
            turnCount++;
        } else if (diceNum == 6) {

        }
    }

    /**
     * Automatically move the turn to next player
     */
    private void turnCount() {
        if (turnCount % 2 == 0) {
            controlPanel.getPlayerRadios()[0].setSelected(true);
            playerSelected = 0;

        } else if (turnCount % 2 != 0) {
            controlPanel.getPlayerRadios()[1].setSelected(true);
            playerSelected = 1;
            controlPanel.getPlayerRadios()[playerSelected].setSelected(true);
        }
    }

    /**
     * Check if Players file exists
     * If so, then append the file data to the highScorePlayers array
     * @param fileName
     *          File name
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void checkPlayerFile(String fileName, boolean winner) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        Player playerFile;

        if(file.exists()) {
            if(serializeToDisk.readPlayer(fileName) != null) {
                playerFile = serializeToDisk.readPlayer(fileName);

                if (winner) {
                    highScorePlayer = getPlayers().get(playerSelected);
                } else {
                    highScorePlayer = playerFile;
                }

                controlPanel.highScorePlayerName.setText("Player " + (highScorePlayer.getId()+1) + ": " + highScorePlayer.getName());
                controlPanel.highScorePlayerScore.setText(highScorePlayer.getScore() + " rolls");
            } // If file data is not null
        } // If file exists
        else {
            if(winner) {
                highScorePlayer = getPlayers().get(playerSelected);
                controlPanel.highScorePlayerName.setText("Player " + (highScorePlayer.getId()+1) + ": " + highScorePlayer.getName());
                controlPanel.highScorePlayerScore.setText(""+highScorePlayer.getScore() + " rolls");
            } else {
//                controlPanel.highScorePlayerName.setText("No previous players yet!");
            }
        } // file doesn't exist
    }

    /**
     * Save Player data to disk using serialization class
     */
    private void savePlayerToDisk(boolean winner) {
        try {
            checkPlayerFile(fileName, winner);

            serializeToDisk.savePlayer(highScorePlayer, fileName);

        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (ClassNotFoundException nfE) {
            nfE.printStackTrace();
        }
    }

}
