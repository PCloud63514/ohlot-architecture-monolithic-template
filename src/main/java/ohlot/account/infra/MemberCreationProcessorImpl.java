package ohlot.account.infra;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.MemberCreationProcessor;
import ohlot.account.domain.MemberIdentityToken;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberFactory;
import ohlot.member.domain.MemberRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class MemberCreationProcessorImpl implements MemberCreationProcessor {
    private final MemberFactory memberFactory;
    private final MemberRepository memberRepository;
    @Override
    public MemberIdentityToken createMember(final String nickname) {
        final Member member = memberFactory.createMember(nickname);
        memberRepository.save(member);
        return new MemberIdentityToken(member.getSecureId().value());
    }
}
