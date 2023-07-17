package ohlot.account.domain.exception;

public class AccountNotFoundException extends RuntimeException {
    private static final String MSG = "요청한 계정 정보를 찾지 못했습니다.";

    public AccountNotFoundException() {
        super(MSG);
    }
}
