package controller;

import model.Player;

import java.util.List;

public interface BaseController {

    public void registerPlayers(String[] players);

    public List<Player> getPlayers();
}
