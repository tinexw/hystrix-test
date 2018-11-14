package de.tine.hystrixtest;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class TimeoutCommand extends HystrixCommand<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(TimeoutCommand.class);

    private final RestTemplate restTemplate;

    protected TimeoutCommand(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("timeout"), 100);
        this.restTemplate = restTemplate;
    }

    @Override
    protected Void run() throws Exception {
        LOG.info("Calling /timeout");
        restTemplate.getForObject("/timeout", String.class);
        return null;
    }

    @Override
    protected Void getFallback() {
        LOG.info("Fallback for /timeout");
        return null;
    }
}
