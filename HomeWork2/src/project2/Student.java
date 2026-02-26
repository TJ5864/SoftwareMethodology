package project2;

/** Represent Student class
 * Students include profile, Major and Credits taken
 * @author tjt97*/
public abstract class Student implements Comparable<Student> {
    private static final int MAX_FRESHMAN_CREDITS = 30;
    private static final int MAX_SOPHOMORE_CREDITS = 60;
    private static final int MAX_JUNIOR_CREDITS = 90;
    private Profile profile;
    private Major major;
    private int creditCompleted;

    /** Create the Student object
     * @param profile student's profile
     * @param major student's major
     * @param creditCompleted credits earned */
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;

    }

/** The abstract method Tuition that will be used in the other classes*/
    public abstract double tuition(int creditCompleted);

/** returns the student major object */
    public Major getMajor() {
        return this.major;
    }

/** returns the student credit completed */
    public int getCreditCompleted() {
        return creditCompleted;
    }


    /** Compare student using profile
     * @param student2 student we want to compare
     * @return negative zero or positive based on students age*/
    @Override
    public int compareTo(Student student2){
        return this.profile.compareTo(student2.profile);
    }

    public Profile getProfile() {
        return profile;
    }

    /** Check if 2 students are equal based on profiles
     * Same student
     * @param student2 the student we are comparing
     * @return True if profiles are the same false otherwise*/
    @Override
    public boolean equals(Object student2){
        if (this == student2){
            return true;
        }
        if(!(student2 instanceof Student)){
            return false;
        }
        Student student = (Student) student2;
        return profile.equals(student.profile);

    }

    /** Get the student grade level based on credits
     * @return student grade level*/
    public Standing getStanding(){
        if(creditCompleted < MAX_FRESHMAN_CREDITS){
            return Standing.FRESHMAN;
        }
        if(creditCompleted < MAX_SOPHOMORE_CREDITS){
            return Standing.SOPHOMORE;
        }
        if(creditCompleted < MAX_JUNIOR_CREDITS){
            return Standing.JUNIOR ;
        }
        return Standing.SENIOR;
    }



    /** Give us the students information as a string
     * contains profile, major and credits
     * @return student description*/
    @Override
    public String toString(){

        String label = getStatusLabel();
        String suffix = (label == null || label.isBlank()) ? "" : "[" + label + "]";
        return profile + " [" + major + "] credits earned: " + creditCompleted +
                " [" + getStanding().getStanding() + "]" + suffix;
    }

    protected String getStatusLabel() {
        return "";
    }


}
