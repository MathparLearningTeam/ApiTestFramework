package mathpar.test.apis;

import mathpar.test.utils.dto.authentication.payloads.CreateAccountPayload;
import mathpar.test.utils.dto.frontend.payloads.AuthenticationPayload;
import mathpar.test.utils.newtorking.Request;
import org.springframework.http.HttpMethod;

import java.util.Map;

import static mathpar.test.utils.properties.Properties.frontendUrl;
import static mathpar.test.utils.urls.AuthenticationUrls.*;

public class AccountApi {
    public static void createAccount(String email, String password, String fullName){
        Request.sendRequestWithBodyWithoutAuthentication(frontendUrl+ACCOUNT_URL, HttpMethod.POST, new CreateAccountPayload(email, password, fullName));
    }

    public static void authenticate(String email, String password){
        Request.sendRequestWithBodyWithoutAuthentication(frontendUrl+ AUTHENTICATE_URL, HttpMethod.POST, new AuthenticationPayload(email, password));
    }

    public static void removeAccount(long accountId){
        Request.sendRequestWithoutBody(frontendUrl+ACCOUNT_URL+"?accountId={accountId}", HttpMethod.DELETE, String.valueOf(accountId));
    }

    public static void isEmailAvailable(String email){
        Request.sendRequestWithoutBodyWithoutAuthentication(frontendUrl+IS_EMAIL_AVAILABLE_URL+"?email={email}", HttpMethod.GET, email);
    }

    public static void isTokenValid(String token){
        Request.sendRequestWithoutBodyWithoutAuthentication(frontendUrl+IS_TOKEN_VALID_URL+"?token={email}", HttpMethod.GET, token);
    }

    public static void restorePassword(String email){
        Request.sendRequestWithBodyWithoutAuthentication(frontendUrl+RESTORE_PASSWORD_URL, HttpMethod.POST, Map.of("email", email));
    }

    public static void changePassword(String token, String password){
        Request.sendRequestWithBodyWithoutAuthentication(frontendUrl+CHANGE_PASSWORD_URL, HttpMethod.POST, Map.of("token", token, "newPassword", password));
    }
}
