package ohlot.account.app;

import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberAccount;
import ohlot.account.domain.MemberAccountNotFoundException;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberPassword;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountLoginService {
    private final AccountRepository accountRepository;

    public String loginMemberAccount(final MemberAccountLoginRequest request) {
        final MemberLoginId loginId = new MemberLoginId(request.getLoginId());
        final MemberPassword password = new MemberPassword(request.getPassword());
        final MemberAccount memberAccount = accountRepository.findBy(loginId).orElseThrow(MemberAccountNotFoundException::new);
        return memberAccount.authenticate(password).value();
    }
}
