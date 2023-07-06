package ohlot.account.domain;

public interface AccountRepository {
    boolean isLoginIdExists(final MemberLoginId loginId);
}
