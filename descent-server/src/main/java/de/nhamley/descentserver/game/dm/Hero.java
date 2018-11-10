package de.nhamley.descentserver.game.dm;

import lombok.Data;

@Data
public class Hero {

	private int health;

	private int fatigue;

	private int armor;

	private int speed;

	private int meleeTrait;

	private int rangedTrait;

	private int magicTrait;

	private int fightingSkills;

	private int subterfugeSkills;

	private int spellcastingSkills;

	private int conquestValue;

}
