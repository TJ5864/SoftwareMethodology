package project1;

/**
 * @author mss444
 * This enum class creates the Time thingimajigy
 */

public enum Time {

    PERIOD1 (1, 8, 30),
    PERIOD2 (2, 10, 20),
    PERIOD3 (3, 12, 10),
    PERIOD4 (4, 2, 00),
    PERIOD5 (5, 3, 50),
    PERIOD6 (6, 5, 40);

    private final int periodNum;
    private final int hour;
    private final int minute;


    /**
     * constructor to create the time object
     * @param periodNum serves as an index to access an period within a day's schedule
     * @param hour serves to represent the hour that a period begins
     * @param minute
     */

    Time(periodNum, hour, minute) {
        this.periodNum = periodNum
        this.hour = hour;
        this.minute = minute;
    }


    public int periodNum() {
        return periodNum
    }



    public static void main(String[] args) {
        Time period1 = Time.PERIOD1;
        System.out.println(period1.periodNum());
    }
}