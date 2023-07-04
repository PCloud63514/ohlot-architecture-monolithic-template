package ohlot.member.domain;

import java.io.Serializable;

public record MemberStateMessage(
        String value
) implements Serializable {
    private static final int MAX_LENGTH = 50;
    public MemberStateMessage {
        if (null == value) throw new InvalidMemberFormatException();
        if (MAX_LENGTH < value.length()) throw new InvalidMemberFormatException();
    }
}
