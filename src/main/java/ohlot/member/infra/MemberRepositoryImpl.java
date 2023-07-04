package ohlot.member.infra;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MemberSecureId obtainSecureId() {
        return new MemberSecureId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void save(final Member member) {
        this.memberJpaRepository.save(MemberEntity.mapped(member));
    }
}