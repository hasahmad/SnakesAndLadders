package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JRadioButton playerRadios[];
    private JTextField playerNames[];
    private ButtonGroup playerRadioGroup;

    private JLabel diceLabel;
    private JButton rollDiceBtn;
    private JButton moveBackBtn;
    private JButton moveForwardBtn;
    public JButton newGameBtn;
    private JButton rulesBtn;
    private JButton exitBtn;
    public JLabel highScorePlayerName;
    public JLabel highScorePlayerScore;

    private static String playerIcon;

    public ControlPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);

        setLayout(new BorderLayout());

        initialize();
        createView();
    }

    private void initialize() {
        playerRadios = new JRadioButton[2];
        playerNames = new JTextField[2];
        highScorePlayerName = new JLabel();
        highScorePlayerScore = new JLabel();

        for(int i=0; i<playerNames.length; i++) {
            playerRadios[i] = new JRadioButton();
            playerNames[i] = new JTextField(10);
            playerNames[i].setEditable(false);
        }

        playerRadioGroup = new ButtonGroup();

        diceLabel = new JLabel("");
        rollDiceBtn = new JButton("Roll Dice");
        moveBackBtn = new JButton("Move Back");
        moveForwardBtn = new JButton("Move Forward");
        newGameBtn = new JButton("New Game");
        rulesBtn = new JButton("Game Rules");
        exitBtn = new JButton("Exit");

        /**
         * Setup Buttons
         */
        rollDiceBtn.setToolTipText("Click the button to roll dice");
        moveBackBtn.setToolTipText("Click the button to move backward");
        moveForwardBtn.setToolTipText("Click the button to move forward");
    }

    private void createView() {
        /**
         * Setup Players Radio Buttons
         */
        playerRadioGroup.add(playerRadios[0]);
        playerRadioGroup.add(playerRadios[1]);
        playerRadios[0].setSelected(true);

        /**
         * Setup Forward and Backward Buttons
         */
        moveForwardBtn.setEnabled(false);
        moveBackBtn.setEnabled(false);

        /**
         * Setup Layout and components
         */
        Border innerBorder = BorderFactory.createTitledBorder("Game Controls");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        /**
         * Setup Dice
         */
        diceLabel.setPreferredSize(new Dimension(49, 49));

        /**
         * Players Labels and Fields
         */
        for(int i=0; i<playerNames.length; i++) {

            /**
             * Player Label
             */
            if(i > 0) {
                gc.gridy++;
            } else {
                gc.gridy = 0;
            }

            gc.weightx = 1;
            gc.weighty = 0.0;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
            add(new JLabel("Player "+(i+1)), gc);

            /**
             * Player Fields
             */
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx = 0;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
            add(playerRadios[i], gc);

            gc.gridx = 1;
            gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
            gc.anchor = GridBagConstraints.LINE_START;
            add(playerNames[i], gc);
        }

        /**
         * Next Row
         * Roll dice button and dice number
         */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(rollDiceBtn, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(diceLabel, gc);

        /**
         * Next Row
         * Move forward and backward buttons
         */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(moveBackBtn, gc);

        gc.gridx = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(moveForwardBtn, gc);

        /**
         * Next Row
         * Player High Score Label
         */

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
        add(new JLabel("<html><br><b>Previous Winner</b><br></html>"), gc);

        /**
         * High Score Player
         */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5); // padding(top, left, bottom, right)
        add(highScorePlayerName, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.LINE_START;
        add(highScorePlayerScore, gc);

        /**
         * Next Row
         * Empty Space
         */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.3;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.CENTER;
        add(new JLabel(""), gc);

        /**
         * Next Row
         * New Game Button
         */
//        gc.gridy++;
//
//        gc.weightx = 1;
//        gc.weighty = 0.02;
//
//        gc.gridx = 1;
//        gc.fill = GridBagConstraints.BOTH;
//        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        add(newGameBtn, gc);

        /**
         * Next Row
         * Move forward and backward buttons
         */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(rulesBtn, gc);

        gc.gridx = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(exitBtn, gc);

        /**
         * Next Row
         * Empty Space
         */
//        gc.gridy++;
//
//        gc.weightx = 1;
//        gc.weighty = 0.3;
//
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 0, 0); // padding(top, left, bottom, right)
//        gc.anchor = GridBagConstraints.CENTER;
//        add(new JLabel(""), gc);
    }

    public void setPlayerIcon(String icon) {
        playerIcon = icon;
    }

    public void setPlayerName(String name, int index) {
        playerNames[index].setText(name);
    }

    public void setDiceLabel(String dice) {
        diceLabel.setText(dice);
    }

    public void setDiceNumberIcon(int dice) {
        switch (dice) {
            case 1:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice1.png")));
                break;
            case 2:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice2.png")));
                break;
            case 3:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice3.png")));
                break;
            case 4:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice4.png")));
                break;
            case 5:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice5.png")));
                break;
            case 6:
                diceLabel.setIcon(new ImageIcon(getClass().getResource("/resources/pics/dice6.png")));
                break;
            default:
                break;
        }
    }

    public JRadioButton[] getPlayerRadios() {
        return playerRadios;
    }

    public String getPlayerIcon() {
        return playerIcon;
    }

    public boolean isRollDiceBtnEnabled() {
        return rollDiceBtn.isEnabled();
    }

    public void enableDiceForwardBackBtn(boolean dice, boolean forward, boolean back) {
        rollDiceBtn.setEnabled(dice);
        moveForwardBtn.setEnabled(forward);
        moveBackBtn.setEnabled(back);
    }

    public void addRollDiceListener(ActionListener listener) {
        rollDiceBtn.addActionListener(listener);
    }
    public void addMoveForwardListener(ActionListener listener) {
        moveForwardBtn.addActionListener(listener);
    }
    public void addGoBackListener(ActionListener listener) {
        moveBackBtn.addActionListener(listener);
    }
    public void addNewGameListener(ActionListener listener) {
        newGameBtn.addActionListener(listener);
    }
    public void addRulesListener(ActionListener listener) {
        rulesBtn.addActionListener(listener);
    }
    public void addExitListener(ActionListener listener) {
        exitBtn.addActionListener(listener);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
