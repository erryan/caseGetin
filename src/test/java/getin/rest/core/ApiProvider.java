package getin.rest.core;

import io.cucumber.java.Before;

public class ApiProvider {

    private static ApiGateway apiGateway;

    private final String baseUrl = "https://dev2.roketapp.site";

    public static ApiGateway getInstance() {
        return apiGateway;
    }

    @Before(order = 0)
    public void beforeScenario() {
        apiGateway = new ApiGateway(this.baseUrl);
    }
}
