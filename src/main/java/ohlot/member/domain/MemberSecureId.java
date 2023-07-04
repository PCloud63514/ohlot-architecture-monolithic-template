package ohlot.member.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberSecureId(
        String value
) implements Serializable {
    public MemberSecureId {
        if (!StringUtils.hasText(value)) throw new InvalidMemberFormatException();
    }
}
