package abhibus.abhibusAtos.library.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import abhibus.abhibusAtos.library.browserConfiguration.BrowserType;
import abhibus.abhibusAtos.library.resources.ResourceHelper;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("\\src\\main\\resource\\config\\config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		// TODO Auto-generated method stub
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType geBrowserType() {
		// TODO Auto-generated method stub
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return OR.getProperty("applicationurl");
	}

	public String getstartLocation() {
		// TODO Auto-generated method stub
		if (System.getProperty("fromlocation") != null) {
			return System.getProperty("fromlocation");
		}
		return OR.getProperty("fromlocation");
	}

	public String getToLocation() {
		// TODO Auto-generated method stub
		if (System.getProperty("tolocation") != null) {
			return System.getProperty("tolocation");
		}
		return OR.getProperty("tolocation");
	}

	public static void main(String[] args) {
		PropertyReader g = new PropertyReader();
		System.out.println(g.getstartLocation());
	}

}
