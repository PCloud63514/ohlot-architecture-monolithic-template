package ohlot.account.domain;

import ohlot.account.domain.model.AccountSecureId;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;

import java.util.Optional;

public interface AccountRepository {
    boolean isLoginIdExists(final LoginId loginId);
    void save(final LoginAccount loginAccount);
    Optional<LoginAccount> findBy(LoginId loginId);
    AccountSecureId obtainSecureId();
}
