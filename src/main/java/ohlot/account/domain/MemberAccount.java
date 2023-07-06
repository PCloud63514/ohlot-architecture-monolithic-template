package ohlot.account.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberAccount {
    private final MemberIdentityToken identityToken;
    private final MemberLoginId loginId;
    private final MemberPassword password;

    @Builder
    public MemberAccount(final MemberIdentityToken identityToken, final MemberLoginId loginId, final MemberPassword password) {
        if (null == identityToken || null == loginId || null == password) throw new InvalidMemberAccountFormatException();
        this.identityToken = identityToken;
        this.loginId = loginId;
        this.password = password;
    }
}
