package ohlot.account.domain.model;

import ohlot.account.domain.exception.InvalidUserAccountFormatException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public record AccountUserSecureId(
        String value
) implements Serializable {
    public AccountUserSecureId {
        if (!StringUtils.hasText(value)) throw new InvalidUserAccountFormatException();
    }
}
