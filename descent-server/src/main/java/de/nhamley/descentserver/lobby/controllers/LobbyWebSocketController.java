package de.nhamley.descentserver.lobby.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import de.nhamley.descentserver.lobby.controllers.converters.PlayerFactory;
import de.nhamley.descentserver.lobby.controllers.dtos.LobbyDto;
import de.nhamley.descentserver.lobby.dm.GameLobby;
import de.nhamley.descentserver.lobby.services.GameFindService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LobbyWebSocketController {

	@Autowired
	private GameFindService gameFindService;

	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@MessageMapping("/createGame")
	// @SendTo("/topic/lobbies")
	public void createGameLobby(LobbyDto newLobbyDto) {
		log.debug("Received wish to createNewLobby: " + newLobbyDto);
		GameLobby newLobby = gameFindService.createGame(newLobbyDto.getName(),
				PlayerFactory.createPlayer(newLobbyDto.getOverlord()),
				PlayerFactory.createPlayers(newLobbyDto.getPlayers()));
		log.debug("Returning lobby: {}", newLobby);
		messageTemplate.convertAndSend("/topic/lobbies", newLobby);
	}

	@MessageMapping("/joinGame")
	// @SendTo({ "/topic/lobbies" })
	public void joinGameLobby(LobbyDto newLobbyDto) {
		log.debug("Received wish to join lobby: {}", newLobbyDto);
		GameLobby existingLobby = gameFindService.addPlayersToLobby(newLobbyDto.getName(),
				PlayerFactory.createPlayer(newLobbyDto.getOverlord()),
				PlayerFactory.createPlayers(newLobbyDto.getPlayers()));
		log.debug("Returning lobby: {}", existingLobby);
		messageTemplate.convertAndSend("/topic/lobbies", existingLobby);
		messageTemplate.convertAndSend("/topic/lobbies/" + existingLobby.getName(), existingLobby);
	}

}
