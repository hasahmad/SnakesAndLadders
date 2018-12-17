package model;

import model.helpers.PlayerNotAddedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Database class for storing player data
 * Stores data in a list of Player Objects
 */
public class Database {

    private List<Player> players;

    /**
     * Initialize Players to new ArrayList of Player objects
     */
    public Database() {
        this.players = new ArrayList<Player>();
    }

    /**
     * Add players to the list of players
     *
     * @param p
     *      player object
     *
     * @throws PlayerNotAddedException
     *          if player param is null
     */
    public void addPlayers(Player p) throws PlayerNotAddedException {
        if(p == null){
            throw new PlayerNotAddedException(String.format("Player %s not added!", p));
        }
        this.players.add(p);
    }

    /**
     * returns the list of players stored
     *
     * @return list of players stored
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * clears the list of players
     */
    public void reset() {
        this.players.clear();
    }

    /**
     * clear each player's current and previous position
     */
    public void resetPositions() {
        for (Player player: players) {
            player.resetPositions();
        }
    }
}
