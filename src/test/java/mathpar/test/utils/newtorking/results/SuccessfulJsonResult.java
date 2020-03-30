package mathpar.test.utils.newtorking.results;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.json.JSONObject;

public class SuccessfulJsonResult extends SuccessfulResult {
    @Getter
    protected final JSONObject responseBody;

    public SuccessfulJsonResult(JSONObject responseBody) {
        super();
        this.responseBody = responseBody;
    }

    public <T> T getResponse(Class<T> clazz){
        try {
            return new ObjectMapper().readValue(responseBody.toString(), clazz);
        }catch (Exception e){
            throw new RuntimeException(String.format("Can't map answer %s to type %s", responseBody.toString(), clazz.getName()), e);
        }
    }
}
