package ohlot.member.infra;

import ohlot.member.domain.Member;
import ohlot.member.domain.MemberFactory;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.domain.MemberStateMessage;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Component
class MemberFactoryImpl implements MemberFactory {
    @Override
    public Member createMember(final String nickname) {
        final MemberNickname memberNickname = new MemberNickname(nickname);
        final MemberSecureId memberSecureId = obtainSecureId();
        final MemberPublicId memberPublicId = obtainPublicId();

        return Member.builder()
                .secureId(memberSecureId)
                .publicId(memberPublicId)
                .nickname(memberNickname)
                .stateMessage(new MemberStateMessage(""))
                .build();
    }

    private MemberSecureId obtainSecureId() {
        return new MemberSecureId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    private MemberPublicId obtainPublicId() {
        return new MemberPublicId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }
}
