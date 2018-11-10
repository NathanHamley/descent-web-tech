package de.nhamley.descentserver.lobby.controllers.dtos;

import java.util.List;

import lombok.Data;

@Data
public class LobbyDto {

	private String name;

	private String overlord;

	private List<String> players;

}
