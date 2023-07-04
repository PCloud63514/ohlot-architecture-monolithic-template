package ohlot.member.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberPublicId(
        String value
) implements Serializable {
    public MemberPublicId {
        if (!StringUtils.hasText(value)) throw new InvalidMemberFormatException();
    }
}
