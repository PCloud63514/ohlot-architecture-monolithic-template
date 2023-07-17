package ohlot.user.app;

import java.util.List;

public record MemberQueryListResponse(
        MemberQueryListMeta meta,
        List<MemberQueryResponse> data
) {
}
