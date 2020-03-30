package mathpar.test.actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import mathpar.test.contexts.RequestContext;
import mathpar.test.contexts.ScenarioContext;
import mathpar.test.utils.dto.frontend.responses.ClassResponse;
import mathpar.test.utils.dto.frontend.responses.GroupResponse;
import mathpar.test.utils.dto.school.responses.GetSchoolProfilesResponse;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.newtorking.results.SuccessfulJsonResult;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckActions {
    @Then("Request finished with status {int}")
    public void checkLastRequestStatus(int status){
        assertEquals(status, RequestContext.getLastRequestResult().status);
    }

    @Then("response body has key {string} with value {string}")
    public void checkResponseBody(String key, String value){
        assertEquals(value, String.valueOf(RequestContext.getLastResultAsJson().getResponseBody().get(key)));
    }

    @And("I get school which has {int} head_teachers, {int} teachers, {int} students")
    public void checkFetchedSchool(int headTeachers, int teachers, int students){
        GetSchoolProfilesResponse schoolResponse = RequestContext.getLastResultAsJson().getResponse(GetSchoolProfilesResponse.class);
        assert schoolResponse.getHeadTeachers().size() == headTeachers
                && schoolResponse.getTeachers().size() == teachers
                && schoolResponse.getStudents().size() == students;
    }
    @And("I save result with key {string}")
    public void saveResult(String key){
        ScenarioContext.addToContext(key, RequestContext.getLastResultAsJson());
    }

    @And("I save response body with key {string}")
    public void saveResponseBody_Old(String key){
        ScenarioContext.addToContext(key, RequestContext.getLastResultAsJson().getResponseBody());
    }

    @And("response is saved with key {string}")
    public void saveResponseBody(String key){
        ScenarioContext.addToContext(key, RequestContext.getLastResultAsJson());
    }

    @And("I verify that class has name {string}")
    public void checkClassName(String expected){
        assertEquals(expected, RequestContext.getLastResultAsJson().getResponse(ClassResponse.class).getName());
    }

    @And("I verify that class has only following students")
    public void checkClassStudents(List<String> students){
        var response = RequestContext.getLastResultAsJson().getResponse(ClassResponse.class);
        assert response.getStudents().size() == students.size();
        students.forEach(studentKey ->{
            var student = ScenarioContext.getFromContext(studentKey, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class);
            for(var user: response.getStudents()){
                if(user.getProfileId() == student.getProfileId() && user.getProfileName().equals(student.getProfileName())) return;
            }
            //Happens only when there is no such student in response
            assert false;
        });
    }

    @And("I verify the group has name {string} and assign to teacher {string}")
    public void checkGroupNameAndTeacher(String groupName, String teacherKey){
        var groupResponse = RequestContext.getLastResultAsJson().getResponse(GroupResponse.class);
        var expectedTeacher = ScenarioContext.getFromContext(teacherKey, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class);
        assertEquals(groupName, groupResponse.getName());
        assertEquals(expectedTeacher, groupResponse.getTeacher());
    }

    @And("I verify that group has precisely following students:")
    public void checkGroupStudents(List<String> studentKeys){
        var response = RequestContext.getLastResultAsJson().getResponse(GroupResponse.class);
        assert response.getStudents().size() == studentKeys.size();
        studentKeys.forEach(studentKey ->{
            var student = ScenarioContext.getFromContext(studentKey, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class);
            for(var user: response.getStudents()){
                if(user.getProfileId() == student.getProfileId() && user.getProfileName().equals(student.getProfileName())) return;
            }
            assert false;
        });
    }
}
