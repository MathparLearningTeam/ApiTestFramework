package mathpar.test.utils.properties;

import io.cucumber.java.Before;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PropertiesProcessor {

    @Before(order = 1)
    public void init(){
        if(PropertiesProcessor.arePropertiesLoaded()) return;
        loadProperties();
        //TODO add auto-discovery of properties aware classes
        Properties.propagateProperties();
    }

    private static Map<String, String> properties;

    public static String getProperty(String key){
        if(properties == null) throw new RuntimeException("Properties hasn't been initialized");
        return properties.get(key);
    }

    public static Map<String, String> getAllPropertiesByPattern(String pattern){
        if(properties == null) throw new RuntimeException("Properties hasn't been initialized");
        var matcher = Pattern.compile(pattern).asMatchPredicate();
        var result = new LinkedHashMap<String, String>();
        for(String key: properties.keySet()){
            if(matcher.test(key)) result.put(key, properties.get(key));
        }
        return result;
    }

    public static void loadProperties(){
        try {
            var profileName = System.getProperty("profile");
            var propertiesFileName="application"+(profileName!=null?"-"+profileName:"")+".properties";
            var resource = PropertiesProcessor.class.getClassLoader().getResource(propertiesFileName);
            if (resource==null) throw new RuntimeException("Can't find properties file, check existence");
            var allProperties = new String(resource.openStream().readAllBytes()).split("\n");
            properties = new HashMap<>(allProperties.length);
            for(String line: allProperties){
                var separatorIndex = line.indexOf("=");
                String key = line.substring(0, separatorIndex), value = line.substring(separatorIndex+1);
                properties.put(key, value);
            }
        }catch (IOException | NullPointerException e){
            throw new RuntimeException("Can't load application.properties file, check existence and", e);
        }
    }

    public static boolean arePropertiesLoaded(){
        return properties!=null;
    }
}
