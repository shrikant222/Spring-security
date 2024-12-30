package org.beta.loginregistration.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {
    @GetMapping("/example")
    public String example() {
        return "Hello User";
    }
    @GetMapping("/admin/example2")
    public String example2() {
        return "Hello admin";
    }
}
