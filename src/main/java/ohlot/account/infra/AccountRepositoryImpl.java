package ohlot.account.infra;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class AccountRepositoryImpl implements AccountRepository {
    private final MemberAccountEntityRepository memberAccountEntityRepository;

    @Override
    public void save(final LoginAccount loginAccount) {
        final AccountEntity entity = AccountEntity.mapped(loginAccount);
        memberAccountEntityRepository.save(entity);
    }

    @Override
    public Optional<LoginAccount> findBy(final LoginId loginId) {
        return Optional.empty();
    }

    @Override
    public boolean isLoginIdExists(final LoginId loginId) {
        return memberAccountEntityRepository.existsByLoginId(loginId.value());
    }
}
