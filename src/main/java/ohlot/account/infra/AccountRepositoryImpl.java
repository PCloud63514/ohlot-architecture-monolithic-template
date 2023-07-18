package ohlot.account.infra;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.model.AccountSecureId;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

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
    public AccountSecureId obtainSecureId() {
        return new AccountSecureId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public boolean isLoginIdExists(final LoginId loginId) {
        return memberAccountEntityRepository.existsByLoginId(loginId.value());
    }
}
