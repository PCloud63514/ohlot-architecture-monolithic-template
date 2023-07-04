package ohlot.member.app;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberSignUpService {
    private final MemberRepository memberRepository;

    public void signUp(final MemberSignUpRequest request) {
        final MemberSecureId memberSecureId = memberRepository.obtainSecureId();
        final Member member = Member.generateSignUpInstance(memberSecureId, request.getNickname());
        memberRepository.save(member);
    }
}
