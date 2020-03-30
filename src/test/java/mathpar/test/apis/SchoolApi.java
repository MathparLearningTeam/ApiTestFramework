package mathpar.test.apis;

import mathpar.test.contexts.*;
import mathpar.test.utils.dto.frontend.payloads.*;
import mathpar.test.utils.dto.frontend.responses.SchoolResponse;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.enums.SchoolRoles;
import mathpar.test.utils.newtorking.Request;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

import static mathpar.test.utils.properties.Properties.frontendUrl;
import static mathpar.test.utils.urls.SchoolUrls.*;

public class SchoolApi {
    public static void createSchool(String schoolName, String schoolAddress){
        Request.sendRequestWithBody(frontendUrl+schoolUrl, HttpMethod.POST, Map.of("schoolName", schoolName, "schoolAddress", schoolAddress));
        try {
            var response = RequestContext.getLastResultAsJson().getResponse(SchoolResponse.class);
            ProfileContext.addProfile(response.getDirector());
            SchoolContext.getContext().addCreatedSchool(response);
        } catch (Exception ignored){}
    }

    public static void fetchProfile(){
        Request.sendSchoolRequestWithoutBody(frontendUrl+profileUrl, HttpMethod.GET);
    }

    public static void createProfile(String email, SchoolRoles role){
        Request.sendSchoolRequestWithBody(frontendUrl+profileUrl, HttpMethod.POST, Map.of("email", email, "role", role.name()));
        try {
            ProfileContext.addProfile(RequestContext.getLastResultAsJson().getResponse(SchoolProfileResponse.class));
        } catch (Exception ignored){}
    }

    public static void getSchoolUsers(){
        Request.sendSchoolRequestWithoutBody(frontendUrl+getSchoolUsers, HttpMethod.GET);
    }



    public static void removeSchool(){
        Request.sendSchoolRequestWithoutBody(frontendUrl+schoolUrl, HttpMethod.DELETE);
    }

    public static void getClass(long classId){
        Request.sendSchoolRequestWithoutBody(frontendUrl+classUrl+"?classId={classId}", HttpMethod.GET, String.valueOf(classId));
    }

    public static void createClass(String name){
        Request.sendSchoolRequestWithBody(frontendUrl+classUrl, HttpMethod.POST, Map.of("name", name));
    }

    public static void deleteClass(long classId){
        Request.sendSchoolRequestWithoutBody(frontendUrl+classUrl+"?classId={classId}", HttpMethod.DELETE, String.valueOf(classId));
    }

    public static void addStudentsToClass(long classId, List<Long> studentIds){
        Request.sendSchoolRequestWithBody(frontendUrl+addUsersToClass, HttpMethod.POST, Map.of("classId", classId, "students", studentIds));
    }

    public static void removeStudentsFromClass(long classId, List<Long> studentIds){
        Request.sendSchoolRequestWithBody(frontendUrl+removeUsersFromClass, HttpMethod.POST, Map.of("classId", classId, "students", studentIds));
    }

    public static void getAllClasses(){
        Request.sendRequestWithoutBody(frontendUrl+getAllClasses, HttpMethod.GET);
    }

    public static void createGroup(String groupName, long teacherProfileId){
        Request.sendSchoolRequestWithBody(frontendUrl+groupUrl, HttpMethod.POST, Map.of("name", groupName, "teacherProfileId", teacherProfileId));
    }

    public static void getGroup(long groupId){
        Request.sendSchoolRequestWithoutBody(frontendUrl+groupUrl+"?groupId={groupId}", HttpMethod.GET, String.valueOf(groupId));
    }

    public static void deleteGroup(long groupId){
        Request.sendSchoolRequestWithoutBody(frontendUrl+groupUrl+"?groupId={groupId}", HttpMethod.DELETE, String.valueOf(groupId));
    }

    public static void addStudentsToGroup(List<Long> students, long groupId){
        var payload = new BulkGroupActionPayload(groupId, students);
        Request.sendSchoolRequestWithBody(frontendUrl+addGroupStudentsUrl, HttpMethod.POST, payload);
    }

    public static void removeStudentsFromGroup(List<Long> students, long groupId){
        var payload = new BulkGroupActionPayload(groupId, students);
        Request.sendSchoolRequestWithBody(frontendUrl+removeGroupStudentsUrl, HttpMethod.POST, payload);
    }

    public static void assignTeacher(long teacherId, long groupId){
        var payload = new AssignTeacherToGroupPayload(teacherId, groupId);
        Request.sendSchoolRequestWithBody(frontendUrl+assignTeacherUrl, HttpMethod.POST, payload);
    }
}
