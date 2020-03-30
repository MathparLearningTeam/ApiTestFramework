package mathpar.test.actions.school;

import io.cucumber.java.en.When;
import mathpar.test.apis.SchoolApi;
import mathpar.test.contexts.ScenarioContext;
import mathpar.test.utils.dto.frontend.responses.GroupResponse;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.newtorking.results.SuccessfulJsonResult;

import java.util.List;
import java.util.stream.Collectors;

public class GroupActions {
    @When("I try to create group with name {string} headed by teacher {string}")
    public static void createGroup(String groupName, String teacherKey){
        var teacher = ScenarioContext.getFromContext(teacherKey, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class);
        SchoolApi.createGroup(groupName, teacher.getProfileId());
    }

    @When("I try to get group {string}")
    public static void getGroup(String groupKey){
        var group = ScenarioContext.getFromContext(groupKey, SuccessfulJsonResult.class).getResponse(GroupResponse.class);
        SchoolApi.getGroup(group.getId());
    }

    @When("I try to delete group {string}")
    public static void deleteGroup(String groupKey){
        var group = ScenarioContext.getFromContext(groupKey, SuccessfulJsonResult.class).getResponse(GroupResponse.class);
        SchoolApi.deleteGroup(group.getId());
    }

    @When("I try to modify group {string} and add students")
    public static void addStudentsToGroup(String groupKey, List<String> studentsKeys){
        var group = ScenarioContext.getFromContext(groupKey, SuccessfulJsonResult.class).getResponse(GroupResponse.class);
        var studentIds = studentsKeys.stream().map(key -> ScenarioContext.getFromContext(key, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class).getProfileId()).collect(Collectors.toList());
        SchoolApi.addStudentsToGroup(studentIds, group.getId());
    }

    @When("I try to modify group {string} and remove students")
    public static void removeStudentsFromGroup(String groupKey, List<String> studentsKeys){
        var group = ScenarioContext.getFromContext(groupKey, SuccessfulJsonResult.class).getResponse(GroupResponse.class);
        var studentIds = studentsKeys.stream().map(key -> ScenarioContext.getFromContext(key, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class).getProfileId()).collect(Collectors.toList());
        SchoolApi.removeStudentsFromGroup(studentIds, group.getId());
    }

    @When("I try to assign teacher {string} to group {string}")
    public static void assignTeacher(String teacherKey, String groupKey){
        var teacher = ScenarioContext.getFromContext(teacherKey, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class);
        var group = ScenarioContext.getFromContext(groupKey, SuccessfulJsonResult.class).getResponse(GroupResponse.class);
        SchoolApi.assignTeacher(teacher.getProfileId(), group.getId());
    }
}
