package ohlot.user.domain.model;

import ohlot.user.domain.exception.InvalidUserFormatException;

import java.io.Serializable;

public record UserStateMessage(
        String value
) implements Serializable {
    private static final int MAX_LENGTH = 50;
    public UserStateMessage {
        if (null == value) throw new InvalidUserFormatException();
        if (MAX_LENGTH < value.length()) throw new InvalidUserFormatException();
    }
}
