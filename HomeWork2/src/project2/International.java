package project2;

public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final double ADMIN_FEE = 500;
    private static final double HEALTH_INSURANCE_FEE = 2650;


    public International(Profile profile, Major major, int creditsCompleted, boolean isStudyAbroad){
        super(profile,major,creditsCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    @Override
    public double tuition(int enrolledCredits){
        if (isStudyAbroad) {
            return FULL_TIME_UNIVERSITY_FEE + ADMIN_FEE + HEALTH_INSURANCE_FEE;
        }

        double total = super.tuition(enrolledCredits);
        total = total + ADMIN_FEE + HEALTH_INSURANCE_FEE;
        return total;

    }

    public boolean isStudyAbroad(){
        return isStudyAbroad;
    }
}
