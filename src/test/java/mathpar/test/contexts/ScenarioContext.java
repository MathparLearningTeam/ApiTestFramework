package mathpar.test.contexts;

import io.cucumber.java.Before;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static Map<String, Object> context;

    @Before
    public static void clearContext(){
        context = new HashMap<>();
    }

    public static void addToContext(String key, Object value){
        context.put(key, value);
    }

    public static <T> T getFromContext(String key, Class<T> clazz){
        var object = context.get(key);
        if(object == null) throw new RuntimeException(String.format("There is no object with key %s in context", key));
        try {
            return clazz.cast(object);
        }catch (Exception e){
            throw new RuntimeException(String.format("%s can't be cast to desired type", object.getClass()));
        }
    }
}
