package project1;

/**
 * This is an enum class that contains the information of classroom, including the name, building, campus,
 * and availability.
 * @author mss444
 */
public enum Classroom {
    HIL114 ("HIL114", "Hill Center", "Busch"),
    ARC103 ("ARC103", "Allison Road Classroom", "Busch"),
    BEAUD ("BEAUD", "Beck Hall", "Livingston"),
    TIL232 ("TIL232", "Tillett Hall", "Livingston"),
    AB2225 ("AB2225", "Academic Building", "College Avenue"),
    MU302 ("MU302", "Murray Hall", "College Avenue");

    private String classroomNum;
    private String building;
    private String campus;
    private boolean[] availability;

    /**
     * global constant TOTALPERIODS offset by 1 to allign index number with period i.e. period 1 = index 1.
     */
    private final int TOTALPERIODS = 7;

    /**
     * Creates Classroom object with classroom number, building name, and campus
     * @param classroomNum tells us the classroom number
     * @param building gives the building name
     * @param campus contains the name of the campus
     */
    Classroom(String classroomNum, String building, String campus) {
        this.classroomNum = classroomNum;
        this.building = building;
        this.campus = campus;
        this.availability = new boolean[TOTALPERIODS];

        for (int i = 0; i < availability.length; i++) {
            availability[i] = true;
        }
    }

    /**
     * getter method that retrieves the classroom number
     * @return classroom number
     */
    public String getClassroomNum() {
        return classroomNum;
    }

    /**
     * getter method that retrieves the building of the classroom
     * @return building String of building name that the classroom was located in.
     */
    public String getBuilding() {
        return building;
    }

    /**
     * retrieves the campus that the classroom is located on
     * @return campus String containing name of the campus location
     */
    public String getCampus() {
        return campus;
    }

    /**
     * Method to peek into the classroom's availability at a given period.
     * @param period int representation of a 80 min portion of the daily schedule.
     * @return true if available; false if not
     */
    public boolean checkAvailability(int period) {
        return availability[period];
    }

    /**
     * Method to mark a classroom unavailable if registered for a section at a specific period.
     * @param period int representaton of a 80 min portion of the daily schedule
     */
    public void fillAvailability(int period) {
        if (checkAvailability(period)){
            availability[period] = false;
            return;
        }
        return;

    }

    /**
     * Method to mark a classroom available for a period if a section is dropped for that period
     * @param period int representaton of a 80 min portion of the daily schedule
     */
    public void freeAvailability(int period) {
        if (!checkAvailability(period)) {
            availability[period] = true;
            return;
        }
        return;
    }


    /**
     * main method for testing
     * @param args
     */
    public static void main(String[] args) {
        Classroom til232 = Classroom.TIL232;
        System.out.println(til232.getClassroomNum() + til232.getCampus() + til232.getBuilding());
    }
}