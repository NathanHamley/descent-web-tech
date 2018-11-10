package de.nhamley.descentserver.lobby.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import de.nhamley.descentserver.lobby.dm.GameLobby;
import de.nhamley.descentserver.lobby.dm.Player;
import de.nhamley.descentserver.lobby.exceptions.GameNameAlreadyTakenException;
import de.nhamley.descentserver.lobby.exceptions.GameNonExistentException;
import de.nhamley.descentserver.lobby.exceptions.HeroesFullException;
import de.nhamley.descentserver.lobby.exceptions.OverlordAlreadyTakenException;

@Component
public class GameFindService {

	private static final int MAX_HEROES = 4;

	private Map<String, GameLobby> lobbies = new HashMap<>();

	public Collection<GameLobby> getAllLobbies() {
		return lobbies.values();
	}

	public GameLobby getLobby(String name) {
		return lobbies.get(name);
	}

	public GameLobby createGame(String name, Player overlord, List<Player> heroes) {
		GameLobby newLobby = new GameLobby();
		if (name == null || name.isEmpty()) {
			name = generateFakeName();
		}
		if (lobbies.containsKey(name)) {
			throw new GameNameAlreadyTakenException("The name: " + name + " is already in use.");
		}
		newLobby.setName(name);
		if (overlord != null) {
			newLobby.setOverlord(overlord);
		}
		if (heroes != null && !heroes.isEmpty()) {
			newLobby.getHeroes().addAll(heroes);
		}
		lobbies.put(name, newLobby);
		return newLobby;
	}

	public GameLobby addPlayersToLobby(String name, Player overlord, List<Player> heroes) {
		GameLobby lobby = lobbies.get(name);
		if (lobby == null) {
			throw new GameNonExistentException("There is no game called: " + name);
		}
		if (overlord != null) {
			if (lobby.getOverlord() != null) {
				throw new OverlordAlreadyTakenException();
			}
			lobby.setOverlord(overlord);
		}
		if (heroes != null && !heroes.isEmpty()) {
			if (lobby.getHeroes() == null) {
				lobby.setHeroes(new ArrayList<>());
			}
			if (lobby.getHeroes().size() + heroes.size() > MAX_HEROES) {
				throw new HeroesFullException("The maximum amount of heroes are already taken");
			}
			lobby.getHeroes().addAll(heroes);
		}
		return lobby;
	}

	private String generateFakeName() {
		Faker faker = new Faker();
		String fakeName = null;
		do {
			fakeName = faker.leagueOfLegends().champion();
		} while (lobbies.containsKey(fakeName));
		return fakeName;
	}

}
