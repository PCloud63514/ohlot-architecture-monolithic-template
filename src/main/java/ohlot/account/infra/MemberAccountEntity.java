package ohlot.account.infra;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohlot.account.domain.MemberAccount;
import ohlot.account.domain.MemberIdentityToken;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberPassword;
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
@Table(name = "MEMBER_ACCOUNT", indexes = {
        @Index(name = "IDX_MEMBER_ACCOUNT_LOGIN_ID_PASSWORD", columnList = "login_id,password"),
        @Index(name = "IDX_MEMBER_ACCOUNT_MEMBER_SECURE_ID", columnList = "member_secure_id"),
})
@Entity
@Getter
class MemberAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id", nullable = false)
    private String loginId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "member_secure_id", nullable = false)
    private String memberSecureId;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static MemberAccountEntity mapped(final MemberAccount account) {
        return builder()
                .loginId(account.getLoginId().value())
                .password(account.getPassword().value())
                .memberSecureId(account.getIdentityToken().value())
                .build();
    }

    public MemberAccount toMemberAccount() {
        return MemberAccount.builder()
                .loginId(new MemberLoginId(this.loginId))
                .password(new MemberPassword(this.password))
                .identityToken(new MemberIdentityToken(this.memberSecureId))
                .build();
    }
}
