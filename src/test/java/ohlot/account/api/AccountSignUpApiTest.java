package ohlot.account.api;

import ohlot.account.app.AccountSignUpService;
import ohlot.account.app.MemberAccountSignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountSignUpApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    private AccountSignUpApi accountSignUpApi;
    @Mock
    private AccountSignUpService mockAccountSignUpService;
    @Captor
    private ArgumentCaptor<MemberAccountSignUpRequest> captor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountSignUpApi).build();
    }

    @DisplayName("회원가입 요청 성공 시 status=CREATED(201)")
    @Test
    void signUpMemberAccount_status_is_created() throws Exception {
        mockMvc.perform(post("/api/accounts/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @DisplayName("회원가입 요청 정보를 비즈니스 서비스에 전달")
    @Test
    void signUpMemberAccount_passes_data_service() throws Exception {
        final String givenLoginId = "givenLoginId";
        final String givenPassword = "givenPassword";
        final String givenNickname = "givenNickname";
        mockMvc.perform(post("/api/accounts/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "loginId":"%s",
                            "password":"%s",
                            "nickname": "%s"                              
                        }
                        """.formatted(givenLoginId, givenPassword, givenNickname)));

        verify(mockAccountSignUpService, times(1)).signUpMemberAccount(captor.capture());
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getLoginId()).isEqualTo(givenLoginId);
        assertThat(captor.getValue().getPassword()).isEqualTo(givenPassword);
        assertThat(captor.getValue().getNickname()).isEqualTo(givenNickname);
    }
}
