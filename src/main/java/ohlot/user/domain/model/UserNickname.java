package ohlot.user.domain.model;

import ohlot.user.domain.exception.InvalidUserFormatException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public record UserNickname(
        String value
) implements Serializable {
    public UserNickname {
        if (!StringUtils.hasText(value)) throw new InvalidUserFormatException();
    }
}
