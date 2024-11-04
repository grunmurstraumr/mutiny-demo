package se.fk.kog.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import static se.fk.kog.demo.Constants.EXTERNAL_SERVICE;

@RegisterRestClient(configKey = "blocking-client")
@Path(EXTERNAL_SERVICE)
public interface BlockingClient {

    @GET()
    String getBlockingData();
}
