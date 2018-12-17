package model;

/**
 * class that represents the model
 */
public class Model implements BaseModel {

    /**
     * returns the database object
     * @return database object
     */
    @Override
    public Database getDatabaseModel() {
        return new Database();
    }

    /**
     * returns player object
     * @param name
     *          player name
     * @return player object
     */
    @Override
    public Player getPlayerModel(String name) {
        return new Player(name);
    }

    /**
     * returns dice object
     * @return dice object
     */
    @Override
    public Dice getDiceModel() {
        return new Dice();
    }

    /**
     * returns snake object
     * @return snake object
     */
    @Override
    public Snake getSnakesModel() {
        return new Snake();
    }

    /**
     * returns ladder object
     * @return ladder object
     */
    @Override
    public Ladder getLadderModel() {
        return new Ladder();
    }
}
