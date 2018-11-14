package de.tine.hystrixtest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class WiremockConfig {

    private final WireMockServer wireMockServer;

    public WiremockConfig() {
        wireMockServer = new WireMockServer(options().port(8089).notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
        configureFor("localhost", 8089);
        stubFor(get("/timeout").willReturn(aResponse().withStatus(200).withFixedDelay(500)));
    }


    @PreDestroy
    public void stop() {

    }


}
