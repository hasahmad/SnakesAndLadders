package model;

import java.io.*;


/**
 * Saves data to disk
 */
public class SerializeToDisk {

    public SerializeToDisk() {
    }

    /**
     * Save Winning player to disk
     * @param player
     *          Player object
     * @param fileName
     *          file name
     * @throws IOException
     *      throws io exception
     */
    public void savePlayer(Player player, String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(player);
        oos.close();
    }

    /**
     * Read file and return the player in the file.
     * @param file
     *          file name
     * @return the player in the file
     * @throws IOException
     *      throws io exception
     * @throws ClassNotFoundException
     *      throws class not found exception
     */
    public Player readPlayer(String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Player player = (Player) ois.readObject();
        ois.close();
        return player;
    }
}
