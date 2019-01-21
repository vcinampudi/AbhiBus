package abhibus.abhibusAtos.library.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class VerificationLib {

	private WebDriver driver;
	private Logger log = LoggerHelper.getlogger(VerificationLib.class);

	public VerificationLib(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method check if the element is displayed
	 * 
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed => " + element.getText());
			return true;
		} catch (Exception e) {
			log.info("element not displayed " + e.getMessage());
			return false;
		}
	}

	/**
	 * This Method ensure element is not displayed
	 * 
	 * @param element
	 * @return
	 */
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.." + element.getText());
			return true;
		} catch (Exception e) {
			log.info("element not displayed.." + e.getMessage());
			return false;
		}
	}

	/**
	 * This method will get text of the element
	 * 
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("Element text is " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	/**
	 * This method will get title of page
	 * 
	 * @return
	 */
	public String title() {
		log.info("page title is " + driver.getTitle());
		return driver.getTitle();
	}

}
