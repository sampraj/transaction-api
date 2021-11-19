package api.rest.domain;

import api.rest.dto.Player;

import java.util.List;

public class InitializeData {

    private List<Player> players;
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
