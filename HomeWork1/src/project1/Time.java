package project1;

/**
 * This enum class creates the Time based on period
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
     * updated Tostring*/
    @Override
    public String toString(){
        return getHour() + ":"+ getMinute();
    }

    /**
     *
     * @return the identifying index for the period
     */
    public int getPeriodNum() {
        return periodNum;
    }

    public String getHour() { return hour;}

    public String getMinute() { return minute;}

    public String getMeridiem() {return meridiem;}

    public String getTime() {
        return getHour() + ":" + getMinute();
    }


    public static void main(String[] args) {
        Time period4 = Time.PERIOD4;
        System.out.println("Period " + period4.getPeriodNum() +" happens at " + period4.getHour() + ":" + period4.getMinute() + " " + period4.getMeridiem());
    }
}