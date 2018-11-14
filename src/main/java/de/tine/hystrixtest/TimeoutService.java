package de.tine.hystrixtest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeoutService {

    private static final Logger LOG = LoggerFactory.getLogger(TimeoutService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
        commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
        fallbackMethod = "fallback"
    )
    public void causeTimeout() {
        LOG.info("Calling /timeout");
        restTemplate.getForObject("/timeout", String.class);
    }

    public void fallback() {
        LOG.info("Fallback for /timeout");
    }


}
