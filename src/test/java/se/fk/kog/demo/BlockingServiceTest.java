package se.fk.kog.demo;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@QuarkusTestResource(WiremockTestResource.class)
@QuarkusTest
class BlockingServiceTest {

    @Inject
    BlockingService service;

    LocalDateTime startTime;

    @BeforeEach
    void startTimer(){
        startTime = LocalDateTime.now();
    }

    @Test
    void testBlockingServiceCalls(){
        List<String> responses = service.callExternalService();
    }

    @Test
    void testParallellCalls(){
        List<String> responses = service.parallellCalls();
    }

    @AfterEach
    void reportTime(){
        long elapsed = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        System.out.printf("Test finished in: %s milliseconds%n", elapsed );
    }
}
