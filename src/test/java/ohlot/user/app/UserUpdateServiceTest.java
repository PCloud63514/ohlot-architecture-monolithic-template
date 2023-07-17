package ohlot.user.app;

import ohlot.user.domain.exception.UserNotFoundException;
import ohlot.user.domain.model.UserSecureId;
import ohlot.user.helper.UserTestHelper;
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

class UserUpdateServiceTest extends UserTestHelper {
    @InjectMocks
    private MemberUpdateService memberUpdateService;

    @DisplayName("업데이트 요청 대상이 존재하지 않을 시 예외")
    @Test
    void updateMember_throw_member_not_found_exception() {
        final String givenSecureId = "givenSecureId";
        final MemberUpdateRequest givenRequest = anMemberUpdateRequest().build();
        BDDMockito.given(mockUserRepository.findBy(any(UserSecureId.class))).willReturn(Optional.empty());

        assertThat(catchThrowableOfType(() -> {
            memberUpdateService.updateMember(givenSecureId, givenRequest);
        }, UserNotFoundException.class)).isNotNull();
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

        verify(mockUserRepository, times(1)).save(memberCaptor.capture());
        assertThat(memberCaptor.getValue()).isNotNull();
        assertThat(memberCaptor.getValue().getNickname().value()).isEqualTo(givenRequest.getNickname());
        assertThat(memberCaptor.getValue().getStateMessage().value()).isEqualTo(givenRequest.getStateMessage());
    }
}
