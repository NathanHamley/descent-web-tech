package de.nhamley.descentserver.lobby.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.nhamley.descentserver.lobby.dm.GameLobby;
import de.nhamley.descentserver.lobby.services.GameFindService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LobbyRestController {

	@Autowired
	private GameFindService gameFindService;

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(path = "/lobbies/all", produces = "application/json")
	public Collection<GameLobby> getExistingLobbies() {
		log.debug("Received Request to find all open lobbies");
		return gameFindService.getAllLobbies();
	}

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(path = "/lobbies/{name}", produces = "application/json")
	public GameLobby getLobby(@PathVariable("name") String name) {
		log.debug("Received request for gameLobby: " + name);
		GameLobby lobby = gameFindService.getLobby(name);
		log.debug("Returning: " + lobby);
		return lobby;
	}

}
