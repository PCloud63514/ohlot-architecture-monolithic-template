package ohlot.member.domain;

import java.util.Optional;

public interface MemberRepository {
    /**
     * 사용가능한 회원 아이디(시스템용)를 얻습니다.
     * @return secureId
     */
    MemberSecureId obtainSecureId();
    /**
     * 사용가능한 회원 아이디(조회용)를 얻습니다.
     * @return secureId
     */
    MemberPublicId obtainPublicId();

    Optional<Member> findBy(final MemberPublicId publicId);

    void save(final Member member);
}
