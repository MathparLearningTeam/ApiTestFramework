package mathpar.test.apis;

import mathpar.test.contexts.ProfileContext;
import mathpar.test.contexts.RequestContext;
import mathpar.test.contexts.SchoolContext;
import mathpar.test.utils.dto.frontend.payloads.AssignTeacherToGroupPayload;
import mathpar.test.utils.dto.frontend.payloads.BulkGroupActionPayload;
import mathpar.test.utils.dto.frontend.responses.SchoolResponse;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;
import mathpar.test.utils.enums.SchoolRoles;
import mathpar.test.utils.newtorking.Request;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

import static mathpar.test.utils.properties.Properties.schoolPrefix;
import static mathpar.test.utils.urls.SchoolUrls.*;

public class SchoolApi {
    public static void createSchool(String schoolName, String schoolAddress){
        Request.sendRequestWithBody(schoolPrefix +schoolUrl, HttpMethod.POST, Map.of("schoolName", schoolName, "schoolAddress", schoolAddress));
        try {
            var response = RequestContext.getLastResultAsJson().getResponse(SchoolResponse.class);
            ProfileContext.addProfile(response.getDirector());
            SchoolContext.getContext().addCreatedSchool(response);
        } catch (Exception ignored){}
    }

    public static void fetchProfile(){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +profileUrl, HttpMethod.GET);
    }

    public static void createProfile(String email, SchoolRoles role){
        Request.sendSchoolRequestWithBody(schoolPrefix +profileUrl, HttpMethod.POST, Map.of("email", email, "role", role.name()));
        try {
            ProfileContext.addProfile(RequestContext.getLastResultAsJson().getResponse(SchoolProfileResponse.class));
        } catch (Exception ignored){}
    }

    public static void getSchoolUsers(){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +getSchoolUsers, HttpMethod.GET);
    }



    public static void removeSchool(){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +schoolUrl, HttpMethod.DELETE);
    }

    public static void getClass(long classId){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +classUrl+"?classId={classId}", HttpMethod.GET, String.valueOf(classId));
    }

    public static void createClass(String name){
        Request.sendSchoolRequestWithBody(schoolPrefix +classUrl, HttpMethod.POST, Map.of("name", name));
    }

    public static void deleteClass(long classId){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +classUrl+"?classId={classId}", HttpMethod.DELETE, String.valueOf(classId));
    }

    public static void addStudentsToClass(long classId, List<Long> studentIds){
        Request.sendSchoolRequestWithBody(schoolPrefix +addUsersToClass, HttpMethod.POST, Map.of("classId", classId, "students", studentIds));
    }

    public static void removeStudentsFromClass(long classId, List<Long> studentIds){
        Request.sendSchoolRequestWithBody(schoolPrefix +removeUsersFromClass, HttpMethod.POST, Map.of("classId", classId, "students", studentIds));
    }

    public static void getAllClasses(){
        Request.sendRequestWithoutBody(schoolPrefix +getAllClasses, HttpMethod.GET);
    }

    public static void createGroup(String groupName, long teacherProfileId){
        Request.sendSchoolRequestWithBody(schoolPrefix +groupUrl, HttpMethod.POST, Map.of("name", groupName, "teacherProfileId", teacherProfileId));
    }

    public static void getGroup(long groupId){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +groupUrl+"?groupId={groupId}", HttpMethod.GET, String.valueOf(groupId));
    }

    public static void deleteGroup(long groupId){
        Request.sendSchoolRequestWithoutBody(schoolPrefix +groupUrl+"?groupId={groupId}", HttpMethod.DELETE, String.valueOf(groupId));
    }

    public static void addStudentsToGroup(List<Long> students, long groupId){
        var payload = new BulkGroupActionPayload(groupId, students);
        Request.sendSchoolRequestWithBody(schoolPrefix +addGroupStudentsUrl, HttpMethod.POST, payload);
    }

    public static void removeStudentsFromGroup(List<Long> students, long groupId){
        var payload = new BulkGroupActionPayload(groupId, students);
        Request.sendSchoolRequestWithBody(schoolPrefix +removeGroupStudentsUrl, HttpMethod.POST, payload);
    }

    public static void assignTeacher(long teacherId, long groupId){
        var payload = new AssignTeacherToGroupPayload(teacherId, groupId);
        Request.sendSchoolRequestWithBody(schoolPrefix +assignTeacherUrl, HttpMethod.POST, payload);
    }
}
