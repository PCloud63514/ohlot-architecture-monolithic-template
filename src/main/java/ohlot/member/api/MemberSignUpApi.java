package ohlot.member.api;


import lombok.RequiredArgsConstructor;
import ohlot.member.app.MemberSignUpRequest;
import ohlot.member.app.MemberSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/members")
@RestController
class MemberSignUpApi {
    private final MemberSignUpService memberSignUpService;

    @ResponseStatus(code= HttpStatus.CREATED)
    @PostMapping("signup")
    void signUp(@RequestBody final MemberSignUpRequest request) {
        memberSignUpService.signUp(request);
    }
}
