package ohlot.account.app;

import ohlot.account.domain.exception.AccountNotFoundException;
import ohlot.account.domain.exception.InvalidPasswordException;
import ohlot.account.domain.exception.InvalidUserAccountFormatException;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import ohlot.account.domain.model.LoginPassword;
import ohlot.account.helper.LoginAccountTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LoginAccountLoginServiceTest extends LoginAccountTestHelper {
    @InjectMocks
    private AccountLoginService accountLoginService;

    @DisplayName("로그인 요청 정보가 잘못되었을 시 예외처리")
    @ParameterizedTest
    @CsvSource({
            "givenLogin,",
            ",givenPassword",
            ",",
    })
    void loginMemberAccount_throws_InvalidMemberAccountFormat_exception(String loginId, String password) {
        final MemberAccountLoginRequest givenRequest = anMemberAccountLoginRequest()
                .loginId(loginId)
                .password(password)
                .build();

        assertThat(catchThrowableOfType(() -> {
            accountLoginService.loginMemberAccount(givenRequest);
        }, InvalidUserAccountFormatException.class)).isNotNull();
    }

    @DisplayName("회원 계정 정보가 존재하지 않을 시 예외처리")
    @Test
    void loginMemberAccount_throws_MemberAccountNotFound_exception() {
        final MemberAccountLoginRequest givenRequest = anMemberAccountLoginRequest().build();
        BDDMockito.given(mockAccountRepository.findBy(any(LoginId.class))).willReturn(Optional.empty());

        assertThat(catchThrowableOfType(() -> {
            accountLoginService.loginMemberAccount(givenRequest);
        }, AccountNotFoundException.class)).isNotNull();

        verify(mockAccountRepository, times(1)).findBy(memberLoginIdCaptor.capture());
        assertThat(memberLoginIdCaptor.getValue()).isNotNull();
        assertThat(memberLoginIdCaptor.getValue().value()).isEqualTo(givenRequest.getLoginId());
    }

    @DisplayName("잘못된 패스워드를 입력했을 시 예외처리")
    @Test
    void loginMemberAccount_throws_InvalidPassword_exception() {
        final MemberAccountLoginRequest givenRequest = anMemberAccountLoginRequest()
                .loginId("loginId")
                .password("QWEQWE!@#!@#")
                .build();
        final LoginAccount givenLoginAccount = anMemberAccount()
                .loginId(new LoginId("loginId"))
                .password(new LoginPassword("hhhhhhh"))
                .build();
        BDDMockito.given(mockAccountRepository.findBy(any(LoginId.class))).willReturn(Optional.of(givenLoginAccount));

        assertThat(catchThrowableOfType(() -> {
            accountLoginService.loginMemberAccount(givenRequest);
        }, InvalidPasswordException.class)).isNotNull();
    }
}
