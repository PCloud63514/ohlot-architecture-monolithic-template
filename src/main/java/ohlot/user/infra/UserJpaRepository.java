package ohlot.user.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPublicId(final String publicId);
    Optional<UserEntity> findBySecureId(final String secureId);
    Page<UserEntity> findByNicknameLike(final String nickname, Pageable pageable);
}
