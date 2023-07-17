package ohlot.account.domain.model;

import lombok.Builder;
import lombok.Getter;
import ohlot.account.domain.exception.InvalidPasswordException;
import ohlot.account.domain.exception.InvalidUserAccountFormatException;

@Getter
public class LoginAccount {
    private final AccountSecureId accountSecureId;
    private final AccountUserSecureId userSecureId;
    private final LoginId loginId;
    private final LoginPassword password;

    @Builder
    public LoginAccount(final AccountSecureId secureId, final AccountUserSecureId userSecureId, final LoginId loginId, final LoginPassword password) {
        if (null == secureId || null == userSecureId) throw new InvalidUserAccountFormatException();
        if (null == loginId || null == password) throw new InvalidUserAccountFormatException();
        this.accountSecureId = secureId;
        this.userSecureId = userSecureId;
        this.loginId = loginId;
        this.password = password;
    }

    public AccountUserSecureId authenticate(final LoginPassword loginPassword) {
        if (!this.password.equals(loginPassword)) throw new InvalidPasswordException();
        return this.userSecureId;
    }
}
