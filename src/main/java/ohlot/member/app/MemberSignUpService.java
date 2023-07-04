package ohlot.member.app;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberSignUpService {
    private final MemberRepository memberRepository;

    public void signUp(final MemberSignUpRequest request) {
        final MemberSecureId memberSecureId = memberRepository.obtainSecureId();
        final MemberPublicId memberPublicId = memberRepository.obtainPublicId();
        final Member member = Member.generateSignUpInstance(memberSecureId, memberPublicId, request.getNickname());
        memberRepository.save(member);
    }
}
