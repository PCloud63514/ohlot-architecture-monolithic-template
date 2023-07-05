package ohlot.member.app;

import ohlot.member.domain.MemberNotFoundException;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.helper.MemberTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MemberUpdateServiceTest extends MemberTestHelper {
    @InjectMocks
    private MemberUpdateService memberUpdateService;

    @DisplayName("업데이트 요청 대상이 존재하지 않을 시 예외")
    @Test
    void updateMember_throw_member_not_found_exception() {
        final String givenSecureId = "givenSecureId";
        final MemberUpdateRequest givenRequest = anMemberUpdateRequest().build();
        BDDMockito.given(mockMemberRepository.findBy(any(MemberSecureId.class))).willReturn(Optional.empty());

        assertThat(catchThrowableOfType(() -> {
            memberUpdateService.updateMember(givenSecureId, givenRequest);
        }, MemberNotFoundException.class)).isNotNull();
    }

    @DisplayName("업데이트된 회원 정보를 저장 요청")
    @Test
    void updateMember_passes_update_after_member_to_repository() {
        final String givenSecureId = "givenSecureId";
        final MemberUpdateRequest givenRequest = anMemberUpdateRequest()
                .nickname("NNN")
                .stateMessage("HHH")
                .build();

        memberUpdateService.updateMember(givenSecureId, givenRequest);

        verify(mockMemberRepository, times(1)).save(memberCaptor.capture());
        assertThat(memberCaptor.getValue()).isNotNull();
        assertThat(memberCaptor.getValue().getNickname().value()).isEqualTo(givenRequest.getNickname());
        assertThat(memberCaptor.getValue().getStateMessage().value()).isEqualTo(givenRequest.getStateMessage());
    }
}
