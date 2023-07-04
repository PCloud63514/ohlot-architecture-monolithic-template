package ohlot.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private final MemberSecureId secureId;
    private MemberNickname nickname;
    private MemberStateMessage stateMessage;

    @Builder
    private Member(final MemberSecureId secureId, final MemberNickname nickname, final MemberStateMessage stateMessage) {
        if (null == secureId || null == nickname || null == stateMessage) throw new InvalidMemberFormatException();
        this.secureId = secureId;
        this.nickname = nickname;
        this.stateMessage = stateMessage;
    }

    public static Member generateSignUpInstance(final MemberSecureId memberSecureId, final String nickname) {
        final MemberNickname memberNickname = new MemberNickname(nickname);
        return builder()
                .secureId(memberSecureId)
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
