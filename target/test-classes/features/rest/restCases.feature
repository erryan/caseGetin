Feature: Apı Test

  Scenario: Login Service Test Case
    Given I set Accept header to */*
    Given I set Content-Type header to application/json
    Given I set body to:
    """
    {
      "phoneNumber": "+905054881393",
      "password": "Cc123-"
    }
    """
    When I POST /identity/user/login
    Then response code should be 200
    Then response body path $.status should be success
    Then response body path $.data.tokenInformation.token should exists
    Then response body path $.data.tokenInformation.refreshToken should exists
    Then response body path $.data.tokenInformation.expireAt should exists
    Then response body path $.meta.buildVersion should be 1.0-SNAPSHOT
    Then response body path $.meta.X-Request-ID should exists
    Then response body path $.meta.locale should be tr