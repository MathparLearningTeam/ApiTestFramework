package mathpar.test.actions.school;

import io.cucumber.java.en.When;
import mathpar.test.apis.SchoolApi;
import mathpar.test.contexts.ScenarioContext;
import mathpar.test.utils.dto.frontend.responses.ClassResponse;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.newtorking.results.SuccessfulJsonResult;

import java.util.List;
import java.util.stream.Collectors;

public class ClassActions {
    @When("I try to create class with name {string}")
    public static void createClass(String name){
        SchoolApi.createClass(name);
    }

    @When("I try to get class {string}")
    public static void getClass(String className){
        SchoolApi.getClass(ScenarioContext.getFromContext(className, SuccessfulJsonResult.class).getResponse(ClassResponse.class).getId());
    }

    @When("I try to delete class {string}")
    public static void deleteClass(String className){
        SchoolApi.deleteClass(ScenarioContext.getFromContext(className, SuccessfulJsonResult.class).getResponse(ClassResponse.class).getId());
    }

    @When("I try to modify class {string} and add students")
    public static void addStudentsToClass(String classKey, List<String> studentsKeys){
        var classId = ScenarioContext.getFromContext(classKey, SuccessfulJsonResult.class).getResponse(ClassResponse.class).getId();
        var studentIds = studentsKeys.stream().map(key-> ScenarioContext.getFromContext(key, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class).getProfileId())
                .collect(Collectors.toList());
        SchoolApi.addStudentsToClass(classId, studentIds);
    }

    @When("I try to modify class {string} and remove students")
    public static void removeStudentsFromClass(String classKey, List<String> studentsKeys){
        var classId = ScenarioContext.getFromContext(classKey, SuccessfulJsonResult.class).getResponse(ClassResponse.class).getId();
        var studentIds = studentsKeys.stream().map(key-> ScenarioContext.getFromContext(key, SuccessfulJsonResult.class).getResponse(SchoolProfileResponse.class).getProfileId())
                .collect(Collectors.toList());
        SchoolApi.removeStudentsFromClass(classId, studentIds);
    }

    @When("I try to get all classes for my school")
    public static void getAllClassesForMySchool(){
        SchoolApi.getAllClasses();
    }
}
