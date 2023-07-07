package ohlot.account.domain;

public class MemberAccountNotFoundException extends RuntimeException {
    private static final String MSG = "요청하신 회원 계정 정보를 찾지 못했습니다.";

    public MemberAccountNotFoundException() {
        super(MSG);
    }
}
