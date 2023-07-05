package ohlot.member.app;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNotFoundException;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {
    private final MemberRepository memberRepository;

    public void updateMember(final String memberSecureId, final MemberUpdateRequest request) {
        final MemberSecureId secureId = new MemberSecureId(memberSecureId);
        final Member member = memberRepository.findBy(secureId).orElseThrow(MemberNotFoundException::new);
        member.changeInfo(request.getNickname(), request.getStateMessage());
        memberRepository.save(member);
    }
}
