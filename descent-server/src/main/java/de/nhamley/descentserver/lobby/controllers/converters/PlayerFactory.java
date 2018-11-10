package de.nhamley.descentserver.lobby.controllers.converters;

import java.util.ArrayList;
import java.util.List;

import de.nhamley.descentserver.lobby.dm.Player;

public class PlayerFactory {

	private PlayerFactory() {
		// only static methods, so no initialization
	}

	public static Player createPlayer(String name) {
		if(name == null) {
			return null;
		}
		Player player = new Player();
		player.setName(name);
		return player;
	}

	public static List<Player> createPlayers(List<String> names) {
		if (names == null) {
			return null;
		}
		List<Player> players = new ArrayList<>();
		for (String name : names) {
			players.add(createPlayer(name));
		}
		return players;
	}

}
