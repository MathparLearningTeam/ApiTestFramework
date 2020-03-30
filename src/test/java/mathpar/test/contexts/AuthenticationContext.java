package mathpar.test.contexts;

import io.cucumber.java.Before;
import mathpar.test.utils.dto.Account;
import mathpar.test.utils.dto.Token;
import mathpar.test.utils.dto.frontend.responses.TokenResponse;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationContext {
    private static Token currentAuthentication;
    private static Account currentAccount;

    private static Map<Long, Token> authentications = new HashMap<>();

    @Before
    public static void cleanCurrentAuthentication(){
        authentications = new HashMap<>();
        currentAuthentication = null;
    }

    public static void addAuthentication(long accountId, TokenResponse tokenResponse){
        authentications.put(accountId, new Token(tokenResponse.getToken(), tokenResponse.getExpirationDate()));
    }

    public static void useAuthentication(long accountId){
        currentAuthentication=authentications.get(accountId);
        currentAccount = AccountContext.getContext().getAccount(accountId);
    }

    public static void useAuthentication(String accountEmail){
        var accountId = AccountContext.getContext().getAccount(accountEmail).getId();
        currentAuthentication=authentications.get(accountId);
        currentAccount = AccountContext.getContext().getAccount(accountId);
    }

    public static Account getCurrentAccount(){
        return currentAccount;
    }

    public static String getAuthenticationToken(){
        if(currentAuthentication==null) throw new RuntimeException("Authentication wasn't populated");
        return currentAuthentication.getToken();
    }
}
