package project2;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for International.tuition().
 * Tests 2 cases: study abroad and not study abroad.
 * @author tjt97
 */
public class InternationalTest {

    private final Profile johnDoe2000 = new Profile("John", "Doe", new Date(1, 1, 2000));

    /** Study abroad: university fee + admin fee + health insurance = $3,891 + $500 + $2,650 = $7,041.00 */
    @Test
    public void testStudyAbroadTuition() {
        International student = new International(johnDoe2000, Major.CS, 60, true);
        assertEquals(7041.00, student.tuition(12), 0.001);
    }

    /** Not study abroad, full-time (15 credits): $35,758 + $3,891 + $500 + $2,650 = $42,799.00 */
    @Test
    public void testNotStudyAbroadTuition() {
        International student = new International(johnDoe2000, Major.CS, 60, false);
        assertEquals(42799.00, student.tuition(15), 0.001);
    }
}
