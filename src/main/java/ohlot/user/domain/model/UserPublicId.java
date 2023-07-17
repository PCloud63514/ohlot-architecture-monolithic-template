package ohlot.user.domain.model;

import ohlot.user.domain.exception.InvalidUserFormatException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public record UserPublicId(
        String value
) implements Serializable {
    public UserPublicId {
        if (!StringUtils.hasText(value)) throw new InvalidUserFormatException();
    }
}
