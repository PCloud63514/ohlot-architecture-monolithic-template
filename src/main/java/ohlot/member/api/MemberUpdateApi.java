package ohlot.member.api;

import lombok.RequiredArgsConstructor;
import ohlot.member.app.MemberUpdateRequest;
import ohlot.member.app.MemberUpdateService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberUpdateApi {
    private final MemberUpdateService memberUpdateService;

    @PutMapping("api/members")
    void updateMember(
            @RequestHeader("X-MEMBER-SECURE-ID") String secureId,
            @RequestBody MemberUpdateRequest request) {
        memberUpdateService.updateMember(secureId, request);
    }
}
