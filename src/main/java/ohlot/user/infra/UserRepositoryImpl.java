package ohlot.user.infra;

import lombok.RequiredArgsConstructor;
import ohlot.user.domain.UserRepository;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserNickname;
import ohlot.user.domain.model.UserPublicId;
import ohlot.user.domain.model.UserSecureId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findBy(UserPublicId publicId) {
        return userJpaRepository.findByPublicId(publicId.value())
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findBy(final UserSecureId secureId) {
        return userJpaRepository.findBySecureId(secureId.value())
                .map(UserEntity::toUser);
    }

    @Override
    public Page<User> findAllByNicknameContains(final String query, Integer display, Integer start) {
        final String str = StringUtils.hasText(query) ? query : "";
        final UserNickname userNickname = new UserNickname("%" + str + "%");
        return userJpaRepository.findByNicknameLike(userNickname.value(), PageRequest.of(Math.abs(start - 1), display))
                .map(UserEntity::toUser);
    }

    @Override
    public void save(final User user) {
        this.userJpaRepository.save(UserEntity.mapped(user));
    }
}
