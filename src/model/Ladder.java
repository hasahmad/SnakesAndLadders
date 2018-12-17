package model;


/**
 * Used to check if players current position is on tail of ladder
 * If so, the set player current position to the head of ladder
 */
public class Ladder {

    public Ladder() {
    }

    /**
     * Check if players current position is on tail of ladder
     * If so, the set player current position to the head of ladder
     * Else don't do anything
     *
     * @param player
     *          player object
     */
    public void checkLadder(Player player) {
        switch (player.getCurrentPosition()) {
            case 4:
                player.setCurrentPosition(25);
                break;
            case 13:
                player.setCurrentPosition(46);
                break;
            case 33:
                player.setCurrentPosition(49);
                break;
            case 42:
                player.setCurrentPosition(63);
                break;
            case 50:
                player.setCurrentPosition(79);
                break;
            case 62:
                player.setCurrentPosition(81);
                break;
            case 74:
                player.setCurrentPosition(92);
                break;
            default:
                break;
        }
    }
}
