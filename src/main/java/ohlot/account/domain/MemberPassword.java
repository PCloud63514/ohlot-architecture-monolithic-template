package ohlot.account.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberPassword(
        String value
) implements Serializable {

    public MemberPassword {
        if (!StringUtils.hasText(value)) throw new InvalidMemberAccountFormatException();
    }
}
