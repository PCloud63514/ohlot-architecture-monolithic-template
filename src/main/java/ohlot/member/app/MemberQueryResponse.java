package ohlot.member.app;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberQueryResponse {
    private String publicId;
    private String nickname;
    private String stateMessage;
}
