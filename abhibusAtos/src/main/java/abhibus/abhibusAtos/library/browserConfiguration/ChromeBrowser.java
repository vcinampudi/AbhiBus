package abhibus.abhibusAtos.library.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import abhibus.abhibusAtos.library.resources.ResourceHelper;

public class ChromeBrowser {
	
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");

		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		// Linux
		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}

	public WebDriver getChromeDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Windows 10")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath("\\src\\main\\resource\\drivers\\chromedriver.exe"));
			return new ChromeDriver(cap);

		} else if (System.getProperty("os.name").contains("Windows 7")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath("\\src\\main\\resource\\drivers\\chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		return null;

	}

}
