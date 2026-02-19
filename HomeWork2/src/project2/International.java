package project2;

public class International extends NonResident{
    private boolean isStudyAbroad;


    public International(Profile profile, Major major, int creditsCompleted, boolean isStudyAbroad){
        super(profile,major,creditsCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }
}
