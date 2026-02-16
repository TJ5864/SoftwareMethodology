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

    /**
     * Constructor creating Standing object containing the name of the standing
     * @param standing String of standing (Freshman... Senior)
     */
    Standing(String standing) {
        this.standing = standing;
    }

    /**
     * Getter method that returns the standing
     * @return standing String containing the name of the standing
     */
    public String getStanding() {
        return standing;
    }
}


