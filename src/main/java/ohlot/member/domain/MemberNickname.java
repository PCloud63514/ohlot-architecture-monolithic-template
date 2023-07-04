package ohlot.member.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberNickname(
        String value
) implements Serializable {
    public MemberNickname {
        if (!StringUtils.hasText(value)) throw new InvalidMemberFormatException();
    }
}
