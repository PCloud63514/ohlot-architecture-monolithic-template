package ohlot.member.infra;

import lombok.RequiredArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberRepository;
import ohlot.member.domain.MemberSecureId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
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
    public MemberPublicId obtainPublicId() {
        return new MemberPublicId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public Optional<Member> findBy(MemberPublicId publicId) {
        return memberJpaRepository.findByPublicId(publicId)
                .map(MemberEntity::toMember);
    }

    @Override
    public Optional<Member> findBy(final MemberSecureId secureId) {
        return memberJpaRepository.findBySecureId(secureId)
                .map(MemberEntity::toMember);
    }

    @Override
    public Page<Member> findAllByNicknameContains(final String query, Integer display, Integer start) {
        final String str = StringUtils.hasText(query) ? query : "";
        final MemberNickname memberNickname = new MemberNickname("%" + str + "%");
        return memberJpaRepository.findByNicknameLike(memberNickname, PageRequest.of(Math.abs(start - 1), display))
                .map(MemberEntity::toMember);
    }

    @Override
    public void save(final Member member) {
        this.memberJpaRepository.save(MemberEntity.mapped(member));
    }
}
