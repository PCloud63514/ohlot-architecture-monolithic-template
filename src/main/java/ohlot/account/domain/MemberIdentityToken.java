package ohlot.account.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberIdentityToken(
        String value
) implements Serializable {
    public MemberIdentityToken {
        if (!StringUtils.hasText(value)) throw new InvalidMemberAccountFormatException();
    }
}
