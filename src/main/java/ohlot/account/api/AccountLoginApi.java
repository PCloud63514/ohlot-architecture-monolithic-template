package ohlot.account.api;

import lombok.RequiredArgsConstructor;
import ohlot.account.app.AccountLoginService;
import ohlot.account.app.MemberAccountLoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
class AccountLoginApi {
    private final AccountLoginService accountLoginService;

    @PostMapping("api/accounts/members/login")
    void loginMemberAccount(@RequestBody MemberAccountLoginRequest request, HttpSession session) {
        final String identityToken = accountLoginService.loginMemberAccount(request);
        session.setAttribute("X-MEMBER-SECURE-ID", identityToken);
    }
}
