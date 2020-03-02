package LoayNaser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetCoordinate {

	private static final Logger logger = LogManager.getLogger(LoayNaser.GetCoordinate.class);

	public static String getCassandraConnectionData(String Choose) {
		String result = null;
		Properties prop = new Properties();
		InputStream input = null;
		try {
			if (new File("Cassandra-config.properties").exists()) {
				input = new FileInputStream("Cassandra-config.properties");
				// load a properties file
				prop.load(input);
				// get the property value and print it out
				result = String.valueOf(prop.getProperty(Choose));
			}
			return result;

		} catch (IOException e) {
			logger.error(e);
			logger.error("Exception is:", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e);
					logger.error("Exception is:", e);
				}
			}
		}
		return result;
	}
}
