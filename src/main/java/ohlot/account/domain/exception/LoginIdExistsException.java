package ohlot.account.domain.exception;

public class LoginIdExistsException extends RuntimeException {
    private static final String MSG = "이미 존재하는 아이디 입니다.";

    public LoginIdExistsException() {
        super(MSG);
    }
}
