package ohlot.user.domain.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MSG = "사용자 정보가 존재하지 않습니다.";

    public UserNotFoundException() {
        super(MSG);
    }
}
