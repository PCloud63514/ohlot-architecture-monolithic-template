package ohlot.member.domain;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findBy(final MemberPublicId publicId);
    Optional<Member> findBy(final MemberSecureId secureId);

    Page<Member> findAllByNicknameContains(final String query, Integer display, Integer start);

    void save(final Member member);
}
