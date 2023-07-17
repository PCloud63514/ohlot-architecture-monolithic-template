package ohlot.user.app;

import lombok.RequiredArgsConstructor;
import ohlot.user.domain.UserRepository;
import ohlot.user.domain.exception.UserNotFoundException;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserSecureId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {
    private final UserRepository userRepository;

    public void updateMember(final String memberSecureId, final MemberUpdateRequest request) {
        final UserSecureId secureId = new UserSecureId(memberSecureId);
        final User user = userRepository.findBy(secureId).orElseThrow(UserNotFoundException::new);
        user.changeInfo(request.getNickname(), request.getStateMessage());
        userRepository.save(user);
    }
}
