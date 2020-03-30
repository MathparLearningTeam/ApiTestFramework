package mathpar.test.actions.account;

import io.cucumber.java.en.When;
import mathpar.test.apis.AccountApi;
import mathpar.test.utils.properties.Properties;

public class RestorationActions {
    @When("i send a request to change password for an account from template {string}")
    public static void restorePassword(String templateName){
        var accountData = Properties.accountDataList.get(templateName);
        AccountApi.restorePassword(accountData.getEmail());
    }

    @When("i send a request to change password for an email {string}")
    public static void restorePasswordForEmail(String email){
        AccountApi.restorePassword(email);
    }
}
