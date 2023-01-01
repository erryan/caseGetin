package getin.rest.core;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Value;

public class ApiProvider {

    private static ApiGateway apiGateway;

    @Value("${rest.base.url}")
    private String baseUrl;

    public static ApiGateway getInstance() {
        return apiGateway;
    }

    @Before
    public void beforeScenario() {
        apiGateway = new ApiGateway(this.baseUrl);
    }
}
