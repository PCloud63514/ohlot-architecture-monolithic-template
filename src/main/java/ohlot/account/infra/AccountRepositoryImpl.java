package ohlot.account.infra;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberAccount;
import ohlot.account.domain.MemberLoginId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class AccountRepositoryImpl implements AccountRepository {
    private final MemberAccountEntityRepository memberAccountEntityRepository;

    @Override
    public void save(final MemberAccount account) {
        final MemberAccountEntity entity = MemberAccountEntity.mapped(account);
        memberAccountEntityRepository.save(entity);
    }

    @Override
    public Optional<MemberAccount> findBy(final MemberLoginId loginId) {
        return Optional.empty();
    }

    @Override
    public boolean isLoginIdExists(final MemberLoginId loginId) {
        return memberAccountEntityRepository.existsByLoginId(loginId.value());
    }
}
