package se.fk.kog.demo;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@ApplicationScoped
public class BlockingService {
    @ConfigProperty(name = "numberOfCalls", defaultValue = "10")
    int numberOfCalls;

    @RestClient
    BlockingClient client;

    public List<String> callExternalService(){
        List<String> responses = new ArrayList<>(numberOfCalls);

        for (int i = 0; i < numberOfCalls; i++)
        {
            responses.add(client.getBlockingData());
        }

        return responses;
    }

    public List<String> parallellCalls(){
        return Stream.iterate(0, i -> i+1).parallel().map(i -> client.getBlockingData()).toList();
    }
}
