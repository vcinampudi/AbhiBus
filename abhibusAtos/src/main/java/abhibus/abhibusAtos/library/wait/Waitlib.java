package abhibus.abhibusAtos.library.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class Waitlib {

	private WebDriver driver;
	private Logger log = LoggerHelper.getlogger(Waitlib.class);

	public Waitlib(WebDriver driver) {
		this.driver = driver;
	}

	public void setImplicityWait(long timeout, TimeUnit unit) {
		log.info("Implicit has set to => " + unit);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	/**
	 * This will help us to get WebDriverWait object
	 * 
	 * @param timeOutInSeconds
	 * @param PollingEveryMiliSeconds
	 * @return
	 */
	public WebDriverWait getwait(int timeOutInSeconds, int PollingEveryMiliSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(PollingEveryMiliSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure Element visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param PollingEveryMiliSeconds
	 */

	public void WaitForElementVisible(WebElement element, int timeOutInSeconds, int PollingEveryMiliSeconds) {
		log.info("Waiting for Element " + element.toString() + "for " + timeOutInSeconds + " seconds");
		WebDriverWait wait = getwait(timeOutInSeconds, PollingEveryMiliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now ");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for Element " + element.toString() + "for " + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now ");
	}

	/**
	 * This method will make sure element is invisible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean WaitForElementNotPresent(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for Element " + element.toString() + "for " + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is not visible now ");
		return status;
	}

	/**
	 * This method make sure frame To Be Available And Switch To It
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForFrameToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for frame Element " + element.toString() + "for " + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is available and switched now ");

	}

	/**
	 * This method will give is fluentWait Object
	 * 
	 * @param timeOutInSeconds
	 * @param PollingEveryMiliSeconds
	 * @return
	 */
	public Wait<WebDriver> getFluentWait(int timeOutInSeconds, int PollingEveryMiliSeconds) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(PollingEveryMiliSeconds))
				.ignoring(NoSuchElementException.class);
		return fwait;

	}

	/**
	 * This method will wait until Element loaded for specified time duration
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param PollingEveryMiliSeconds
	 * @return
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int PollingEveryMiliSeconds) {

		Wait<WebDriver> fwait = getFluentWait(timeOutInSeconds, PollingEveryMiliSeconds);
		WebElement status = fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	/**
	 * This method will wait to load page for specified time duration
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadTime(long timeout, TimeUnit unit) {
		log.info("Waiting for page to load for " + timeout + " Seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("Page is loaded");
	}

	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for : " + element.toString() + " for :" + timeOutInSeconds + " Seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		// return element;
		log.info("element is visible..");
	}
}
