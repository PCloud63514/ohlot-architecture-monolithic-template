package ohlot.account.infra;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohlot.account.domain.model.AccountSecureId;
import ohlot.account.domain.model.AccountUserSecureId;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import ohlot.account.domain.model.LoginPassword;
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
@Table(name = "OHLOT_ACCOUNT", indexes = {
        @Index(name = "IDX_ACCOUNT_SECURE_ID", columnList = "secure_id"),
        @Index(name = "IDX_ACCOUNT_USER_SECURE_ID", columnList = "user_secure_id"),
        @Index(name = "IDX_ACCOUNT_LOGIN_ID_PASSWORD", columnList = "login_id,password"),
})
@Entity
@Getter
class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "secure_id", nullable = false, unique = true)
    private String secureId;
    @Column(name = "user_secure_id", nullable = false)
    private String userSecureId;
    @Column(name = "login_id", nullable = false)
    private String loginId;
    @Column(name = "password", nullable = false)
    private String password;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static AccountEntity mapped(final LoginAccount loginAccount) {
        return builder()
                .secureId(loginAccount.getAccountSecureId().value())
                .loginId(loginAccount.getLoginId().value())
                .password(loginAccount.getPassword().value())
                .userSecureId(loginAccount.getUserSecureId().value())
                .build();
    }

    public LoginAccount toAccount() {
        return LoginAccount.builder()
                .secureId(new AccountSecureId(this.secureId))
                .loginId(new LoginId(this.loginId))
                .password(new LoginPassword(this.password))
                .userSecureId(new AccountUserSecureId(this.userSecureId))
                .build();
    }
}
