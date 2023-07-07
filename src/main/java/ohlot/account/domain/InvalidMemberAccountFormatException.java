package ohlot.account.domain;

public class InvalidMemberAccountFormatException extends RuntimeException {
    private static final String MSG = "잘못된 계정 형식 입니다.";
    public InvalidMemberAccountFormatException() {
        super(MSG);
    }
}
