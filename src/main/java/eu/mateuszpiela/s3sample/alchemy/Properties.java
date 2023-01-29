package eu.mateuszpiela.s3sample.alchemy;

import java.io.FileInputStream;
import java.io.IOException;

public final class Properties {
	private static Properties instance;
	private java.util.Properties properties; 
	
    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }
    
    private void loadConfigFile() throws IOException {
    	FileInputStream file = new FileInputStream("./application.properties");
    	properties = new java.util.Properties();
    	properties.load(file);
    	file.close();
    }
    
    public String getConfig(String option_name) throws IOException {
    	if(properties == null) {
    		loadConfigFile();
    	}
    	
    	return properties.getProperty(option_name);
    }
}
