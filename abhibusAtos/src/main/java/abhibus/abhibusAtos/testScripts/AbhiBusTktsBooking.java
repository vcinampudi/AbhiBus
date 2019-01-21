package abhibus.abhibusAtos.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import abhibus.abhibusAtos.PageObjects.HomePage;
import abhibus.abhibusAtos.TestSuit.TestSuit;
import abhibus.abhibusAtos.library.assertion.AssertionLib;
import abhibus.abhibusAtos.library.browserConfiguration.config.ObjectReader;
import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class AbhiBusTktsBooking extends TestSuit {

	private Logger log = LoggerHelper.getlogger(AbhiBusTktsBooking.class);

	HomePage homepg;

	@Test
	public void verifyLogoToolTip() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		homepg = new HomePage(driver);
		boolean status = homepg.matchToolTip();
		AssertionLib.updateTestStatus(status);
	}

	@Test(priority = 0, dependsOnMethods = { "verifyLogoToolTip" })
	public void Searh_Buses() {
		homepg.enter_Soure_destination(ObjectReader.reader.getstartLocation(), ObjectReader.reader.getToLocation());
		boolean status = homepg.SelectTravelDates();
		AssertionLib.updateTestStatus(status);
	}
	
	

}