package de.nhamley.descentserver.game.dm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TileStatus {
	EMPTY(1, false), //
	PITS(3, false), //
	WATER(-1, false), //
	RUBBLE(-1, false), //
	ENCOUNTERS(1, false), //
	GLYPH_DEACTIVATED(1, false), //
	GLYPH_ACTIVATED(1, false), //
	STAIRCASE(1, false), //
	DOOR_CLOSED(0, true), //
	DOOR_OPEN(0, false); //

	private int movePointsToCross;

	private boolean blocksLineOfSight;
}
