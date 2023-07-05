package ohlot.member.infra;

import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPublicId(final MemberPublicId publicId);
    Page<MemberEntity> findByNicknameLike(final MemberNickname nickname, Pageable pageable);
}
