package core.util;

import java.io.*;
import java.util.Properties;

public class Property {

	public static Properties fromFile(String filePath)  {
		Properties properties = new Properties();

		try {
		properties.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}


	public static void updateKeyValue(String filePath , String key , String value) throws IOException {
		FileInputStream in = new FileInputStream(filePath);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(filePath);
		props.setProperty(key, value);
		props.store(out, null);
		out.close();
	}

	public static String getValue(String filePath , String key) throws IOException {

		InputStream input = new FileInputStream(filePath);

			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty(key);
	}
}
