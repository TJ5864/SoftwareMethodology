package util;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test class for Date.isValid().
 * Tests 4 invalid and 2 valid dates.
 * @author tjt97
 */
public class DateTest {

    // --- Invalid dates (4 cases) ---

    /** February 30 does not exist in any year */
    @Test
    public void testInvalidFeb30() {
        Date date = new Date(2, 30, 2024);
        assertFalse(date.isValid());
    }

    /** Month 13 does not exist */
    @Test
    public void testInvalidMonthTooHigh() {
        Date date = new Date(13, 10, 2024);
        assertFalse(date.isValid());
    }

    /** April has 30 days, not 31 */
    @Test
    public void testInvalidDay31InApril() {
        Date date = new Date(4, 31, 2024);
        assertFalse(date.isValid());
    }

    /** Year 1800 is before MIN_YEAR of 1900 */
    @Test
    public void testInvalidYearTooEarly() {
        Date date = new Date(1, 1, 1800);
        assertFalse(date.isValid());
    }

    // --- Valid dates (2 cases) ---

    /** February 29 is valid in 2024 because 2024 is a leap year */
    @Test
    public void testValidLeapYearFeb29() {
        Date date = new Date(2, 29, 2024);
        assertTrue(date.isValid());
    }

    /** December 25, 2025 is a normal valid date */
    @Test
    public void testValidNormalDate() {
        Date date = new Date(12, 25, 2025);
        assertTrue(date.isValid());
    }
}
