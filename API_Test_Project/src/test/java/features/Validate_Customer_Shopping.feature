Feature: Validating Customer is able to so shopping 

Scenario: Verify if customer is able register with sesion id
Given customer session id is provided
When customer calls "GetSessionID" with "GET" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "success" in response body is "1"


