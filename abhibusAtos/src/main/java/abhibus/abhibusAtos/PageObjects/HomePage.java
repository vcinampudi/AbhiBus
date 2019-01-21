package abhibus.abhibusAtos.PageObjects;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhibus.abhibusAtos.library.browserConfiguration.config.ObjectReader;
import abhibus.abhibusAtos.library.logger.LoggerHelper;
import abhibus.abhibusAtos.library.wait.Waitlib;

public class HomePage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getlogger(HomePage.class);
	Waitlib waitlib;

	@FindBy(xpath = "//*[@id=\"gmg_wp_notif\"]/div[2]/button[1]")
	WebElement xp_Later;

	@FindBy(css = "body > div.mainheader > div.wrap > a > img")
	public WebElement css_AbhiBusLogo;

	@FindBy(xpath = "//*[@id=\"source\"]")
	WebElement xp_LeavingFrom;

	@FindBy(xpath = "//*[@id=\"destination\"]")
	WebElement xp_GoingTo;

	@FindBy(xpath = "//*[@id=\"datepicker1\"]")
	WebElement xp_DateofJourney;

	@FindBy(xpath = "//*[@id=\"datepicker2\"]")
	WebElement xp_DateofReturn;

	@FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody")
	WebElement xp_DateGrid_fromDate;

	@FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody")
	WebElement xp_DateGrid_Retrun_Date;

	@FindBy(xpath = "//*[@id=\"roundTrip\"]/a")
	WebElement xp_Search;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitlib = new Waitlib(driver);
		waitlib.waitForElement(css_AbhiBusLogo, ObjectReader.reader.getExplicitWait());
	}

	public void enterFromLocation(WebElement element, String string) throws InterruptedException {
		element.sendKeys(string);
		Thread.sleep(1000);
		element.sendKeys(Keys.ENTER);
	}

	public boolean matchToolTip() {

		if (xp_Later.isDisplayed()) {
			xp_Later.click();
			log.info("Closing Notification Message ");
		}
		log.info("Moving on to element " + "\"" + css_AbhiBusLogo.getAttribute("title") + "\"");
		moveToElement(css_AbhiBusLogo);
		String eleTooltip = css_AbhiBusLogo.getAttribute("title").toString();
		if (eleTooltip.equalsIgnoreCase("abhibus.com - India's Fastest Online bus ticket booking site")) {
			log.info("Mathcing Tool tip information is " + eleTooltip);
			return true;
		} else {
			log.info("message is not matched");
			return false;
		}
	}

	/**
	 * This method will click on element pass
	 * 
	 * @param element
	 */
	private void moveToElement(WebElement element) {
		try {
			Actions ac = new Actions(driver).moveToElement(element);
			log.info("Moving on To Element " + element.toString());
			ac.build().perform();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.info("Exception occured " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param string
	 */
	private void enterFromLocation(String string) {
		try {
			log.info("entering From Location " + string.toString());
			clickOnElement(xp_LeavingFrom);
			xp_LeavingFrom.sendKeys(Keys.chord(string));
			Thread.sleep(1000);
			xp_LeavingFrom.sendKeys(Keys.ENTER);
		} catch (InterruptedException e) {
			log.info("From Locaiton Element Exception" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method will enter Value into Element
	 * 
	 * @param string
	 */
	private void enterToLocation(String string) {

		try {
			log.info("Entering To Location " + string.toString());
			clickOnElement(xp_GoingTo);
			xp_GoingTo.sendKeys(Keys.chord(string));
			Thread.sleep(1000);
			xp_GoingTo.sendKeys(Keys.ENTER);
		} catch (InterruptedException e) {
			log.info("To Location Element  exception" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method will click on element pass
	 * 
	 * @param element
	 */
	private void clickOnElement(WebElement element) {
		try {
			Actions ac = new Actions(driver).moveToElement(element);
			log.info("Clicking on Element " + element.toString());
			ac.click().build().perform();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.info("Exception occured " + e.getMessage());
		}
	}

	public void enter_Soure_destination(String s, String d) {
		enterFromLocation(s);
		enterToLocation(d);
	}

	public boolean selectStartDate() {
		try {
			String today = Integer.toString(getTodayDate());
			clickOnElement(xp_DateofJourney);
			selectDate(xp_DateGrid_fromDate, today);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean selectReturnDate() {
		try {
			String Tomorrow = Integer.toString(getTomorrowDate());
			clickOnElement(xp_DateofReturn);
			selectDate(xp_DateGrid_Retrun_Date, Tomorrow);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean SelectTravelDates() {
		try {
			log.info("Selecting Start Date..");
			boolean start = selectStartDate();
			log.info("Selecting Return Date..");
			boolean returnj = selectReturnDate();
			if (start && returnj) {
				log.info("Clicked on Search Buses..");
				xp_Search.click();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void selectDate(WebElement element, String string) {
		List<WebElement> columns = element.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			if (cell.getText().equals(string)) {
				log.info("Clicking on date " + string);
				cell.click();
				break;
			}
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Get Today Date
	private static int getTodayDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
//		long todayStr = Integer.toString(todayInt);
		return todayInt;
	}

	// Get Tomorrow Date
	private static int getTomorrowDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		int tomorrow = calendar.get(Calendar.DAY_OF_MONTH);
		return tomorrow;
	}

}