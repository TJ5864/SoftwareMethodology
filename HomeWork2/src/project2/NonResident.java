package project2;

/**
 * This is the non-resident class, calculating the tuition based on credits and tuition discount.
 */
public class NonResident extends Student {
    private static final double NEW_YORK_TUITION_DISCOUNT = 4000;
    private static final double CONNECTICUT_DISCOUNT = 5000;
    private static final double FULL_TIME_UNVERSITY_FEE = 3891;
    private static final double PART_TIME_RATE = 1162;
    private static final double PART_TIME_UNIVERSITY_FEE = 1945.50;
    private static final int FULL_TIME_MIN_CREDS = 12;
    private static final int

    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    @Override
    public double tuition(int CreditCompleted){



    }
}
