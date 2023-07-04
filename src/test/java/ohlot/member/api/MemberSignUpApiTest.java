package ohlot.member.api;

import ohlot.member.app.MemberSignUpRequest;
import ohlot.member.app.MemberSignUpService;
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
class MemberSignUpApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    private MemberSignUpApi memberSignUpApi;
    @Mock
    private MemberSignUpService mockMemberSignUpService;
    @Captor
    private ArgumentCaptor<MemberSignUpRequest> captor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberSignUpApi).build();
    }

    @DisplayName("회원가입 요청 성공 시 status=CREATED(201)")
    @Test
    void signUp_status_is_created() throws Exception {
        mockMvc.perform(post("/api/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @DisplayName("회원가입 요청 정보를 비즈니스 서비스에 전달")
    @Test
    void signUp_passes_data_service() throws Exception {
        final String givenNickname = "nick";
        mockMvc.perform(post("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nickname": "%s"                              
                        }
                        """.formatted(givenNickname)));

        verify(mockMemberSignUpService, times(1)).signUp(captor.capture());
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getNickname()).isEqualTo(givenNickname);
    }
}
