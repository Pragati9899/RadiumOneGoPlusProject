package cubepay.com.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cubepay.com.constants.FrameworkConstants;
import cubepay.com.enums.ConfigProperties;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {
    private static final Map<String, String> map;

    private JsonUtils() {
        // Private constructor to prevent instantiation
    }

    static {
        try {
            String configJsonFilePath = FrameworkConstants.getConfigJsonFilePath();
            map = new ObjectMapper().readValue(new File(configJsonFilePath), new TypeReference<HashMap<String, String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage(), e);
        }
    }


    public static String getValue(ConfigProperties key)  {
        if(Objects.isNull(key) || Objects.isNull(map.get(key.name().toLowerCase()))){
            throw new IllegalArgumentException(key +" is not found, check the config.properties file");
        }
        return map.get(key.name().toLowerCase());
    }

}

