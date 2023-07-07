package ohlot.account.domain;

public class InvalidPasswordException extends RuntimeException {
    private static final String MSG = "잘못된 패스워드를 입력하였습니다.";

    public InvalidPasswordException() {
        super(MSG);
    }
}
