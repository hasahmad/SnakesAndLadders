package model;

/**
 * Roll the die and get the random value
 */
public class Dice {

    private static int die;
    private static int count = 0;

    /**
     * Initialize the die integer.
     */
    public Dice() {
        die = 0;
    }

    /**
     *
     * set the die value to random integer between 1 and 6
     * Set the player count by 1
     * returns die value (random integer between 1 and 6)
     *
     * @param p
     *      player object
     * @return random integer between 1 and 6
     */
    public int roll(Player p) {
        die = (int)(Math.random() * 6) + 1;
        p.countScore(1); // Count player score
        return die;
    }

    /**
     * returns the die attribute
     *
     * @return the die attribute
     */
    public static int getDie() {
        return die;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Dice.count = count;
    }
}
