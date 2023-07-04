package ohlot.member.domain;

public class MemberNotFoundException extends RuntimeException {
    private static final String MSG = "요청한 회원정보가 존재하지 않습니다.";

    public MemberNotFoundException() {
        super(MSG);
    }
}
