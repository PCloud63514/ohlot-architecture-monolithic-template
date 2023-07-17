package ohlot.account.app;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.exception.AccountNotFoundException;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import ohlot.account.domain.model.LoginPassword;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountLoginService {
    private final AccountRepository accountRepository;

    public String loginMemberAccount(final MemberAccountLoginRequest request) {
        final LoginId loginId = new LoginId(request.getLoginId());
        final LoginPassword password = new LoginPassword(request.getPassword());
        final LoginAccount loginAccount = accountRepository.findBy(loginId).orElseThrow(AccountNotFoundException::new);
        return loginAccount.authenticate(password).value();
    }
}
