package de.nhamley.descentserver.lobby.exceptions;

public class GameNameAlreadyTakenException extends RuntimeException {

	public GameNameAlreadyTakenException() {
		super();
	}

	public GameNameAlreadyTakenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameNameAlreadyTakenException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameNameAlreadyTakenException(String message) {
		super(message);
	}

	public GameNameAlreadyTakenException(Throwable cause) {
		super(cause);
	}

}
