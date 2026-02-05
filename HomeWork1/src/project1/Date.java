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
    private static final int FEBRUARY_INDEX = 1;

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
    /** Tells us if the year is leap year and we need to adjust days
     * @return true if leapyear false otherwise */
    private boolean isLeapYear(){
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
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
    /** Determine wherther the dates are equal
     * @param obj we are comparing the object
     * @return True if dates are equal False if not*/
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Date)) {
            return false;
        }

        Date other = (Date) obj;

        return year == other.year
                && month == other.month
                && day == other.day;
    }


    /** toString method convert the numeric date into a string ready to be output
     * @return the month/day/year as a string */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Testbed main method for Date.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Date d1 = new Date(2, 29, 2024);
        Date d2 = new Date(2, 29, 2023);

        System.out.println(d1.isValid()); // true
        System.out.println(d2.isValid()); // false
    }


}

