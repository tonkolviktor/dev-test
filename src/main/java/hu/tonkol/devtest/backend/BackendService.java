package hu.tonkol.devtest.backend;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import hu.tonkol.devtest.BusinessException;
import hu.tonkol.devtest.Configuration;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by tonkol on 2016.01.03..
 */
public class BackendService {

    public List<City> listCitiesByName(String name) {
        Response response = callPositionService(name);
        validateResponse(response);
        return unmarshallResult(response);
    }

    private Response callPositionService(String name) {
        WebTarget webTarget = getPositionClient().path(name);
        Invocation.Builder request = webTarget.request();

        try {
            return request.get();
        } catch (ProcessingException pe) {
            throw new BusinessException("Error during backend call: " + pe.getMessage());
        }
    }

    private WebTarget getPositionClient() {
        return getBaseClient().target(Configuration.BASE_REST_API).path("position/suggest/en/");
    }

    private Client getBaseClient() {
        ClientConfig cfg = new ClientConfig();
        cfg.register(JacksonJsonProvider.class);
        return ClientBuilder.newClient(cfg);
    }

    private void validateResponse(Response response) {
        if (response == null) {
            throw new BusinessException("Empty result was received from server at " + Configuration.BASE_REST_API);
        }
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new BusinessException("Service call resulted in error, status: " + response.getStatus());
        }
    }

    private List<City> unmarshallResult(Response response) {
        return response.readEntity(new GenericType<List<City>>() {
        });
    }
}
