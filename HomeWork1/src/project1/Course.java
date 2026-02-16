package project1;
/**
 * @author mss444
 * this is an enum class for the Courses *
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
     * @param courseNum
     * @param creditHours
     * @param standing
     * @param major
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
     * Getter method
     *
     * @return course number
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * Getter method
     *
     * @return creditHours
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Getter method
     *
     * @return standing
     */
    public Standing getStanding() {
        return standing;
    }

    /**
     * Getter method
     *
     * @return major
     */
    public Major getMajor() {
        return major;
    }


    public static void main(String[] args) {
        Course cs100 = Course.CS100;
        System.out.println(cs100.getCreditHours() + " " + cs100.getCourseNum() + " " + cs100.getMajor() + " " + cs100.getStanding());


    }
}