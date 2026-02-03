package project1;
/**
 * @author tjt97
 * creates the date variables
 *
 *            */


public class Date {
    private static final int MIN_YEAR = 1900;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int FEBRUARY = 2;

    private int year;
    private int month;
    private int day;

/**
 * create the date object with month day and year
 * @param year tells us the year
 * @param month tells us the month
 * @param day tells us the day*/
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year=year;

    }
    /** Method tells us weather the date is valis
     * @return true if the date is valid false if not*/
    public boolean isValid(){
        if(year < 0) return false;
        if(month < 1 || month > 12) return false;
        int [] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

        if (isLeapYear()) {
            daysInMonth[1] = 29;
        }
        if (day < 1 || day > daysInMonth[month - 1]) return false;

        return true;

    }
    /** Tells us if the year is leap year and we need to adjust days
     * @return true if leapyear false otherwise */
    private boolean isLeapYear(){
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    /** toString method convert the numeric date into a string ready to be output
     * @return the month/day/year as a string */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}
