package com.vvs.web.server.controller;

import com.vvs.web.server.data.MyResponse;
import com.vvs.web.server.exception.BadInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class MyRestController {

    @GetMapping("/test")
    public MyResponse getMyResponse(@RequestParam(required = false) String var) {
        log.info("Received new request with input {} ", var);
        if (Objects.equals(var, "error")) {
            throw new BadInputException();
        }
        return MyResponse.builder()
                .value(var)
                .build();
    }
}
