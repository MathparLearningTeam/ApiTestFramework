package mathpar.test.actions.school;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mathpar.test.apis.SchoolApi;
import mathpar.test.contexts.RequestContext;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.enums.SchoolRoles;
import mathpar.test.utils.properties.Properties;

import static org.junit.Assert.assertEquals;

public class ProfileActions {
    @When("i try to fetch own profile")
    public static void fetchOwnProfile(){
        SchoolApi.fetchProfile();
    }

    @And("i verify my profile of role {string} and school template {string}")
    public static void verifyProfile(String profileRole, String schoolTemplate){
        var schoolData = Properties.schoolDataList.get(schoolTemplate);
        var profileResponse = RequestContext.getLastResultAsJson().getResponse(SchoolProfileResponse.class);
        assertEquals(profileResponse.getProfileName(), schoolData.schoolName+"-"+profileRole);
        assertEquals(profileResponse.getRole().name(), profileRole);
    }

    @When("i try to create a profile for account template {string} and role {string}")
    public static void createProfile(String accountTemplate, String role){
        var accountData = Properties.accountDataList.get(accountTemplate);
        SchoolApi.createProfile(accountData.getEmail(), SchoolRoles.valueOf(role));
    }

    @Given("profile for account template {string} and role {string} exists in current school")
    public static void profileExists(String accountTemplate, String role){
        var accountData = Properties.accountDataList.get(accountTemplate);
        SchoolApi.createProfile(accountData.getEmail(), SchoolRoles.valueOf(role));
        assert RequestContext.getLastRequestResult().status == 200;
    }
}
