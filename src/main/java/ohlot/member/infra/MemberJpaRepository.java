package ohlot.member.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPublicId(final String publicId);
    Optional<MemberEntity> findBySecureId(final String secureId);
    Page<MemberEntity> findByNicknameLike(final String nickname, Pageable pageable);
}
