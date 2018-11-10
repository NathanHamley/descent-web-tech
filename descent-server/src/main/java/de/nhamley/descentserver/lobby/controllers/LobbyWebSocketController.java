package de.nhamley.descentserver.lobby.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

	public LobbyWebSocketController() {
		System.out.println("Lobby Created");
	}

	@Autowired
	private GameFindService gameFindService;

	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@MessageMapping("/createLobby")
	@SendTo("/topic/lobbies")
	public GameLobby createGameLobby(LobbyDto newLobbyDto) {
		log.info("Received wish to createNewLobby: " + newLobbyDto);
		GameLobby newLobby = gameFindService.createGame(newLobbyDto.getName(),
				PlayerFactory.createPlayer(newLobbyDto.getOverlord()),
				PlayerFactory.createPlayers(newLobbyDto.getPlayers()));
		log.info("Returning lobby: {}", newLobby);
		return newLobby;
	}

	@MessageMapping("/joinGame")
	@SendTo({ "/topic/lobbies" })
	public GameLobby joinGameLobby(LobbyDto newLobbyDto) {
		log.info("Received wish to join lobby: {}", newLobbyDto);
		GameLobby newLobby = gameFindService.addPlayersToLobby(newLobbyDto.getName(),
				PlayerFactory.createPlayer(newLobbyDto.getOverlord()),
				PlayerFactory.createPlayers(newLobbyDto.getPlayers()));
		log.info("Returning lobby: {}", newLobby);
		messageTemplate.convertAndSend("/topic/lobbies/" + newLobby.getName(), newLobby);
		return newLobby;
	}

}
