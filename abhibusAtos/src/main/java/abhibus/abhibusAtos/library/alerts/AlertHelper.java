package abhibus.abhibusAtos.library.alerts;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class AlertHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getlogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHelper class initialised");
	}

	public Alert getAlert() {
		log.info("alert test " + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("Accept allert is done ...");
	}

	public void DismissAlert() {
		getAlert().dismiss();
		log.info("Dismiss allert is done ...");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("allert text " + text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		} catch (Exception e) {
			log.info("alert not present " + e.getCause());
			return false;
		}
	}

	public void acceptIfAlertPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void dismissIfAlertPresent() {
		if (isAlertPresent()) {
			DismissAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void acceptPrompt(String text) {
		if (isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text " + text);
		}
	}

}
