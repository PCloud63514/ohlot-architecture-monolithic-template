package org.pcloud.monolithicarchitecture.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.pcloud.monolithicarchitecture.global.support.http.RequestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("demo")
@RestController
public class DemoApi {

    @GetMapping
    public RequestResponse<String> getDemo() {
        return RequestResponse.create("Success");
    }
}
