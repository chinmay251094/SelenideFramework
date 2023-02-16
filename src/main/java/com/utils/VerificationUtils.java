package com.utils;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.constants.FrameworkConstants.ASSERTION_FOR;
import static com.reports.ReportsLogger.fail;
import static com.reports.ReportsLogger.pass;

public class VerificationUtils {
    public static void validate(Object actual, Object expected, String message) {
        try {
            logFile(actual, expected);
            Assert.assertEquals(actual, expected, message);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
            Assertions.fail(message);
        }
    }

    public static void validate(List<WebElement> listOfWebElements, String message) {
        try {
            Assertions.assertThat(listOfWebElements).isNotEmpty();
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b>" + "<u>" + listOfWebElements.get(0).getText() + "</u>" + " and <b><i> Expected: </i> </b>" + "<u>Present</u>",
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + " | <b><i>Actual: </i> </b>" + "<u>" + listOfWebElements.get(0).getText() + "</u>" + " and <b><i> Expected: </i> </b>" + "<u>Not Present</u>");
            Assertions.fail(message);
        }
    }

    public static void validateURL(Object actual, Object expected, String message) {
        try {
            logFile(actual, expected);
            Assert.assertNotEquals(actual, expected, message);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
            Assert.fail(message);
        }
    }

    public static void assertURL(Object intialURL, Object finalURL, String message) {
        try {
            logFile(intialURL, finalURL);
            Assertions.assertThat(intialURL).isNotEqualTo(finalURL);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b><u>" + intialURL + "</u> and <b><i> Expected: </i> </b><u>" + finalURL + "</u>",
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + "   |   <b><i>Actual: </i> </b>" + intialURL + " and <b><i> Expected: </i> </b>" + finalURL);
            Assert.fail(message);
        }
    }

    private static void logFile(Object actual, Object expected) {
//        TestUtils.log().info("Actual: " + actual);
//        TestUtils.log().info("Expected: " + expected);
    }

    public static void validateResponse(boolean result, String message) {
        try {
            Assert.assertTrue(result);
            pass("<b><i>" + message + "</b></i>", true);
        } catch (AssertionError assertionError) {
            fail("<b><i>" + message + "</b></i>");
            Assert.fail(message);
        }
    }
}
