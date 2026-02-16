package project1;

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


