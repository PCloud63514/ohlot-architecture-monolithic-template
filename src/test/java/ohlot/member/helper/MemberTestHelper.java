package ohlot.member.helper;

import ohlot.member.app.MemberSignUpRequest;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class MemberTestHelper {
    @Mock
    protected MemberRepository mockMemberRepository;
    @Captor
    protected ArgumentCaptor<Member> memberCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockMemberRepository.obtainSecureId()).thenReturn(new MemberSecureId(UUID.randomUUID().toString()));
        Mockito.lenient().when(mockMemberRepository.obtainPublicId()).thenReturn(new MemberPublicId(UUID.randomUUID().toString()));
    }


    protected MemberSignUpRequest.MemberSignUpRequestBuilder anMemberSignUpRequest() {
        return MemberSignUpRequest.builder()
                .nickname("givenNickname")
                ;
    }
}
