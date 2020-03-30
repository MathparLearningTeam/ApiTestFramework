package mathpar.test.contexts;

import io.cucumber.java.After;
import mathpar.test.apis.SchoolApi;
import mathpar.test.utils.dto.frontend.responses.SchoolResponse;

import java.util.LinkedList;
import java.util.List;

public class SchoolContext {
    private final static SchoolContext instance = new SchoolContext();
    private List<SchoolResponse> createdSchools = new LinkedList<>();

    @After
    public static void cleanSchoolUp(){
        for(var school: instance.createdSchools){
            AuthenticationContext.useAuthentication(school.getDirector().getAccountId());
            SchoolApi.removeSchool();
        }
        instance.createdSchools = new LinkedList<>();
    }

    public void addCreatedSchool(SchoolResponse school){
        this.createdSchools.add(school);
    }

    public SchoolResponse getSchoolByName(String schoolName){
        for(var school: createdSchools){
            if(school.getSchoolName().equals(schoolName)) return school;
        }
        throw new RuntimeException(String.format("School with name %s hasn't been created", schoolName));
    }

    public static SchoolContext getContext(){
        return instance;
    }
}
