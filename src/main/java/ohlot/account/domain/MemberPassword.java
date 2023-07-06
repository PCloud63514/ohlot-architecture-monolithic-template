package ohlot.account.domain;

import org.springframework.util.StringUtils;

public record MemberPassword(
        String value
) {

    public MemberPassword {
        if (!StringUtils.hasText(value)) throw new InvalidMemberAccountFormatException();
    }
}
