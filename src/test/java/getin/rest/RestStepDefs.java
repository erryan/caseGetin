package getin.rest;

import getin.rest.core.ApiGateway;
import getin.rest.core.ApiProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;

public class RestStepDefs {

    private ApiGateway apiGateway() {
        return ApiProvider.getInstance();
    }

    public String getToken() {
        this.apiGateway().setHeader("Accept", "*/*");
        this.apiGateway().setHeader("Content-Type", "application/json");
        this.apiGateway().setBody("   {\n" +
                "      \"phoneNumber\": \"+905054881393\",\n" +
                "      \"password\": \"Cc123-\"\n" +
                "    }");
        this.apiGateway().sendRequest("/identity/user/login", HttpMethod.POST);
        return (String) apiGateway().getJsonPathValue("$.data.tokenInformation.token");
    }

    @Given("^I set (.*) header to (.*)$")
    public void header(String headerName, String headerValue) {
        this.apiGateway().setHeader(headerName, headerValue);
    }

    @Given("^I set Authorization token$")
    public void headerAuthorization() {
        this.apiGateway().setHeader("Authorization", "Bearer " + getToken());
    }

    @Given("^I set body to:$")
    public void setBodyTo(String body) {
        this.apiGateway().setBody(body);
    }

    @When("^I POST (.*)$")
    public void post(String resource) {
        this.apiGateway().sendRequest(resource, HttpMethod.POST);
    }

    @When("^I GET (.*)$")
    public void get(String resource) {
        this.apiGateway().sendRequest(resource, HttpMethod.GET);
    }

    @Then("^response code should be (\\d+)$")
    public void responseCode(Integer status) {
        this.apiGateway().checkResponseStatus(status, false);
    }

    @Then("^response body path (.*) should be (.*)$")
    public void bodyPathEqual(String jsonPath, String value) {
        this.apiGateway().checkJsonPath(jsonPath, value, false);
    }

    @Then("^response body path (.*) should exists$")
    public void bodyPathExists(String jsonPath) {
        this.apiGateway().getJsonPathValue(jsonPath);
    }
}
