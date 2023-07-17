package ohlot.user.domain.exception;

public class InvalidUserFormatException extends RuntimeException {
    private static final String MSG = "사용자 정보가 잘못되었습니다.";

    public InvalidUserFormatException() {
        super(MSG);
    }
}
