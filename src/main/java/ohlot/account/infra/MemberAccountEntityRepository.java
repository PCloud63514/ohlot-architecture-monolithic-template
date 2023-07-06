package ohlot.account.infra;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberAccountEntityRepository extends JpaRepository<MemberAccountEntity, Long> {
    boolean existsByLoginId(final String loginId);
}
