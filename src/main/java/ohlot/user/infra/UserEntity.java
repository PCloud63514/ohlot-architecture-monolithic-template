package ohlot.user.infra;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohlot.user.domain.model.User;
import ohlot.user.domain.model.UserNickname;
import ohlot.user.domain.model.UserPublicId;
import ohlot.user.domain.model.UserSecureId;
import ohlot.user.domain.model.UserStateMessage;
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
@Table(name = "OHLOT_USER", indexes = {
        @Index(name = "IDX_USER_SECURE_ID", columnList = "secure_id"),
        @Index(name = "IDX_USER_PUBLIC_ID", columnList = "public_id"),
})
@Entity
@Getter
class UserEntity {
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

    public static UserEntity mapped(final User user) {
        return builder()
                .secureId(user.getSecureId().value())
                .publicId(user.getPublicId().value())
                .nickname(user.getNickname().value())
                .stateMessage(user.getStateMessage().value())
                .build();
    }

    public User toUser() {
        return User.builder()
                .secureId(new UserSecureId(this.secureId))
                .publicId(new UserPublicId(this.publicId))
                .nickname(new UserNickname(this.nickname))
                .stateMessage(new UserStateMessage(this.stateMessage))
                .build();
    }
}
