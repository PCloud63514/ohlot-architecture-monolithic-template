package ohlot.account.domain.exception;

public class InvalidUserAccountFormatException extends RuntimeException {
    private static final String MSG = "잘못된 계정 형식 입니다.";
    public InvalidUserAccountFormatException() {
        super(MSG);
    }
}
