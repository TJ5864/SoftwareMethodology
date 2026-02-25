package project2;

import org.junit.Test;
import util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test class for Profile.compareTo().
 * Tests 7 cases covering last name, first name, and DOB ordering.
 * @author tjt97
 */
public class ProfileTest {

    // Shared profiles used across tests
    private final Profile johnDoe2000   = new Profile("John", "Doe",   new Date(1, 1, 2000));
    private final Profile janeDoe2000   = new Profile("Jane", "Doe",   new Date(1, 1, 2000));
    private final Profile adamSmith2000 = new Profile("Adam", "Smith", new Date(1, 1, 2000));
    private final Profile johnBrown2000 = new Profile("John", "Brown", new Date(1, 1, 2000));
    private final Profile johnDoe1999   = new Profile("John", "Doe",   new Date(1, 1, 1999));
    private final Profile johnDoe2001   = new Profile("John", "Doe",   new Date(1, 1, 2001));
    private final Profile johnDoe2000b  = new Profile("John", "Doe",   new Date(1, 1, 2000));

    /** Same last name; Jane comes before John alphabetically → negative */
    @Test
    public void testSameLastNameFirstNameJaneBeforeJohn() {
        assertTrue(janeDoe2000.compareTo(johnDoe2000) < 0);
    }

    /** Smith comes after Doe alphabetically → positive */
    @Test
    public void testLastNameSmithAfterDoe() {
        assertTrue(adamSmith2000.compareTo(johnDoe2000) > 0);
    }

    /** Brown comes before Doe alphabetically → negative */
    @Test
    public void testLastNameBrownBeforeDoe() {
        assertTrue(johnBrown2000.compareTo(johnDoe2000) < 0);
    }

    /** Doe comes after Brown alphabetically → positive */
    @Test
    public void testLastNameDoeAfterBrown() {
        assertTrue(johnDoe2000.compareTo(johnBrown2000) > 0);
    }

    /** Same name, earlier DOB (1999) comes before later DOB (2000) → negative */
    @Test
    public void testSameNameOlderDobFirst() {
        assertTrue(johnDoe1999.compareTo(johnDoe2000) < 0);
    }

    /** Same name, later DOB (2001) comes after earlier DOB (2000) → positive */
    @Test
    public void testSameNameYoungerDobLast() {
        assertTrue(johnDoe2001.compareTo(johnDoe2000) > 0);
    }

    /** Identical profiles → 0 */
    @Test
    public void testIdenticalProfiles() {
        assertEquals(0, johnDoe2000.compareTo(johnDoe2000b));
    }
}
