package de.tine.hystrixtest;


import brave.Tracer;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.instrument.hystrix.TraceCommand;
import org.springframework.web.client.RestTemplate;

public class TimeoutTracerCommand extends TraceCommand<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(TimeoutTracerCommand.class);

    private final RestTemplate restTemplate;

    public TimeoutTracerCommand(Tracer tracer, RestTemplate restTemplate) {
        super(tracer,
            Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("timeout"))
                .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(100)));
        this.restTemplate = restTemplate;
    }

    @Override
    public Void doRun() throws Exception {
        LOG.info("Calling /timeout");
        restTemplate.getForObject("/timeout", String.class);
        return null;
    }

    @Override
    public Void doGetFallback() {
        LOG.info("Fallback for /timeout");
        return null;
    }
}
