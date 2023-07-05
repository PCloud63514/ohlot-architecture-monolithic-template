package ohlot.member.app;

import java.util.List;

public record MemberQueryListResponse(
        MemberQueryListMeta meta,
        List<MemberQueryResponse> data
) {
}
