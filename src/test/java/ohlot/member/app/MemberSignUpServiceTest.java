package ohlot.member.app;

import ohlot.member.domain.MemberSecureId;
import ohlot.member.helper.MemberTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberSignUpServiceTest extends MemberTestHelper {
    @InjectMocks
    private MemberSignUpService memberSignUpService;

    @DisplayName("회원가입에 필요한 아이디(시스템용)를 흭득합니다.")
    @Test
    void signUp_call_obtainSecureId_to_repository() {
        final MemberSignUpRequest givenRequest = anMemberSignUpRequest().build();

        memberSignUpService.signUp(givenRequest);

        verify(mockMemberRepository, times(1)).obtainSecureId();
    }

    @DisplayName("회원 정보를 저장합니다.")
    @Test
    void signUp_passes_member_to_repository() {
        final MemberSignUpRequest givenRequest = anMemberSignUpRequest().build();
        final MemberSecureId givenSecureId = new MemberSecureId("givenSecureId");
        BDDMockito.given(mockMemberRepository.obtainSecureId()).willReturn(givenSecureId);

        memberSignUpService.signUp(givenRequest);

        verify(mockMemberRepository, times(1)).save(memberCaptor.capture());
        assertThat(memberCaptor.getValue()).isNotNull();
        assertThat(memberCaptor.getValue().getNickname().value()).isEqualTo(givenRequest.getNickname());
        assertThat(memberCaptor.getValue().getSecureId()).isEqualTo(givenSecureId);
    }
}
