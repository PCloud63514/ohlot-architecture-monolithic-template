package ohlot.account.infra;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberAccountEntityRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByLoginId(final String loginId);
}
