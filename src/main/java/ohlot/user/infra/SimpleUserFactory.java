package ohlot.user.infra;

import ohlot.user.domain.UserFactory;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserNickname;
import ohlot.user.domain.model.UserPublicId;
import ohlot.user.domain.model.UserSecureId;
import ohlot.user.domain.model.UserStateMessage;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Component
class SimpleUserFactory implements UserFactory {
    @Override
    public User createMember(final String nickname) {
        final UserNickname userNickname = new UserNickname(nickname);
        final UserSecureId userSecureId = obtainSecureId();
        final UserPublicId userPublicId = obtainPublicId();

        return User.builder()
                .secureId(userSecureId)
                .publicId(userPublicId)
                .nickname(userNickname)
                .stateMessage(new UserStateMessage(""))
                .build();
    }

    private UserSecureId obtainSecureId() {
        return new UserSecureId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    private UserPublicId obtainPublicId() {
        return new UserPublicId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }
}
