package ohlot.account.api;

import lombok.RequiredArgsConstructor;
import ohlot.account.app.AccountSignUpService;
import ohlot.account.app.MemberAccountSignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class AccountSignUpApi {
    private final AccountSignUpService accountSignUpService;

    @ResponseStatus(code= HttpStatus.CREATED)
    @PostMapping("api/accounts/members/signup")
    void signUpMemberAccount(@RequestBody final MemberAccountSignUpRequest request) {
        accountSignUpService.signUpMemberAccount(request);
    }
}
