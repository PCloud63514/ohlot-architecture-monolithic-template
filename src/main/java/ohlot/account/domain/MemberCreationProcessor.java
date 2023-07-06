package ohlot.account.domain;

public interface MemberCreationProcessor {
    MemberIdentityToken createMember(String nickname);
}
