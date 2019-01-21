package abhibus.abhibusAtos.library.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import abhibus.abhibusAtos.library.resources.ResourceHelper;

public class LoggerHelper {

	private static boolean root = false;

	public static Logger getlogger(Class cls) {

		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator
				.configure(ResourceHelper.getResourcePath("\\src\\main\\resource\\config\\log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}
}