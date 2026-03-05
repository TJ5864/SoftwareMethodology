package project2;
/** This is the resident method, we calculate the tuition based on credits for Resident Students
 * We also set the student scholarship in this method
 * @author tjt97*/

public class Resident extends Student {
    private int scholarship;
    private static final double FULL_TIME_TUITION = 14933;
    private static final double PART_TIME_RATE = 482;
    private static final double FULL_TIME_FEE = 3891;
    private static final double PART_TIME_FEE = 1945.50;
    private static final int FULL_TIME_MIN = 12;
    private static final int EXTRA_THRESHOLD = 16;

    /** Creates a Resident student with no scholarship applied
     * @param profile student profile
     * @param major student major
     * @param creditCompleted credits already earned */
    public Resident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
        this.scholarship = 0;
    }
    /** This sets the Scholarship to be used in the tuition method
     * @param  amount the amount of scholarship  */
    public void setScholarship(int amount) {
        this.scholarship = amount;
    }

/**Calculates the tuition for a student who is a Resident
 * @param enrolledCredits the amount of credits a student is taking
 * @return cost the total cost of students tuition with fees and scholarships */
    @Override
    public double tuition(int enrolledCredits){
        if (enrolledCredits > EXTRA_THRESHOLD){
            double extraCredits = enrolledCredits - 16;
            double cost = FULL_TIME_FEE + FULL_TIME_TUITION + (extraCredits * PART_TIME_RATE) - scholarship;
            return cost;

        }else if(enrolledCredits < FULL_TIME_MIN){
            double cost = PART_TIME_FEE + (enrolledCredits * PART_TIME_RATE);
            return cost;

        }else{
            return FULL_TIME_TUITION + FULL_TIME_FEE - scholarship;

        }

    }

    /** Returns the resident type label used in toString
     * @return the string "resident" */
    @Override
    protected String getStatusLabel() {
        return "resident";
    }
//    @Override
//    public String toString() {
//        return super.toString() + "[resident]";
//    }

    /** Returns the scholarship label if a scholarship is set, empty string otherwise
     * @return formatted scholarship string or empty string */
    @Override
    protected String getScholarshipLabel() {
        if (scholarship == 0) {
            return "";
        }
        return "scholarship: $" + String.format("%,d", scholarship);
    }
}
