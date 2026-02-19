package project2;

public class NonResident extends Student {
    private int scholarship;

    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
        this.scholarship = 0;
    }

    @Override
    public double tuition(int CreditCompleted){
        return 0.0;
    }
}
