package project2;

/**
 * This enum class creates the Time object, tracking the starting time for each 80 min time slot (military time) and a
 * representing num for the timeslot
 * @author mss444
 */
public enum Time {

    PERIOD1 (1, "8", "30", "AM"),
    PERIOD2 (2, "10", "20", "AM"),
    PERIOD3 (3, "12", "10", "PM"),
    PERIOD4 (4, "14", "00", "PM"),
    PERIOD5 (5, "15", "50", "PM"),
    PERIOD6 (6, "17", "40", "PM");

    private final int periodNum;
    private final String hour;
    private final String minute;
    private final String meridiem;


    /**
     * constructor to create the time object
     * @param periodNum serves as an index to access an period within a day's schedule
     * @param hour serves to represent the hour that a period begins
     * @param minute represents the minutes
     * @author mss444
     */
    Time(int periodNum, String hour, String minute, String meridiem) {
        this.periodNum = periodNum;
        this.hour = hour;
        this.minute = minute;
        this.meridiem = meridiem;
    }

    /** Prints the number related to the time
     * updated toString*/
    @Override
    public String toString(){
        return getHour() + ":"+ getMinute();
    }

    /**
     * Getter method for retrieving the int represenation of 80 min time slot
     * @return periodNum the identifying index for the period
     */
    public int getPeriodNum() {
        return periodNum;
    }

    /**
     * Getter method for the hour of the starting time
     * @return hour String of hour
     */
    public String getHour() { return hour;}

    /**
     * getter method for the minute of the starting time
     * @return minute String of minute
     */
    public String getMinute() { return minute;}

    /**
     * Getter method for AM or PM (not useful anymore because the time was changed to military time
     * @return meridiem String containing AM or PM
     */
    public String getMeridiem() {return meridiem;}

    /**
     * Method that returns String of the starting time of the period
     * @return String containing starting time of period
     */
    public String getTime() {
        return getHour() + ":" + getMinute();
    }


}