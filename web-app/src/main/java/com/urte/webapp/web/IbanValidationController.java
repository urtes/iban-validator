package com.urte.webapp.web;

import com.urte.engine.IncludeMe;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IbanValidationController {

    @PostMapping("/validate")
    public List<String> validate(@RequestBody List<String> request) {
        IncludeMe includeMe = new IncludeMe();
        includeMe.test();
        return request;
    }
}
