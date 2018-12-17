package model;

import java.util.Objects;

/**
 * A class that represents the player
 */
public class Player implements java.io.Serializable {

    private static int count = 0;

    private int id;
    private String name;
    private int score = 0;
    private int prevPosition = 0;
    private int currentPosition = 0;
    private boolean selected;

    /**
     * Instatiate the player object
     * set name of the parameter to name
     * Increment id of player, starting from 0
     *
     * @param name
     *          player name
     */
    public Player(String name) {
        this.setName(name);
        this.setSelected(false);

        this.id = count;
        count++; // starting from 0
    }

    /**
     * returns the id of player
     * @return id of player
     */
    public int getId() {
        return id;
    }

    /**
     * returns name of player
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * set the player name to the parameter
     * @param name
     *      player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the score of player
     * @return score of player
     */
    public int getScore() {
        return score;
    }

    /**
     * Set score of player
     * @param score
     *          player score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void countScore(int score) {
        this.score += score;
    }

    /**
     * return previous position of player
     * @return previous position of player
     */
    public int getPrevPosition() {
        return prevPosition;
    }

    /**
     * return true if player is selected
     * @return true if player is selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * set previous position of player
     * @param prevPosition
     *          player previous position
     */
    public void setPrevPosition(int prevPosition) {
        this.prevPosition = prevPosition;
    }

    /**
     * return current position of player
     * @return current position of player
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * current position of player and increment the score value by it
     * @param currentPosition
     *          player current position
     */
    public void setCurrentPosition(int currentPosition) {
//        this.score += Dice.getDie();
        this.currentPosition = currentPosition;
    }

    /**
     * Set boolean selected to player
     * @param selected
     *          selected player (true/false)
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * reset the player positions and score to zero
     */
    public void resetPositions() {
        this.setPrevPosition(0);
        this.setCurrentPosition(0);
        Dice.setCount(0);
        this.setScore(0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.score, this.prevPosition, this.currentPosition, this.selected);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;

        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.score != other.score) {
            return false;
        }
        if (this.prevPosition != other.prevPosition) {
            return false;
        }
        if (this.currentPosition != other.currentPosition) {
            return false;
        }
        if (this.selected != other.selected) {
            return false;
        }

        return true;
    }

    /**
     * Returns Player information (id, name, current position, and score)
     * @return Player information (id, name, current position, and score)
     */
    public String toString() {

        String result = String.format("[Player: id=%d, name=%s, currentPosition=%d, diceRollCount=%d]\n",
                                this.id, this.name, this.currentPosition, this.score);
        return result;
    }
}

