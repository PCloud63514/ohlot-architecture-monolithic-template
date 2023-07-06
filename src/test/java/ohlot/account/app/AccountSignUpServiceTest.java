package ohlot.account.app;

import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberCreationProcessor;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberLoginIdExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountSignUpServiceTest  {
    @InjectMocks
    private AccountSignUpService accountSignUpService;
    @Mock
    private AccountRepository mockAccountRepository;
    @Mock
    private MemberCreationProcessor mockMemberCreationProcessor;
    @Captor
    private ArgumentCaptor<MemberLoginId> memberLoginIdCaptor;
    @Captor
    private ArgumentCaptor<String> nicknameCaptor;

    @DisplayName("로그인 아이디가 이미 존재하면 예외처리합니다.")
    @Test
    void signUpMemberAccount_throw_loginId_exists_exception() {
        final MemberAccountSignUpRequest givenRequest = MemberAccountSignUpRequest.builder()
                .loginId("givenLoginId")
                .password("givenPassword")
                .build();

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
        final MemberAccountSignUpRequest givenRequest = MemberAccountSignUpRequest.builder()
                .loginId("givenLoginId")
                .password("givenPassword")
                .nickname("givenNickname")
                .build();

        accountSignUpService.signUpMemberAccount(givenRequest);

        verify(mockMemberCreationProcessor, times(1)).createMember(nicknameCaptor.capture());
        assertThat(nicknameCaptor.getValue()).isNotNull();
        assertThat(nicknameCaptor.getValue()).isEqualTo(givenRequest.getNickname());
    }
}
