package ohlot.account.domain;

import java.util.Optional;

public interface AccountRepository {
    boolean isLoginIdExists(final MemberLoginId loginId);
    void save(final MemberAccount account);
    Optional<MemberAccount> findBy(MemberLoginId loginId);
}
