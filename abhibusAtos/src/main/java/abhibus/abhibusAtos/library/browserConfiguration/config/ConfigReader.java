package abhibus.abhibusAtos.library.browserConfiguration.config;

import abhibus.abhibusAtos.library.browserConfiguration.BrowserType;

public interface ConfigReader {

	public int getImplicitWait();

	public int getExplicitWait();

	public int getPageLoadTime();

	public BrowserType geBrowserType();

	public String getUrl();

	public String getstartLocation();

	public String getToLocation();

}
