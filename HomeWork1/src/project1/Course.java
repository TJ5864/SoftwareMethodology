package project1;

/**
 * @author mss444
 * Creates the Course object
 *
 */

public class Course{

    private String courseNum;
    private int creditHours;
    private String standing;
    private String major;

    /**
     * Default Constructor
     */
    public Course(){

    /**
     *
     * @param courseNum
     * @param creditHours
     * @param standing
     * @param major
     */
    public Course(String courseNum, int creditHours, String standing, String major) {
        this.courseNum = courseNum;
        this.creditHours = creditHours;
        this.standing = standing;
        this.major = major;
    }

    @Override
    public boolean equals(Course course) {
        if (this.courseNum.equals(course.getCourseNum())){
            return true;
        }
        return false;
        }
    /**
     * Getter method
     * @return
     */

    public String getCourseNum() {
        return courseNum;
    }

    /**
     * Getter method
     * @return
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Getter method
     * @return
     */
    public String getStanding() {
        return standing;
    }

    /**
     * Getter method
     * @return
     */
    public String getMajor(){
        return major;
    }

    /**
     * Setter method
     * @return
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * Setter method
     * @param creditHours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * Setter method
     * @param standing
     */
    public void setStanding(String standing) {
        this.standing = standing;
    }

    /**
     * Setter method
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }


}