package ohlot.member.domain;

public interface MemberRepository {
    /**
     * 사용가능한 회원 아이디(시스템용)를 얻습니다.
     * @return secureId
     */
    MemberSecureId obtainSecureId();
    void save(final Member member);
}
