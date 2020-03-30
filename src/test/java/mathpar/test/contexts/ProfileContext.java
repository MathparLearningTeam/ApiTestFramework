package mathpar.test.contexts;

import io.cucumber.java.Before;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;

import java.util.LinkedList;

public class ProfileContext {
    private static LinkedList<SchoolProfileResponse> profiles = new LinkedList<>();

    public static SchoolProfileResponse getLastProfile(){
        return profiles.getLast();
    }

    @Before
    public static void cleanUp(){
        profiles = new LinkedList<>();
    }

    public static SchoolProfileResponse getProfile(long accountId){
        return profiles.stream().filter(profile -> profile.getAccountId() == accountId).findFirst().orElseThrow();
    }

    public static void addProfile(SchoolProfileResponse profile){
        profiles.addLast(profile);
    }

    public static SchoolProfileResponse getCurrentProfile(){
        return getProfile(AuthenticationContext.getCurrentAccount().getId());
    }
}
