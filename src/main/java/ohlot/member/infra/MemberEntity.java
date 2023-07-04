package ohlot.member.infra;

import lombok.*;
import ohlot.member.domain.Member;
import ohlot.member.domain.MemberNickname;
import ohlot.member.domain.MemberSecureId;
import ohlot.member.domain.MemberStateMessage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER", indexes = {
        @Index(name = "IDX_MEMBER_SECURE_ID", columnList = "secure_id")
})
@Entity
@Getter
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = MemberSecureIdConverter.class)
    @Column(name = "secure_id", nullable = false, unique = true)
    private MemberSecureId secureId;
    @Convert(converter = MemberNicknameConverter.class)
    @Column(name = "nickname", nullable = false)
    private MemberNickname nickname;
    @Convert(converter = MemberStateMessageConverter.class)
    @Column(name = "state_message")
    private MemberStateMessage stateMessage;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static MemberEntity mapped(final Member member) {
        return builder()
                .secureId(member.getSecureId())
                .nickname(member.getNickname())
                .stateMessage(member.getStateMessage())
                .build();
    }

    public Member toMember() {
        return Member.builder()
                .secureId(this.secureId)
                .nickname(this.nickname)
                .stateMessage(this.stateMessage)
                .build();
    }
}
