package app.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Code;

@RestController
public class CodeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/rest/code")
    public Code greeting(@RequestParam(value="country", required=false, defaultValue="be") String name) {
        return new Code(counter.incrementAndGet(),
                            String.format(template, name));
    }
}