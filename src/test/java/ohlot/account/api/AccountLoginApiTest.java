package ohlot.account.api;

import ohlot.account.app.AccountLoginService;
import ohlot.account.app.MemberAccountLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountLoginApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    private AccountLoginApi accountLoginApi;
    @Mock
    private AccountLoginService mockAccountLoginService;
    @Captor
    private ArgumentCaptor<MemberAccountLoginRequest> memberAccountLoginRequestCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountLoginApi).build();
    }

    @DisplayName("로그인 성공 시 status=OK(200)")
    @Test
    void loginMemberAccount_status_is_ok() throws Exception {
        mockMvc.perform(post("/api/accounts/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 요청 정보를 비즈니스 서비스에 전달")
    @Test
    void loginMemberAccount_passes_data_to_service() throws Exception {
        final String givenLoginId = "givenLoginId";
        final String givenPassword = "givenPassword";

        mockMvc.perform(post("/api/accounts/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "loginId": "%s",
                          "password": "%s"
                        }
                        """.formatted(givenLoginId, givenPassword)));

        Mockito.verify(mockAccountLoginService, times(1)).loginMemberAccount(memberAccountLoginRequestCaptor.capture());
        assertThat(memberAccountLoginRequestCaptor.getValue()).isNotNull();
        assertThat(memberAccountLoginRequestCaptor.getValue().getLoginId()).isEqualTo(givenLoginId);
        assertThat(memberAccountLoginRequestCaptor.getValue().getPassword()).isEqualTo(givenPassword);
    }

    @DisplayName("로그인 인증 정보를 세션에 저장합니다.")
    @Test
    void loginMemberAccount_passes_identityToken_to_Session() throws Exception {
        final String givenKey = "X-MEMBER-SECURE-ID";
        final String givenResult = "GIVEN-X-MEMBER-SECURE-ID";
        final MockHttpSession mockHttpSession = new MockHttpSession();
        BDDMockito.given(mockAccountLoginService.loginMemberAccount(any())).willReturn(givenResult);

        mockMvc.perform(post("/api/accounts/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(mockHttpSession)
                .content("{}"));

        assertThat(mockHttpSession.getAttribute(givenKey)).isEqualTo(givenResult);
    }
}
