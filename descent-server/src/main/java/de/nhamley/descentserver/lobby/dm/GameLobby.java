package de.nhamley.descentserver.lobby.dm;

import java.util.List;

import lombok.Data;

@Data
public class GameLobby {
	
	private String name;

	private List<Player> heroes;

	private Player overlord;
	
	

}
