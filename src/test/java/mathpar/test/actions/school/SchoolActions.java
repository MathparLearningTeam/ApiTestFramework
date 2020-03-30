package mathpar.test.actions.school;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mathpar.test.apis.SchoolApi;
import mathpar.test.contexts.AccountContext;
import mathpar.test.contexts.AuthenticationContext;
import mathpar.test.contexts.ProfileContext;
import mathpar.test.contexts.RequestContext;
import mathpar.test.utils.dto.school.responses.GetSchoolProfilesResponse;
import mathpar.test.utils.properties.Properties;

import java.util.List;
import java.util.stream.Collectors;

public class SchoolActions {
    @Given("account from template {string} created school from template {string}")
    public static void createSchoolFromTemplate(String accountTemplate, String schoolTemplate){
        var accountData = Properties.accountDataList.get(accountTemplate);
        var schoolData = Properties.schoolDataList.get(schoolTemplate);
        AuthenticationContext.useAuthentication(accountData.getEmail());
        SchoolApi.createSchool(schoolData.getSchoolName(), schoolData.getSchoolAddress());
        assert RequestContext.getLastRequestResult().status==200;
    }

    @Given("current account created school from template {string}")
    public static void createSchoolFromTemplate(String schoolTemplate){
        var schoolData = Properties.schoolDataList.get(schoolTemplate);
        SchoolApi.createSchool(schoolData.getSchoolName(), schoolData.getSchoolAddress());
        assert RequestContext.getLastRequestResult().status==200;
    }

    @When("i try to get my school profiles")
    public static void getSchoolInfo(){
        SchoolApi.getSchoolUsers();
    }

    @And("i verify school contains profiles of account templates")
    public static void verifySchoolProfiles(List<String> accountTemplates){
        var accountData = accountTemplates.stream()
                .map(Properties.accountDataList::get)
                .map(data -> AccountContext.getContext().getAccount(data.getEmail()))
                .map(account -> ProfileContext.getProfile(account.getId()))
                .collect(Collectors.toList());
        var response = RequestContext.getLastResultAsJson().getResponse(GetSchoolProfilesResponse.class);
        for(var data: accountData){
            assert response.getDirector().equals(data) || response.getHeadTeachers().contains(data)
                    || response.getTeachers().contains(data) || response.getStudents().contains(data);

        }
    }
}
