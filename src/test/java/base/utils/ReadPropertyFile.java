package base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    public static Properties getPropertiesFile(String propertiesFileName) {
        InputStream inputStream = null;
        Properties properties = null;
        try {
            properties = new Properties();
            inputStream = ReadPropertyFile.class.getClassLoader().getResourceAsStream(propertiesFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (!(inputStream == null)) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

}

