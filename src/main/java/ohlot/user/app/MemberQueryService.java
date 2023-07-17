package ohlot.user.app;

import lombok.RequiredArgsConstructor;
import ohlot.user.domain.UserRepository;
import ohlot.user.domain.exception.UserNotFoundException;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberQueryService {
    private final UserRepository userRepository;

    public MemberQueryResponse searchMemberByPublicId(final String publicId) {
        final User user = userRepository.findBy(new UserPublicId(publicId))
                .orElseThrow(UserNotFoundException::new);
        return MemberQueryResponse.builder()
                .publicId(user.getPublicId().value())
                .nickname(user.getNickname().value())
                .stateMessage(user.getStateMessage().value())
                .build();
    }

    public MemberQueryListResponse searchMember(final String query,
                                                final Integer display,
                                                final Integer start) {
        final Page<User> pages = userRepository.findAllByNicknameContains(query, display, start);
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
