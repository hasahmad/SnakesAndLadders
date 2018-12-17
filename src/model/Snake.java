package model;

/**
 * Used to check if players current position is on head of snake
 * If so, the set player current position to the tail of snake
 */
public class Snake {

    /**
     * Check if players current position is on head of snake
     * If so, the set player current position to the tail of snake
     *
     * @param player
     *          player object
     */
    public void checkSnake(Player player) {
        switch (player.getCurrentPosition()) {
            case 27:
                player.setCurrentPosition(5);
                break;
            case 40:
                player.setCurrentPosition(3);
                break;
            case 43:
                player.setCurrentPosition(18);
                break;
            case 54:
                player.setCurrentPosition(31);
                break;
            case 66:
                player.setCurrentPosition(45);
                break;
            case 76:
                player.setCurrentPosition(58);
                break;
            case 89:
                player.setCurrentPosition(53);
                break;
            case 99:
                player.setCurrentPosition(41);
                break;
            default:
                break;
        }
    }
}
