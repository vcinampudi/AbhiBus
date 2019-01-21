package abhibus.abhibusAtos.library.javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class Javalib {

	private WebDriver driver;
	private Logger log = LoggerHelper.getlogger(Javalib.class);

	public Javalib(WebDriver driver) {
		this.driver = driver;
		log.info("Java SCript has been initialised ");
	}

	public Object executeJavaScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public Object executeJavaScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	/**
	 * This method will scroll To Element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		log.info("Scroll to Element..");
		executeJavaScript("window.scrollTo(arguments[0],argument[1]", element.getLocation().x, element.getLocation().y);
	}

	/**
	 * This method will scroll To Element And Click
	 * 
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("Scroll to Element and Clicked on element");
	}

	/**
	 * This method will scroll InTo View
	 * 
	 * @param element
	 */
	public void scrollInToView(WebElement element) {
		log.info("scroll till web element");
		executeJavaScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * This method will scroll InTo View And Click
	 * 
	 * @param element
	 */
	public void scrollInToViewAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		executeJavaScript("arguments[0].scrollIntoView()", element);
		log.info("Scroll Into View and clicked on element " + element.toString());
	}

	/**
	 * This method will scroll Down Vertically
	 */
	public void scrollDownVertically() {
		log.info("Scrolling down vertically....");
		executeJavaScript("window.scrollTo(0,document.body.scrolHeight)");
	}

	/**
	 * This method will scroll Up Vertically
	 */
	public void scrollUpVertically() {
		log.info("Scrolling up Vertically....");
		executeJavaScript("window.scrollTo(0,-document.body.scrolHeight)");
	}

	/**
	 * This method will scroll Down By Pixel
	 * 
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		log.info("Scrolling Down By Pixel..");
		executeJavaScript("window.scrollBY(0," + pixel);
	}

	/**
	 * This method will scroll Up By Pixel
	 * 
	 * @param pixel
	 */
	public void scrollUpByPixel(int pixel) {
		log.info("Scrolling Up By Pixel..");
		executeJavaScript("window.scrollBY(0,-" + pixel);
	}

	/**
	 * This method will zoom In 100%
	 * 
	 * @param pixel
	 */
	public void zoomInBy100Percent() {
		log.info("Zoom in 100%  ...");
		executeJavaScript("document.body.style.zoom='100%'");
	}

	/**
	 * This method will zoom In the page 60%
	 */
	public void zoomInBy60Percent() {
		log.info("Zoom in 60% ...");
		executeJavaScript("document.body.style.zoom='60%'");
	}

	/**
	 * This method will zoom In the page 40%
	 */
	public void zoomInBy40Percent() {
		log.info("Zoom in 60% ...");
		executeJavaScript("document.body.style.zoom='40%'");
	}

	/**
	 * This method will click on Element
	 * 
	 * @param element
	 */
	public void clieckElement(WebElement element) {
		log.info("Java Script Click Element...");
		executeJavaScript("arguments[0].click();", element);
	}

}
