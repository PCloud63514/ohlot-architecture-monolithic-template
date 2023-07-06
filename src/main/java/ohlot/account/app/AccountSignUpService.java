package ohlot.account.app;


import lombok.RequiredArgsConstructor;
import ohlot.account.domain.AccountRepository;
import ohlot.account.domain.MemberAccount;
import ohlot.account.domain.MemberCreationProcessor;
import ohlot.account.domain.MemberIdentityToken;
import ohlot.account.domain.MemberLoginId;
import ohlot.account.domain.MemberLoginIdExistsException;
import ohlot.account.domain.MemberPassword;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountSignUpService {
    private final AccountRepository accountRepository;
    private final MemberCreationProcessor memberCreationProcessor;

    public void signUpMemberAccount(final MemberAccountSignUpRequest request) {
        final MemberLoginId loginId = new MemberLoginId(request.getLoginId());
        final MemberPassword password = new MemberPassword(request.getPassword());
        if (accountRepository.isLoginIdExists(loginId)) throw new MemberLoginIdExistsException();

        final MemberIdentityToken identityToken = memberCreationProcessor.createMember(request.getNickname());
        final MemberAccount memberAccount = new MemberAccount(identityToken, loginId, password);
        accountRepository.save(memberAccount);

    }
}
