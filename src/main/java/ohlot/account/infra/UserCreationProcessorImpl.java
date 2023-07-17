package ohlot.account.infra;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.UserCreationProcessor;
import ohlot.account.domain.model.AccountUserSecureId;
import ohlot.user.domain.UserFactory;
import ohlot.user.domain.UserRepository;
import ohlot.user.domain.model.User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class UserCreationProcessorImpl implements UserCreationProcessor {
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    @Override
    public AccountUserSecureId createUser(final String nickname) {
        final User user = userFactory.createMember(nickname);
        userRepository.save(user);
        return new AccountUserSecureId(user.getSecureId().value());
    }
}
