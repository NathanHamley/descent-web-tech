package de.nhamley.descentserver.lobby.exceptions;

public class HeroesFullException extends RuntimeException {

	public HeroesFullException() {
	}

	public HeroesFullException(String message) {
		super(message);
	}

	public HeroesFullException(Throwable cause) {
		super(cause);
	}

	public HeroesFullException(String message, Throwable cause) {
		super(message, cause);
	}

	public HeroesFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
