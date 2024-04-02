package cubepay.com.utilities;
import cubepay.com.constants.FrameworkConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadConfigFile {
    private ReadConfigFile() {
    }
    private static final Properties properties = new Properties();
    private static final Map<String,String> map =  new HashMap<>();

    static{
        try (FileInputStream fileInputStream =
                     new FileInputStream(FrameworkConstants.getConfigFilePath())){

            properties.load(fileInputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                String key = String.valueOf(entry.getKey());
                String value = String.valueOf(entry.getValue());
                map.put(key,value);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static String getValue(String key){
        return map.get(key).trim();
    }
}
