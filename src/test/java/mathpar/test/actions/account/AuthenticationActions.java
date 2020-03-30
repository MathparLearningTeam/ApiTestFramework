package mathpar.test.actions.account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mathpar.test.apis.AccountApi;
import mathpar.test.contexts.AuthenticationContext;
import mathpar.test.contexts.RequestContext;
import mathpar.test.utils.dto.frontend.responses.TokenResponse;
import mathpar.test.utils.properties.Properties;

public class AuthenticationActions {
    @When("I try to log in with email {string} and password {string}")
    public static void authenticated(String email, String password){
        AccountApi.authenticate(email, password);
    }

    @When("I try to log in with credentials from template {string}")
    public static void authenticated(String templateName){
        var accountData = Properties.accountDataList.get(templateName);
        AccountApi.authenticate(accountData.getEmail(), accountData.getPassword());
    }

    @Then("I am authenticated successfully")
    public static void authenticationSucceeds(){
        TokenResponse authenticationResult = RequestContext.getLastResultAsJson().getResponse(TokenResponse.class);
        var accountId = authenticationResult.getAccountId();
        AuthenticationContext.addAuthentication(accountId, authenticationResult);
        AuthenticationContext.useAuthentication(accountId);
    }

    @When("I try to check if current authentication token is valid")
    public static void checkIfAuthTokenValid(){
        AccountApi.isTokenValid(AuthenticationContext.getAuthenticationToken());
    }

    @When("I try to check if authentication token {string} is valid")
    public static void checkIfCustomAuthTokenValid(String token){
        AccountApi.isTokenValid(token);
    }

    @Given("account from template {string} is authenticated")
    public static void authenticateAccount(String templateName){
        var accountData = Properties.accountDataList.get(templateName);
        AccountApi.authenticate(accountData.getEmail(), accountData.getPassword());
        TokenResponse authenticationResult = RequestContext.getLastResultAsJson().getResponse(TokenResponse.class);
        var accountId = authenticationResult.getAccountId();
        AuthenticationContext.addAuthentication(accountId, authenticationResult);
        AuthenticationContext.useAuthentication(accountId);
    }
}
