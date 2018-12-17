package model;

/**
 * Interface class for Model
 */
public interface BaseModel {

    /**
     * returns the database object
     * @return database object
     */
    public Database getDatabaseModel();

    /**
     * returns player object
     * @param name
     *          player name
     * @return player object
     */
    public Player getPlayerModel(String name);

    /**
     * returns dice object
     * @return dice object
     */
    public Dice getDiceModel();

    /**
     * returns snake object
     * @return snake object
     */
    public Snake getSnakesModel();

    /**
     * returns ladder object
     * @return ladder object
     */
    public Ladder getLadderModel();
}
