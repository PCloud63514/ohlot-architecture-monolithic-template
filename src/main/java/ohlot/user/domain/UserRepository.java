package ohlot.user.domain;

import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserPublicId;
import ohlot.user.domain.model.UserSecureId;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findBy(final UserPublicId publicId);
    Optional<User> findBy(final UserSecureId secureId);

    Page<User> findAllByNicknameContains(final String query, Integer display, Integer start);

    void save(final User user);
}
