package ohlot.member.helper;

import ohlot.member.app.MemberSignUpRequest;
import ohlot.member.app.MemberUpdateRequest;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.domain.MemberStateMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
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
        Mockito.lenient().when(mockMemberRepository.findBy(ArgumentMatchers.any(MemberPublicId.class))).thenReturn(Optional.of(anMember().build()));
        Mockito.lenient().when(mockMemberRepository.findBy(ArgumentMatchers.any(MemberSecureId.class))).thenReturn(Optional.of(anMember().build()));
    }

    protected Member.MemberBuilder anMember() {
        return Member.builder()
                .secureId(new MemberSecureId("secureId"))
                .publicId(new MemberPublicId("publicId"))
                .nickname(new MemberNickname("nickname"))
                .stateMessage(new MemberStateMessage("stateMessage"))
                ;
    }

    protected MemberSignUpRequest.MemberSignUpRequestBuilder anMemberSignUpRequest() {
        return MemberSignUpRequest.builder()
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
