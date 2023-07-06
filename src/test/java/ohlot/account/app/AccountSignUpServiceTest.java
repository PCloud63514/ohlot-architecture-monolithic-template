package ohlot.account.app;

import ohlot.account.domain.MemberIdentityToken;
import ohlot.account.domain.MemberLoginIdExistsException;
import ohlot.account.helper.AccountTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountSignUpServiceTest  extends AccountTestHelper {
    @InjectMocks
    private AccountSignUpService accountSignUpService;

    @DisplayName("로그인 아이디가 이미 존재하면 예외처리합니다.")
    @Test
    void signUpMemberAccount_throw_loginId_exists_exception() {
        final MemberAccountSignUpRequest givenRequest = anMemberAccountSignUpRequest().build();
        BDDMockito.given(mockAccountRepository.isLoginIdExists(any())).willReturn(true);

        assertThat(catchThrowableOfType(() -> {
            accountSignUpService.signUpMemberAccount(givenRequest);
        }, MemberLoginIdExistsException.class)).isNotNull();

        verify(mockAccountRepository, times(1)).isLoginIdExists(memberLoginIdCaptor.capture());
        assertThat(memberLoginIdCaptor.getValue()).isNotNull();
    }

    @DisplayName("회원 생성을 요청합니다.")
    @Test
    void signUpMemberAccount_call_creationMember() {
        final MemberAccountSignUpRequest givenRequest = anMemberAccountSignUpRequest().build();

        accountSignUpService.signUpMemberAccount(givenRequest);

        verify(mockMemberCreationProcessor, times(1)).createMember(nicknameCaptor.capture());
        assertThat(nicknameCaptor.getValue()).isNotNull();
        assertThat(nicknameCaptor.getValue()).isEqualTo(givenRequest.getNickname());
    }

    @DisplayName("MemberAccount 정보를 저장합니다.")
    @Test
    void signUpMemberAccount_passes_account_to_repository() {
        final MemberAccountSignUpRequest givenRequest =anMemberAccountSignUpRequest().build();
        MemberIdentityToken givenMemberIdentityToken = new MemberIdentityToken("givenMemberIdentityToken");
        BDDMockito.given(mockMemberCreationProcessor.createMember(any())).willReturn(givenMemberIdentityToken);

        accountSignUpService.signUpMemberAccount(givenRequest);

        verify(mockAccountRepository, times(1)).save(memberAccountCaptor.capture());
        assertThat(memberAccountCaptor.getValue()).isNotNull();
        assertThat(memberAccountCaptor.getValue().getIdentityToken()).isEqualTo(givenMemberIdentityToken);
        assertThat(memberAccountCaptor.getValue().getLoginId().value()).isEqualTo(givenRequest.getLoginId());
        assertThat(memberAccountCaptor.getValue().getPassword().value()).isEqualTo(givenRequest.getPassword());
    }
}
