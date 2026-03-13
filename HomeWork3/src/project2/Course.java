package project2;
/**This is an enum class for the Courses.
 * Creates courses with Course ID, Credit hours, Standing, and required Major*
 * @author mss444
 */
public enum Course {
    CS100("CS100", 4, Standing.FRESHMAN, null),
    CS200("CS200", 4, Standing.SOPHOMORE, null),
    CS300("CS300", 4, Standing.JUNIOR, Major.CS),
    CS400("CS400", 4, Standing.JUNIOR, Major.CS),
    CS442("CS442", 3, Standing.JUNIOR, null),
    PHY100("PHY100", 5, Standing.FRESHMAN, null),
    PHY200("PHY200", 5, Standing.SOPHOMORE, null),
    ECE300("ECE300", 4, Standing.JUNIOR, Major.ECE),
    ECE400("ECE400", 4, Standing.SENIOR, Major.ECE),
    CCD("CCD", 4, Standing.FRESHMAN, null),
    HST("HST", 3, Standing.FRESHMAN, null);

    private final String courseNum;
    private final int creditHours;
    private final Standing standing;
    private final Major major;


    /**
     * Constructor to create Course object
     * @param courseNum String containing the name of the course
     * @param creditHours int representing the credits earned from completion of the course
     * @param standing Object containing the standing (Freshman...Senior) prereq
     * @param major Object containing the major prerequisite for the course.
     */
    Course(String courseNum, int creditHours, Standing standing, Major major) {
        this.courseNum = courseNum;
        this.creditHours = creditHours;
        this.standing = standing;
        this.major = major;
    }

//    @Override
//    public boolean equals(Course course) {
//        if (this.courseNum.equals(course.getCourseNum())) {
//            return true;
//        }
//        return false;
//    }

    /**
     * Getter method for the courseNum
     * @return course number String
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * Getter method for the credit hours
     * @return creditHours int representing the number of credits hours
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Getter method for the Standing object
     * @return standing Object containing information about standing prereq
     */
    public Standing getStanding() {
        return standing;
    }

    /**
     * Getter method for the Major object
     * @return major Object containing info about major prereq
     */
    public Major getMajor() {
        return major;
    }


}