package abhibus.abhibusAtos.library.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import abhibus.abhibusAtos.library.logger.LoggerHelper;

public class AssertionLib {

	private static Logger log = LoggerHelper.getlogger(AssertionLib.class);

	public static void verifyText(String s1, String s2) {
		log.info("verify text : " + s1 + " with " + s2);
		Assert.assertEquals(s1, s2);
	}

	public static void makeTrue() {
		log.info("making script pass");
		Assert.assertTrue(true);
	}

	public static void makeFalse() {
		log.info("making script fail");
		Assert.assertFalse(true);
	}

	public static void makeTrue(String message) {
		log.info("making script pass.." + message);
		Assert.assertTrue(true, message);
	}

	public static void makeFalse(String message) {
		log.info("making script fail.." + message);
		Assert.assertTrue(false, message);
	}

	public static void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}

	public static void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}

	public static void verifyNull(String s1) {
		log.info("Verify object is Null..");
		Assert.assertNull(s1);
	}

	public static void verifyNotNull(String s1) {
		log.info("Verify object is Not Null..");
		Assert.assertNotNull(s1);
	}

	public static void pass() {
		Assert.assertTrue(true);
	}

	public static void fail() {
		Assert.assertTrue(false);
	}

	public static void updateTestStatus(boolean status) {
		if (status) {
			pass();
		} else {
			fail();
		}
	}

}
