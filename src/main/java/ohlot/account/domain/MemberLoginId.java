package ohlot.account.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberLoginId(
        String value
) implements Serializable {
    public MemberLoginId {
        if (!StringUtils.hasText(value)) throw new InvalidMemberAccountFormatException();
    }
}
