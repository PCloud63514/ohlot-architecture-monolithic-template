package ohlot.member.app;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNotFoundException;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberQueryResponse searchMemberByPublicId(final String publicId) {
        final Member member = memberRepository.findBy(new MemberPublicId(publicId))
                .orElseThrow(MemberNotFoundException::new);
        return MemberQueryResponse.builder()
                .publicId(member.getPublicId().value())
                .nickname(member.getNickname().value())
                .stateMessage(member.getStateMessage().value())
                .build();
    }

    public MemberQueryListResponse searchMember(final String query,
                                                final Integer display,
                                                final Integer start) {
        final Page<Member> pages = memberRepository.findAllByNicknameContains(query, display, start);
        final MemberQueryListMeta meta = new MemberQueryListMeta(
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.isLast()
        );
        List<MemberQueryResponse> list = pages.stream()
                .map(member -> MemberQueryResponse.builder()
                        .publicId(member.getPublicId().value())
                        .nickname(member.getNickname().value())
                        .stateMessage(member.getStateMessage().value())
                        .build())
                .toList();
        return new MemberQueryListResponse(meta, list);
    }
}
