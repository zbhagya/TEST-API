Feature: Validating Place APIs

Scenario: Verify if Add Place is being Succesfully added using AddPlaceAPI
Given Add Place Payload
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

Scenario: verify if Get place functionality is working
Given Get Place Payload
When user calls "getPlaceAPI" with "GET" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

