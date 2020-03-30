package mathpar.test.actions.account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mathpar.test.apis.AccountApi;
import mathpar.test.contexts.AccountContext;
import mathpar.test.contexts.AuthenticationContext;
import mathpar.test.contexts.RequestContext;
import mathpar.test.utils.dto.authentication.responses.CreateAccountResponse;
import mathpar.test.utils.properties.Properties;

public class AccountActions {
    @Given("account with email {string}, name {string} and password {string} exists")
    public static void createAccount(String email, String fullName, String password){
        AccountApi.createAccount(email, password, fullName);
    }

    @Given("account from template {string} exists")
    public static void createAccountFromTemplate(String templateName){
        var accountData = Properties.accountDataList.get(templateName);
        AccountApi.createAccount(accountData.getEmail(), accountData.getPassword(), accountData.getFullName());
        var response = RequestContext.getLastResultAsJson().getResponse(CreateAccountResponse.class);
        AccountContext.getContext().addAccount(response, accountData.getEmail(), accountData.getPassword());
        AuthenticationContext.addAuthentication(response.getAccountId(), response.getAuthentication());
    }


    @When("i check if email {string} available")
    public static void isEmailAvailable(String email){
        AccountApi.isEmailAvailable(email);
    }

    @When("i check if email from account template {string} available")
    public static void isEmailFromTemplateAvailable(String templateName){
        var accountData = Properties.accountDataList.get(templateName);
        AccountApi.isEmailAvailable(accountData.getEmail());
    }
}
