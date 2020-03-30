package mathpar.test.contexts;

import io.cucumber.java.After;
import mathpar.test.apis.AccountApi;
import mathpar.test.utils.dto.Account;
import mathpar.test.utils.dto.authentication.responses.CreateAccountResponse;

import java.util.LinkedList;
import java.util.List;

public class AccountContext {
    private final static AccountContext instance = new AccountContext();
    private List<Account> createdAccounts = new LinkedList<>();

    @After(order = 1)
    public static void cleanUpAccounts(){
        for(var account: instance.createdAccounts){
            AuthenticationContext.useAuthentication(account.getId());
            AccountApi.removeAccount(account.getId());
        }
        instance.createdAccounts = new LinkedList<>();
    }

    public void addAccount(CreateAccountResponse response, String email, String password){
        createdAccounts.add(new Account(response.getAccountId(), email, password, response.getFullName(), response.getCreationDate()));
    }

    public Account getAccount(String email){
        return createdAccounts.stream().filter(account -> account.getEmail().equals(email)).findFirst().orElseThrow();
    }

    public Account getAccount(long accountId){
        return createdAccounts.stream().filter(account -> account.getId() == accountId).findFirst().orElseThrow();
    }

    public static AccountContext getContext(){
        return instance;
    }
}
