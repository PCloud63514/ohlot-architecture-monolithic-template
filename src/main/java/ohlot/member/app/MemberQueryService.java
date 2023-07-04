package ohlot.member.app;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNotFoundException;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberQueryResponse searchMemberByPublicId(String publicId) {
        final Member member = memberRepository.findBy(new MemberPublicId(publicId))
                .orElseThrow(MemberNotFoundException::new);

        return MemberQueryResponse.builder()
                .publicId(member.getPublicId().value())
                .nickname(member.getNickname().value())
                .stateMessage(member.getStateMessage().value())
                .build();
    }
}
