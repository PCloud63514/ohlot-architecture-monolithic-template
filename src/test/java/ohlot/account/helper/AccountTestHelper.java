package ohlot.account.helper;

import ohlot.account.app.MemberAccountLoginRequest;
import ohlot.account.app.MemberAccountSignUpRequest;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberAccount;
import ohlot.account.domain.MemberCreationProcessor;
import ohlot.account.domain.MemberIdentityToken;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberPassword;
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
public abstract class AccountTestHelper {
    @Mock
    protected AccountRepository mockAccountRepository;
    @Mock
    protected MemberCreationProcessor mockMemberCreationProcessor;
    @Captor
    protected ArgumentCaptor<MemberLoginId> memberLoginIdCaptor;
    @Captor
    protected ArgumentCaptor<String> nicknameCaptor;
    @Captor
    protected ArgumentCaptor<MemberAccount> memberAccountCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockMemberCreationProcessor.createMember(any())).thenReturn(new MemberIdentityToken("givenMemberIdentityToken"));
        Mockito.lenient().when(mockAccountRepository.isLoginIdExists(any())).thenReturn(false);
        Mockito.lenient().when(mockAccountRepository.findBy(any())).thenReturn(Optional.of(anMemberAccount().build()));
    }

    protected MemberAccount.MemberAccountBuilder anMemberAccount() {
        return MemberAccount.builder()
                .identityToken(new MemberIdentityToken("givenIdentityToken"))
                .loginId(new MemberLoginId("givenLoginId"))
                .password(new MemberPassword("givenPassword"));
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
