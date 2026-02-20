package project2;

public class NonResident extends Student {


    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    @Override
    public double tuition(int CreditCompleted){
        return 0.0;
    }
}
