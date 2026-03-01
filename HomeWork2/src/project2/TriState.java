package project2;

/** Represents a TriState non-resident student from NY or CT, eligible for a tuition discount when full-time */
public class TriState extends NonResident{
    private String state;
    private static final double NEW_YORK_TUITION_DISCOUNT = 4000;
    private static final double CONNECTICUT_DISCOUNT = 5000;

    /** Creates a TriState student from NY or CT
     * @param profile student profile
     * @param major student major
     * @param creditCompleted credits already earned
     * @param state the state the student is from, either NY or CT */
    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /** Returns the state the student is from
     * @return the state string, either NY or CT */
    public String getState(){
        return state;
    }

    /** Calculates tuition for a TriState student, applying the state discount for full-time enrollment
     * @param enrolledCredits the number of credits being taken this semester
     * @return the total tuition cost after applying any applicable state discount */
    @Override
    public double tuition(int enrolledCredits) {
        double total = super.tuition(enrolledCredits);

        if (enrolledCredits >= FULL_TIME_MIN_CREDS) {
            if (state.equalsIgnoreCase("CT")) {
                total = total - CONNECTICUT_DISCOUNT;
            }
            else if (state.equalsIgnoreCase("NY")){
                total = total - NEW_YORK_TUITION_DISCOUNT;
            }
        }

        return total;
    }

    /** Returns the tri-state type label including the student state, used in toString
     * @return label string such as "tri-state:NY" */
    @Override
    protected String getStatusLabel() {
        return "tri-state:" + state;
    }

}
