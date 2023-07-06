package ohlot.account.app;


import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberLoginIdExistsException;
import ohlot.account.domain.MemberPassword;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountSignUpService {
    private final AccountRepository accountRepository;

    public void signUpMemberAccount(final MemberAccountSignUpRequest request) {
        final MemberLoginId loginId = new MemberLoginId(request.getLoginId());
        final MemberPassword password = new MemberPassword(request.getPassword());
        if (accountRepository.isLoginIdExists(loginId)) throw new MemberLoginIdExistsException();

    }
}
