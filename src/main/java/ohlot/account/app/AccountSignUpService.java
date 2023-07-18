package ohlot.account.app;


import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.UserCreationProcessor;
import ohlot.account.domain.exception.LoginIdExistsException;
import ohlot.account.domain.model.AccountSecureId;
import ohlot.account.domain.model.AccountUserSecureId;
import ohlot.account.domain.model.LoginAccount;
import ohlot.account.domain.model.LoginId;
import ohlot.account.domain.model.LoginPassword;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountSignUpService {
    private final AccountRepository accountRepository;
    private final UserCreationProcessor userCreationProcessor;

    public void signUpMemberAccount(final MemberAccountSignUpRequest request) {
        final LoginId loginId = new LoginId(request.getLoginId());
        final LoginPassword password = new LoginPassword(request.getPassword());
        if (accountRepository.isLoginIdExists(loginId)) throw new LoginIdExistsException();

        final AccountUserSecureId accountUserSecureId = userCreationProcessor.createUser(request.getNickname());
        final AccountSecureId secureId = accountRepository.obtainSecureId();
        final LoginAccount loginAccount = new LoginAccount(secureId, accountUserSecureId, loginId, password);
        accountRepository.save(loginAccount);
    }
}
