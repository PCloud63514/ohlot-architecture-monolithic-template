package ohlot.user.domain;

import ohlot.user.domain.model.User;

public interface UserFactory {
    User createMember(final String nickname);
}
