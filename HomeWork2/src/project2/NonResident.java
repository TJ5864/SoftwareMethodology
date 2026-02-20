package project2;

/**
 * This is the non-resident class, calculating the tuition based on credits and tuition discount.
 */
public class NonResident extends Student {
    private static final double FULL_TIME_TUITION = 35758;
    protected static final double FULL_TIME_UNIVERSITY_FEE = 3891;
    private static final double PART_TIME_RATE = 1162;
    protected static final double PART_TIME_UNIVERSITY_FEE = FULL_TIME_UNIVERSITY_FEE / 2.0;
    protected static final int FULL_TIME_MIN_CREDS = 12;
    private static final int EXTRA_THRESHOLD = 16;

    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    @Override
    public double tuition(int enrolledCredits) {
        if (enrolledCredits < FULL_TIME_MIN_CREDS) {
            return PART_TIME_RATE * enrolledCredits + PART_TIME_UNIVERSITY_FEE;
        }
        else if (enrolledCredits > EXTRA_THRESHOLD) {
            return FULL_TIME_TUITION + FULL_TIME_UNIVERSITY_FEE + (enrolledCredits - EXTRA_THRESHOLD) * PART_TIME_RATE;
        }
        else {
            return FULL_TIME_TUITION + FULL_TIME_UNIVERSITY_FEE;
        }
    }
}
