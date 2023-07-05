package ohlot.member.api;

import lombok.RequiredArgsConstructor;
import ohlot.member.app.MemberQueryListResponse;
import ohlot.member.app.MemberQueryResponse;
import ohlot.member.app.MemberQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class MemberQueryApi {
    private final MemberQueryService memberQueryService;

    @GetMapping("api/members")
    MemberQueryListResponse searchMemberList(@RequestParam(name = "query", defaultValue = "") String query,
                                             @RequestParam(name = "display", defaultValue = "10") Integer display,
                                             @RequestParam(name = "start", defaultValue = "1") Integer start
    ) {
        return memberQueryService.searchMember(query, display, start);
    }
    @GetMapping("api/members/{publicId}")
    MemberQueryResponse searchMember(@PathVariable String publicId) {
        return memberQueryService.searchMemberByPublicId(publicId);
    }
}
