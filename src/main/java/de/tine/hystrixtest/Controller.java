package de.tine.hystrixtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private TimeoutService timeoutService;

    @GetMapping("/timeout")
    public void timeout() {
        timeoutService.causeTimeout();
    }

}
