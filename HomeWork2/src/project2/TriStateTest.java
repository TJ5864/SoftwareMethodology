package project2;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for TriState.tuition().
 * Tests 2 cases: full-time (discount applies) and part-time (no discount).
 * @author tjt97
 */
public class TriStateTest {

    private final Profile johnDoe2000 = new Profile("John", "Doe", new Date(1, 1, 2000));

    /** Full-time NY student (15 credits): $35,758 + $3,891 - $4,000 NY discount = $35,649.00 */
    @Test
    public void testFullTimeNYTuition() {
        TriState student = new TriState(johnDoe2000, Major.CS, 60, "NY");
        assertEquals(35649.00, student.tuition(15), 0.001);
    }

    /** Part-time CT student (9 credits): (9 × $1,162) + $1,945.50 = $12,403.50 — no discount for part-time */
    @Test
    public void testPartTimeCTTuition() {
        TriState student = new TriState(johnDoe2000, Major.CS, 60, "CT");
        assertEquals(12403.50, student.tuition(9), 0.001);
    }
}
