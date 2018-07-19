package com.visual.app.api.core.exceptions;

public class EntityInUseException extends UserException {

    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String message, Throwable cause) {
        super(message, cause);
    }

}
