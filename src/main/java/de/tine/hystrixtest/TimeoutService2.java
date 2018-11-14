package de.tine.hystrixtest;

import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeoutService2 {

    private static final Logger LOG = LoggerFactory.getLogger(TimeoutService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Tracer tracer;

    public void causeTimeout() {
        new TimeoutCommand(restTemplate).execute();
    }

}
