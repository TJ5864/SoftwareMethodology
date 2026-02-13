package project1;

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

        for (int i = 0; i < availabiltiy.length; i++) {
            availability = true;
        }
    }

    /**
     * retrieves the classroom numberr
     * @return classroom number
     */
    public String getClassroomNum() {
        return classroomNum;
    }

    /**
     * retrieves the building that a class can occur in
     * @return building name
     */
    public String getBuilding() {
        return building;
    }

    /**
     * retrieves the campus that the classroom object is
     * @return campus
     */
    public String getCampus() {
        return campus;
    }

    public boolean checkAvailability(int period) {
        return availability[period];
    }

    public void fillAvailability(int period) {
        if (checkAvailability(period)){
            availability[period] = false;
            return;
        }
        return;

    }


    public static void main(String[] args) {
        Classroom til232 = Classroom.TIL232;
        System.out.println(til232.getClassroomNum() + til232.getCampus() + til232.getBuilding());
    }
}