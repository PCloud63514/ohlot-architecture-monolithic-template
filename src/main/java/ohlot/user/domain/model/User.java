package ohlot.user.domain.model;

import lombok.Builder;
import lombok.Getter;
import ohlot.user.domain.exception.InvalidUserFormatException;

@Getter
public class User {
    private final UserSecureId secureId;
    private final UserPublicId publicId;
    private UserNickname nickname;
    private UserStateMessage stateMessage;

    @Builder
    private User(final UserSecureId secureId, final UserPublicId publicId, final UserNickname nickname, final UserStateMessage stateMessage) {
        if (null == secureId || null == publicId) throw new InvalidUserFormatException();
        if (null == nickname || null == stateMessage) throw new InvalidUserFormatException();
        this.secureId = secureId;
        this.publicId = publicId;
        this.nickname = nickname;
        this.stateMessage = stateMessage;
    }

    public static User generateSignUpInstance(final UserSecureId userSecureId,
                                              final UserPublicId userPublicId,
                                              final String nickname) {
        final UserNickname userNickname = new UserNickname(nickname);
        return builder()
                .secureId(userSecureId)
                .publicId(userPublicId)
                .nickname(userNickname)
                .stateMessage(new UserStateMessage(""))
                .build();
    }

    public void changeInfo(final String _nickname, final String _stateMessage) {
        this.nickname = new UserNickname(_nickname);
        this.stateMessage = new UserStateMessage(_stateMessage);
    }
}
