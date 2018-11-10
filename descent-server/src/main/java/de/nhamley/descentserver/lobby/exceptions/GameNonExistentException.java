package de.nhamley.descentserver.lobby.exceptions;

public class GameNonExistentException extends RuntimeException {

	public GameNonExistentException() {
	}

	public GameNonExistentException(String message) {
		super(message);
	}

	public GameNonExistentException(Throwable cause) {
		super(cause);
	}

	public GameNonExistentException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameNonExistentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
