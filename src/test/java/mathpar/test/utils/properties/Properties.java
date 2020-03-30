package mathpar.test.utils.properties;

import java.util.HashMap;
import java.util.Map;

public class Properties {
    public static final String AUTHENTICATION_TOKEN_NAME = "AUTH-TOKEN";
    public static final String CHOSEN_PROFILE_ID = "PROFILE-ID";

    public static String frontendUrl;

    public static Map<String, SchoolData> schoolDataList = new HashMap<>();
    public static Map<String, AccountData> accountDataList = new HashMap<>();

    public static void propagateProperties(){
        frontendUrl = PropertiesProcessor.getProperty("mathpar.frontend.api.url");
        var schoolDataProperties = PropertiesProcessor.getAllPropertiesByPattern("mathpar.resources.school.*");
        schoolDataProperties.forEach((key, value)->{
            var newKey = key.substring(25);
            var schoolData = value.split(";");
            var parsedSchool = new SchoolData(schoolData[0], schoolData[1]);
            schoolDataList.put(newKey, parsedSchool);
        });
        var accountDataProperties = PropertiesProcessor.getAllPropertiesByPattern("mathpar.resources.account.*");
        accountDataProperties.forEach((key, value)->{
            var newKey = key.substring(26);
            var schoolData = value.split(";");
            var parsedSchool = new AccountData(schoolData[0], schoolData[1], schoolData[2]);
            accountDataList.put(newKey, parsedSchool);
        });
    }
}
