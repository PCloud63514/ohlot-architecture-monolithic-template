package ohlot.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private final MemberSecureId secureId;
    private final MemberPublicId publicId;
    private MemberNickname nickname;
    private MemberStateMessage stateMessage;

    @Builder
    private Member(final MemberSecureId secureId, final MemberPublicId publicId, final MemberNickname nickname, final MemberStateMessage stateMessage) {
        if (null == secureId || null == publicId) throw new InvalidMemberFormatException();
        if (null == nickname || null == stateMessage) throw new InvalidMemberFormatException();
        this.secureId = secureId;
        this.publicId = publicId;
        this.nickname = nickname;
        this.stateMessage = stateMessage;
    }

    public static Member generateSignUpInstance(final MemberSecureId memberSecureId,
                                                final MemberPublicId memberPublicId,
                                                final String nickname) {
        final MemberNickname memberNickname = new MemberNickname(nickname);
        return builder()
                .secureId(memberSecureId)
                .publicId(memberPublicId)
                .nickname(memberNickname)
                .stateMessage(new MemberStateMessage(""))
                .build();
    }

    public void changeNickname(final String _nickname) {
        this.nickname = new MemberNickname(_nickname);
    }

    public void changeStateMessage(final String _memberStatMessage) {
        this.stateMessage = new MemberStateMessage(_memberStatMessage);
    }
}
