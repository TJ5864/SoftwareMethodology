package project2;

public class TriState extends NonResident{
    private String state;
    private static final double NEW_YORK_TUITION_DISCOUNT = 4000;
    private static final double CONNECTICUT_DISCOUNT = 5000;

    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }

    public String getState(){
        return state;
    }

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
}
