package mathpar.test.contexts;

import io.cucumber.java.After;
import mathpar.test.utils.newtorking.results.FailedRequestResult;
import mathpar.test.utils.newtorking.results.RequestResult;
import mathpar.test.utils.newtorking.results.SuccessfulJsonResult;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    private static Map<String, RequestResult> requestHistory = new HashMap<>();
    private static RequestResult lastRequestResult;

    @After
    public static void cleanHistory(){
        requestHistory = new HashMap<>();
        lastRequestResult = null;
    }

    public static void addToHistory(String key, RequestResult requestResult){
        requestHistory.put(key, requestResult);
        lastRequestResult = requestResult;
    }

    public static RequestResult getRequestResult(String key){
        return requestHistory.get(key);
    }

    public static RequestResult getLastRequestResult(){
        return lastRequestResult;
    }

    public static SuccessfulJsonResult getLastResultAsJson(){
        try {
            return (SuccessfulJsonResult) lastRequestResult;
        }catch (Exception e){
            throw new RuntimeException(String.format("Last request's result wasn't either successful or json %s: %s", lastRequestResult.status, ((FailedRequestResult) lastRequestResult).getBody()));
        }
    }
}
