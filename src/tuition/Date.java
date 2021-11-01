package tuition;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that stores a date based off of an input string in mm/dd/yyyy format.
 * The month, day, and year is stored in their respective variables.
 * @author Sai Maduri, Heer Patel
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARY_LEAP_YEAR_MONTH_END = 29;
    public static final int MONTH_START = 1;
    public static final int MONTH_END = 31;
    public static final int CURRENT_YEAR = 2021;

    /**
     * Creates Date object using user input date.
     * @param date entered by user.
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * Checks for invalid date by comparing user input date to current date. 
     * Invalid if user input date passes current date.
     */
    @Override
    public int compareTo(Date date) {
        // TODO Auto-generated method stub
        if (date.year < this.year) {
            return -1;
        } else {
            if (date.year == this.year) {
                if (date.month < this.month) {
                    return -1;
                } else {
                    if (date.month == this.month) {
                        if (date.day < this.day) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                }
            } else {
                return 1;
            }
        }
    }

    /**
     * Checks if date is valid. 
     * For leap year dates calls isLeapYear method to validate the date.
     * @return false if date is invalid, true if date is valid
     */
    public boolean isValid() {
        Calendar paymentDate = Calendar.getInstance();
        paymentDate.set(year, month - 1, day);
        Calendar currentDate = Calendar.getInstance();
        if (paymentDate.after(currentDate) || year < CURRENT_YEAR || day > MONTH_END || day < MONTH_START)
            return false;
        switch (month - 1) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.OCTOBER:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.DECEMBER:
                return day <= MONTH_END;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return day != MONTH_END;
            case Calendar.FEBRUARY:
                if (day > FEBRUARY_LEAP_YEAR_MONTH_END) {
                    return false;
                } else if (isLeapYear()) {
                    return day <= FEBRUARY_LEAP_YEAR_MONTH_END;
                } else {
                    return day < FEBRUARY_LEAP_YEAR_MONTH_END;
                }
            default:
                return false;
        }
    }

    /**
     * Verifies if the user date is a valid leap year date.
     * @return true if date is valid a leap year date, false if date is an invalid
     *         leap year date.
     */
    public boolean isLeapYear() {
        if (year % QUATERCENTENNIAL == 0) {
            return false;
        } else if (year % QUADRENNIAL == 0 || year % CENTENNIAL == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * String formed by combining month, day, and year into date format.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return month + "/" + day + "/" + year;
    }
}