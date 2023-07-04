package ohlot.member.domain;

public class InvalidMemberFormatException extends RuntimeException {
    private static final String MSG = "회원 정보가 잘못되었습니다.";

    public InvalidMemberFormatException() {
        super(MSG);
    }
}
