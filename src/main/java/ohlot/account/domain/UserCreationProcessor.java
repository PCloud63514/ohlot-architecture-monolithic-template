package ohlot.account.domain;

import ohlot.account.domain.model.AccountUserSecureId;

public interface UserCreationProcessor {
    AccountUserSecureId createUser(String nickname);
}
