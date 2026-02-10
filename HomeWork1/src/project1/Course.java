package project1;
/**
 * @author mss444
 * this is an enum class for the Courses *
 */

public enum Course {
    CS100("CS100", 4, "Freshman", "None"),
    CS200("CS200", 4, "Sophomore", "None"),
    CS300("CS300", 4, "Junior", "CS major only"),
    CS400("CS400", 4, "Junior", "CS major only"),
    CS442("CS442", 3, "Junior", "None"),
    PHY100("PHY100", 5, "Freshman", "None"),
    PHY200("PHY200", 5, "Sophomore", "None"),
    ECE300("ECE300", 4, "Junior", "ECE major only"),
    ECE400("ECE400", 4, "Senior", "ECE major only"),
    CCD("CCD", 4, "Freshman", "None"),
    HST("HST", 3, "Freshman", "None");

    private final String courseNum;
    private final int creditHours;
    private final String standing;
    private final String major;


    /**
     * @param courseNum
     * @param creditHours
     * @param standing
     * @param major
     */

    Course(String courseNum, int creditHours, String standing, String major) {
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
     * @return
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * Getter method
     *
     * @return
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Getter method
     *
     * @return
     */
    public String getStanding() {
        return standing;
    }

    /**
     * Getter method
     *
     * @return
     */
    public String getMajor() {
        return major;
    }


    public static void main(String[] args) {
        Course cs100 = Course.CS100;
        System.out.println(cs100.getCreditHours() + " " + cs100.getCourseNum() + " " + cs100.getMajor() + " " + cs100.getStanding());


    }
}