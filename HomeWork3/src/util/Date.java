package util;
import java.util.Calendar;

/**creates the date variables, with month, day, year
 * includes methods to also check date
 * @author tjt97*/
public class Date implements Comparable<Date> {
    private static final int MIN_YEAR = 1900;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int FEBRUARY_INDEX = 1;

    private int year;
    private int month;
    private int day;

    /** Tells us if the year is leap year and we need to adjust days
     * @return true if leapyear false otherwise */
    private boolean isLeapYear(){
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    /** Use Calendar to check today's Date
     * create getter method so the software works
     * @return calendar format of today's date*/
    public Calendar toCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month -1, day);
        return calendar;
    }
    /** Returns the year of this date
     * @return the year */
    public int getYear(){
        return year;
    }
    /** Returns the month of this date
     * @return the month */
    public int getMonth(){
        return month;
    }
    /** Returns the day of this date
     * @return the day */
    public int getDay(){
        return day;
    }


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
        if(year < MIN_YEAR) return false;
        if(month < MIN_MONTH || month> MAX_MONTH) return false;
        int [] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

        if (isLeapYear()) {
            daysInMonth[FEBRUARY_INDEX] = 29;
        }
        if (day < 1 || day > daysInMonth[month - 1]) return false;

        return true;

    }
    /** Override compareTo method for Date class
     * @param  date object to compare
     * @return an int based on if the current date is earlier later or the same as the current date
     * ints will be positive, negative, or 0 */
    @Override
    public int compareTo(Date date){
        if(year != date.year){
            return(year- date.year);
        }
        if(month != date.month){
            return(month - date.month);
        }
        return (day - date.day);

    }
    /** Determine wherther the dates are equal
     * @param obj we are comparing the object
     * @return True if dates are equal False if not*/
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj instanceof Date) {
            Date other = (Date) obj;
            return year == other.year
                    && month == other.month
                    && day == other.day;

        }
        return false;

    }


    /** toString method convert the numeric date into a string ready to be output
     * @return the month/day/year as a string */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

}

