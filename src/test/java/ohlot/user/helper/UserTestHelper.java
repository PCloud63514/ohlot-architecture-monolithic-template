package ohlot.user.helper;

import ohlot.account.app.MemberAccountSignUpRequest;
import ohlot.user.app.MemberUpdateRequest;
import ohlot.user.domain.UserFactory;
import ohlot.user.domain.UserRepository;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserNickname;
import ohlot.user.domain.model.UserPublicId;
import ohlot.user.domain.model.UserSecureId;
import ohlot.user.domain.model.UserStateMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserTestHelper {
    @Mock
    protected UserRepository mockUserRepository;
    @Mock
    protected UserFactory mockUserFactory;
    @Captor
    protected ArgumentCaptor<User> memberCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockUserFactory.createMember(any())).thenReturn(anMember().build());
        Mockito.lenient().when(mockUserRepository.findBy(any(UserPublicId.class))).thenReturn(Optional.of(anMember().build()));
        Mockito.lenient().when(mockUserRepository.findBy(any(UserSecureId.class))).thenReturn(Optional.of(anMember().build()));
    }

    protected User.UserBuilder anMember() {
        return User.builder()
                .secureId(new UserSecureId("secureId"))
                .publicId(new UserPublicId("publicId"))
                .nickname(new UserNickname("nickname"))
                .stateMessage(new UserStateMessage("stateMessage"))
                ;
    }

    protected MemberAccountSignUpRequest.MemberAccountSignUpRequestBuilder anMemberSignUpRequest() {
        return MemberAccountSignUpRequest.builder()
                .nickname("givenNickname")
                ;
    }

    protected MemberUpdateRequest.MemberUpdateRequestBuilder anMemberUpdateRequest() {
        return MemberUpdateRequest.builder()
                .nickname("nickname")
                .stateMessage("stateMessage")
                ;
    }
}
