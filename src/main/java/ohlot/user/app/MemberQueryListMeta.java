package ohlot.user.app;

import java.io.Serializable;

public record MemberQueryListMeta(
        long total,
        int pageableCount,
        boolean isEnd
) implements Serializable {
}
