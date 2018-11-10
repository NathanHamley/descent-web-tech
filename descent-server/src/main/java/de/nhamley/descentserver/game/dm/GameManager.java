package de.nhamley.descentserver.game.dm;

import java.util.List;

public class GameManager {

	private OverlordPlayer overlord;

	private List<HeroPlayer> heroes;

	public void setUpGame() {
		for (HeroPlayer hero : heroes) {
			hero.setUp();
		}
		initGameBoard();

	}
	
	public void startGame() {
		
	}
	
	private void singleTurn() {
		
	}

	private void initGameBoard() {

	}

}
