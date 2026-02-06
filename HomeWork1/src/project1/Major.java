package project1;

/** This class represents the available majors and there respective Schools
 * @author tjt97*/

public enum Major {
    CS("School of Arts & Sciences"),
    ECE("School of Engineering"),
    MATH("School of Arts & Sciences"),
    ITI("School of Communication and Information"),
    BAIT("Rutgers Buisness School");

    private final String school;

    /** Creates the Major object with its associated school
     * @param school, the school offering the major*/
    Major(String school){
        this.school = school;

    }

    /** Returns the schoool associated with the given major
     * @return school name*/
    public String getSchool(){
        return school;
    }

    /** Return the major and the school as a String
     * @return major description*/
    @Override
    public String toString(){
        return name() + " (" + school + ")";
    }

}
