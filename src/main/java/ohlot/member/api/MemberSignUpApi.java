package ohlot.member.api;


import lombok.RequiredArgsConstructor;
import ohlot.member.app.MemberSignUpRequest;
import ohlot.member.app.MemberSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class MemberSignUpApi {
    private final MemberSignUpService memberSignUpService;

    @ResponseStatus(code= HttpStatus.CREATED)
    @PostMapping("api/members/signup")
    void signUp(@RequestBody final MemberSignUpRequest request) {
        memberSignUpService.signUp(request);
    }
}
