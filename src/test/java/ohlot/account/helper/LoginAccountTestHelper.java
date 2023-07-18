package ohlot.account.helper;

import ohlot.account.app.MemberAccountLoginRequest;
import ohlot.account.app.MemberAccountSignUpRequest;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.UserCreationProcessor;
import ohlot.account.domain.model.AccountSecureId;
import ohlot.account.domain.model.AccountUserSecureId;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import ohlot.account.domain.model.LoginPassword;
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
public abstract class LoginAccountTestHelper {
    @Mock
    protected AccountRepository mockAccountRepository;
    @Mock
    protected UserCreationProcessor mockUserCreationProcessor;
    @Captor
    protected ArgumentCaptor<LoginId> memberLoginIdCaptor;
    @Captor
    protected ArgumentCaptor<String> nicknameCaptor;
    @Captor
    protected ArgumentCaptor<LoginAccount> memberAccountCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockUserCreationProcessor.createUser(any())).thenReturn(new AccountUserSecureId("givenMemberIdentityToken"));
        Mockito.lenient().when(mockAccountRepository.isLoginIdExists(any())).thenReturn(false);
        Mockito.lenient().when(mockAccountRepository.findBy(any())).thenReturn(Optional.of(anMemberAccount().build()));
        Mockito.lenient().when(mockAccountRepository.obtainSecureId()).thenReturn(new AccountSecureId("AccountSecureId"));
    }

    protected LoginAccount.LoginAccountBuilder anMemberAccount() {
        return LoginAccount.builder()
                .secureId(new AccountSecureId("AccountSecureId"))
                .userSecureId(new AccountUserSecureId("AccountUserSecureId"))
                .loginId(new LoginId("givenLoginId"))
                .password(new LoginPassword("givenPassword"));
    }

    protected MemberAccountSignUpRequest.MemberAccountSignUpRequestBuilder anMemberAccountSignUpRequest() {
        return MemberAccountSignUpRequest.builder()
                .loginId("givenLoginId")
                .password("givenPassword")
                .nickname("givenNickname");
    }

    protected MemberAccountLoginRequest.MemberAccountLoginRequestBuilder anMemberAccountLoginRequest() {
        return MemberAccountLoginRequest.builder()
                .loginId("givenLoginId")
                .password("givenPassword");
    }
}
