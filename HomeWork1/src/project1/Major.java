package project1;

/** This class represents the available majors and there respective Schools
 * @author tjt97*/

public enum Major {
    CS("CS", "School of Arts & Sciences"),
    ECE("ECE", "School of Engineering"),
    MATH("MATH", "School of Arts & Sciences"),
    ITI("ITI", "School of Communication and Information"),
    BAIT("BAIT", "Rutgers Business School");

    private final String school;
    private final String major;

    /** Creates the Major object with its associated school
     * @param school, the school offering the major*/
    Major(String major, String school){
        this.major = major;
        this.school = school;

    }

    /** Returns the schoool associated with the given major
     * @return school name*/
    public String getSchool(){
        return school;
    }

    public String getMajor() {
        return major;
    }

    /** Return the major and the school as a String
     * @return major description*/
    @Override
    public String toString(){
        return name()+ "," + school;
    }

}
