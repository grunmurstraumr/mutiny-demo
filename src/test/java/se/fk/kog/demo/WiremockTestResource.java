package se.fk.kog.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static se.fk.kog.demo.Constants.EXTERNAL_SERVICE;

public class WiremockTestResource implements QuarkusTestResourceLifecycleManager {
    private final WireMockServer wiremockServer = new WireMockServer(PORT);

    public static final int PORT = 8089;

    @ConfigProperty(name = "")

    @Override
    public Map<String, String> start() {
        if (!wiremockServer.isRunning())
            wiremockServer.start();

        wiremockServer.stubFor(WireMock.get(EXTERNAL_SERVICE)
                .willReturn(ResponseDefinitionBuilder.responseDefinition().withStatus(200)
                        .withBody(Double.toString(Math.random()))
                        .withFixedDelay(500)));
        return Map.of("quarkus.rest-client.blocking-client.url", wiremockServer.baseUrl(),
                "quarkus.rest-client.reactive-client.url", wiremockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (wiremockServer != null && wiremockServer.isRunning()) {
            wiremockServer.stop();
        }
    }
}
