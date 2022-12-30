package getin.rest.core;

import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ApiGateway {

    private final String baseUrl;
    private final HttpHeaders headers;
    private final String[] body;
    private HttpEntity httpEntityWithFile;
    private boolean isFileIncluded;
    private ResponseEntity<String> response;
    private final RestTemplate restTemplate;
    private final Map<String, String> queryParams;

    public ApiGateway(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.body = new String[1];
        this.httpEntityWithFile = null;
        this.queryParams = new HashMap();
    }

    public void setHeader(String name, String value) {
        this.headers.set(name, value);
    }

    public void sendRequest(String requestUri, HttpMethod method) {
        Objects.requireNonNull(requestUri, "You must provide requestUri");
        Objects.requireNonNull(method, "You must provide http method");
        boolean writeMode = HttpMethod.POST.equals(method) || HttpMethod.PUT.equals(method);
        if (writeMode) {
            Objects.requireNonNull(this.body, "You must provide body");
        }

        if (!requestUri.startsWith("/")) {
            requestUri = "/" + requestUri;
        }

        HttpEntity httpEntity;
        if (!this.isFileIncluded) {
            httpEntity = new HttpEntity(this.body[0], this.headers);
        } else {
            httpEntity = this.httpEntityWithFile;
            this.isFileIncluded = false;
        }

        this.response = this.restTemplate.exchange(this.baseUrl + requestUri, method, httpEntity, String.class, this.queryParams);
    }

    public void checkResponseStatus(Integer status, Boolean isNot) {
        HttpStatus givenStatus = HttpStatus.resolve(status);
        Objects.requireNonNull(givenStatus, "Given status is not resolved. Status: " + status);
        HttpStatus responseStatus = this.response.getStatusCode();
        boolean check = isNot != Objects.equals(givenStatus, responseStatus);
        String var10001 = givenStatus.name();
        Assert.isTrue(check, "Response status check failed. Given Status: " + var10001 + ", Response Status: " + responseStatus.name());
    }

    public void setBody(String inputBody) {
        Objects.requireNonNull(inputBody, "You must provide Body");
        this.body[0] = inputBody;
    }

    public Object getJsonPathValue(String jsonPath) {
        Objects.requireNonNull(jsonPath, "You must provide jsonPath");
        return this.getResponseBodyDocument().read(jsonPath, new Predicate[0]);
    }

    public Object getJsonPathValueWithCheck(String jsonPath) {
        Object value = this.getJsonPathValue(jsonPath);
        Objects.requireNonNull(value, "JsonPath value is null. JsonPath: " + jsonPath);
        return value;
    }

    private ReadContext getResponseBodyDocument() {
        Objects.requireNonNull(this.response, "You should send request first");
        return JsonPath.parse((String)this.response.getBody());
    }

    public void checkJsonPath(String jsonPath, String expectedValue, Boolean isNot) {
        Object pathValue = this.getJsonPathValueWithCheck(jsonPath);
        if (isNot) {
            Assert.isTrue(!pathValue.toString().equals(expectedValue), "Expected: " + expectedValue + " Actual: " + pathValue + "\n***** JSON RESPONSE BODY *****\n" + this.toPrettyFormat(this.getResponse()) + "\n***** JSON RESPONSE BODY *****");
        } else {
            Assert.isTrue(pathValue.toString().equals(expectedValue), "Expected: " + expectedValue + " Actual: " + pathValue + "\n***** JSON RESPONSE BODY *****\n" + this.toPrettyFormat(this.getResponse()) + "\n***** JSON RESPONSE BODY *****");
        }
    }

    private String toPrettyFormat(String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
        return prettyJson;
    }

    public String getResponse() {
        return (String)this.response.getBody();
    }
}
