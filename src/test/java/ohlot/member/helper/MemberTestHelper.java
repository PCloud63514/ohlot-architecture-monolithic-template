package ohlot.member.helper;

import ohlot.account.app.MemberAccountSignUpRequest;
import ohlot.member.app.MemberUpdateRequest;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberFactory;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.domain.MemberStateMessage;
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
public class MemberTestHelper {
    @Mock
    protected MemberRepository mockMemberRepository;
    @Mock
    protected MemberFactory mockMemberFactory;
    @Captor
    protected ArgumentCaptor<Member> memberCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockMemberFactory.createMember(any())).thenReturn(anMember().build());
        Mockito.lenient().when(mockMemberRepository.findBy(any(MemberPublicId.class))).thenReturn(Optional.of(anMember().build()));
        Mockito.lenient().when(mockMemberRepository.findBy(any(MemberSecureId.class))).thenReturn(Optional.of(anMember().build()));
    }

    protected Member.MemberBuilder anMember() {
        return Member.builder()
                .secureId(new MemberSecureId("secureId"))
                .publicId(new MemberPublicId("publicId"))
                .nickname(new MemberNickname("nickname"))
                .stateMessage(new MemberStateMessage("stateMessage"))
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
