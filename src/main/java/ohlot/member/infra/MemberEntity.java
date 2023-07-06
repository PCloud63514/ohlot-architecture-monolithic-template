package ohlot.member.infra;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberPublicId;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.domain.MemberStateMessage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER", indexes = {
        @Index(name = "IDX_MEMBER_SECURE_ID", columnList = "secure_id"),
        @Index(name = "IDX_MEMBER_PUBLIC_ID", columnList = "public_id"),
})
@Entity
@Getter
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "secure_id", nullable = false, unique = true)
    private String secureId;
    @Column(name = "public_id", nullable = false, unique = true)
    private String publicId;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "state_message")
    private String stateMessage;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static MemberEntity mapped(final Member member) {
        return builder()
                .secureId(member.getSecureId().value())
                .publicId(member.getPublicId().value())
                .nickname(member.getNickname().value())
                .stateMessage(member.getStateMessage().value())
                .build();
    }

    public Member toMember() {
        return Member.builder()
                .secureId(new MemberSecureId(this.secureId))
                .publicId(new MemberPublicId(this.publicId))
                .nickname(new MemberNickname(this.nickname))
                .stateMessage(new MemberStateMessage(this.stateMessage))
                .build();
    }
}
