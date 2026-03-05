package project2;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for Resident.tuition().
 * Tests 3 cases: full-time, full-time with scholarship, and part-time.
 * @author tjt97
 */
public class ResidentTest {

    private final Profile johnDoe2000 = new Profile("John", "Doe", new Date(1, 1, 2000));

    /** Full-time resident (15 credits, no scholarship): $14,933 + $3,891 = $18,824.00 */
    @Test
    public void testFullTimeTuition() {
        Resident resident = new Resident(johnDoe2000, Major.CS, 60);
        assertEquals(18824.00, resident.tuition(15), 0.001);
    }

    /** Full-time resident with $1,000 scholarship: $14,933 + $3,891 - $1,000 = $17,824.00 */
    @Test
    public void testFullTimeTuitionWithScholarship() {
        Resident resident = new Resident(johnDoe2000, Major.CS, 60);
        resident.setScholarship(1000);
        assertEquals(17824.00, resident.tuition(15), 0.001);
    }

    /** Part-time resident (9 credits): $1,945.50 + (9 × $482) = $6,283.50 */
    @Test
    public void testPartTimeTuition() {
        Resident resident = new Resident(johnDoe2000, Major.CS, 60);
        assertEquals(6283.50, resident.tuition(9), 0.001);
    }
}
