package project2;

/** Represents an international non-resident student, subject to admin and health insurance fees
 * @author mss444*/
public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final double ADMIN_FEE = 500;
    private static final double HEALTH_INSURANCE_FEE = 2650;


    /** Creates an International student
     * @param profile student profile
     * @param major student major
     * @param creditsCompleted credits already earned
     * @param isStudyAbroad true if the student is enrolled in a study abroad program */
    public International(Profile profile, Major major, int creditsCompleted, boolean isStudyAbroad){
        super(profile,major,creditsCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /** Calculates tuition for an international student including admin and health insurance fees
     * Study abroad students pay only the university fee plus admin and health insurance fees
     * @param enrolledCredits the number of credits being taken this semester
     * @return the total tuition cost including all applicable fees */
    @Override
    public double tuition(int enrolledCredits){
        if (isStudyAbroad) {
            return FULL_TIME_UNIVERSITY_FEE + ADMIN_FEE + HEALTH_INSURANCE_FEE;
        }

        double total = super.tuition(enrolledCredits);
        total = total + ADMIN_FEE + HEALTH_INSURANCE_FEE;
        return total;

    }

    /** Returns whether this student is in a study abroad program
     * @return true if study abroad, false otherwise */
    public boolean isStudyAbroad(){
        return isStudyAbroad;
    }


    /** Returns the international type label, indicating study abroad status if applicable
     * @return label string such as "international" or "international:study abroad" */
    @Override
    protected String getStatusLabel() {
        if (isStudyAbroad) {
            return "international:study abroad";
        }
        return "international";
    }
//    @Override
//    public String toString() {
//        if (isStudyAbroad) {
//            return super.toString() + " [international:study abroad]";
//        }
//
//        return super.toString() + " [international]";
//    }
}
