package ohlot.member.api;

import ohlot.member.app.MemberUpdateRequest;
import ohlot.member.app.MemberUpdateService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberUpdateApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    private MemberUpdateApi memberUpdateApi;
    @Mock
    private MemberUpdateService mockMemberUpdateService;
    @Captor
    private ArgumentCaptor<MemberUpdateRequest> requestCaptor;
    @Captor
    private ArgumentCaptor<String> secureIdCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberUpdateApi).build();
    }

    @DisplayName("회원 정보 업데이트 요청 성공 시 status=OK(200)")
    @Test
    void updateMember_status_is_ok() throws Exception {
        mockMvc.perform(put("/api/members")
                        .header("X-MEMBER-SECURE-ID", "givenMemberSecureId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @DisplayName("회원 업데이트 요청 정보를 비즈니스 서비스에 전달")
    @Test
    void updateMember_passes_data_service() throws Exception {
        final String givenMemberSecureId = "givenMemberSecureId";
        final String givenNickname = "givenNickname";
        final String givenStateMessage = "givenStateMessage";

        mockMvc.perform(put("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-MEMBER-SECURE-ID", givenMemberSecureId)
                        .content("""
                                {
                                    "nickname": "%s",
                                    "stateMessage": "%s"
                                }
                                """.formatted(givenNickname, givenStateMessage)))
                .andExpect(status().isOk());

        verify(mockMemberUpdateService, times(1)).updateMember(secureIdCaptor.capture(), requestCaptor.capture());
        assertThat(requestCaptor.getValue()).isNotNull();
        assertThat(requestCaptor.getValue().getNickname()).isEqualTo(givenNickname);
        assertThat(requestCaptor.getValue().getStateMessage()).isEqualTo(givenStateMessage);
        assertThat(secureIdCaptor.getValue()).isNotNull();
        assertThat(secureIdCaptor.getValue()).isEqualTo(givenMemberSecureId);
    }
}
