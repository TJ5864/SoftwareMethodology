package project2;

public class Resident extends Student {
    private int scholarship;

    public Resident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
        this.scholarship = 0;
    }

    @Override
    public double tuition(int enrolledCredits){
        return 0;
    }
}
