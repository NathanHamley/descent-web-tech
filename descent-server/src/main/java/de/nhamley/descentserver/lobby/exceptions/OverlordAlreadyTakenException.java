package de.nhamley.descentserver.lobby.exceptions;

public class OverlordAlreadyTakenException extends RuntimeException {

	public OverlordAlreadyTakenException() {
	}

	public OverlordAlreadyTakenException(String message) {
		super(message);
	}

	public OverlordAlreadyTakenException(Throwable cause) {
		super(cause);
	}

	public OverlordAlreadyTakenException(String message, Throwable cause) {
		super(message, cause);
	}

	public OverlordAlreadyTakenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
