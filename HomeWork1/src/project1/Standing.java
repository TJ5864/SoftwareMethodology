package project1;
/**Standing class to easily compare Grade level for prereqs
 * @author tjt97
 * @author mss444*/

public enum Standing {
    FRESHMAN("Freshman"),
    SOPHOMORE("Sophomore"),
    JUNIOR("Junior"),
    SENIOR("Senior");


    private final String standing;

    Standing(String standing) {
        this.standing = standing;
    }

    public String getStanding() {
        return standing;
    }
}


