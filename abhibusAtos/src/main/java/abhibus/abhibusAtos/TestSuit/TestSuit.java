package abhibus.abhibusAtos.TestSuit;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import abhibus.abhibusAtos.library.browserConfiguration.BrowserType;
import abhibus.abhibusAtos.library.browserConfiguration.ChromeBrowser;
import abhibus.abhibusAtos.library.browserConfiguration.config.ObjectReader;
import abhibus.abhibusAtos.library.browserConfiguration.config.PropertyReader;
import abhibus.abhibusAtos.library.wait.Waitlib;

public class TestSuit {
	public WebDriver driver;
	public static File reportDirectory;

	private Logger log = Logger.getLogger(TestSuit.class);

	@BeforeClass
	public void beforeClass() throws Exception {
		ObjectReader.reader = new PropertyReader();
//		reportDirectory = new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\screenShots"));
		setupDriver(ObjectReader.reader.geBrowserType());
	}

	public void setupDriver(BrowserType bType) throws Exception {
		driver = getBrowserObject(bType);
		log.info("Initialised " + bType.toString() + " Web Driver and hashcode is " + bType.hashCode());
		Waitlib wait = new Waitlib(driver);
		wait.setImplicityWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info(bType.name() + " browser maximized..");
	}

	/**
	 * Get Browser Object to Configure
	 * 
	 * @param bType
	 * @return
	 * @throws Exception
	 */
	public WebDriver getBrowserObject(BrowserType bType) throws Exception {
		try {
			switch (bType) {
			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				log.info(bType.name() + " browser has initialised");
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			default:
				throw new Exception("Driver not found" + bType.name());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * Log an Entry when new Test is starting execution
	 * 
	 * @param method
	 */
	@BeforeMethod
	public void beforeMethod(Method method) {
		log.info("");
		log.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		log.info(method.getName() + " Test has started");
		log.info("");
	}

	/**
	 * Log an Entry when new Test is Completed execution
	 * 
	 * @param method
	 */
	@AfterMethod
	public void afterMethod(Method method) {
		log.info("");
		log.info(method.getName() + " Test has completed");
		log.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		log.info("");
	}

	/**
	 * Open Application Url
	 * 
	 * @param url
	 */
	public void getApplicationUrl(String url) {
		driver.get(url);
		log.info("Navigating to url " + url);
	}

	@AfterClass
	public void afterTest() {
		log.info("All Test has been fineshed and Closing browser");
		if (driver != null) {
			driver.close();
//			driver.quit();
			log.info(ObjectReader.reader.geBrowserType() + " Browser Closed");
		} else {
			log.info(ObjectReader.reader.geBrowserType() + " Browser already Closed");
		}
	}

}
