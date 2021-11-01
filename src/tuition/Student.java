package tuition;

import java.text.DecimalFormat;

/**
 *  A class that stores information about a student.
 *  This information includes a name, major, and credits.
 *  @author Sai Maduri, Heer Patel
 */
public class Student {
    private Profile profile;
    private int credits;
    private Date lastPaymentDate;
    private double lastPaymentAmount;
    private double tuition;
    private boolean isTuitionCalculated;

    public static final int MIN_CREDITS = 3;
    public static final int MIN_FULLTIME_CREDITS = 12;
    public static final int MAX_CREDITS = 24;
    public static final int CREDIT_EXCEED_LIMIT = 16;
    public static final int UNIVERSITY_FEE = 3268;
    public static final double PARTTIME_FEE_RATE = 0.8;

    /**
     * Creates Student student object type.
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     * @param credits int given by user - student's credit
     */
    public Student(String name, String major, int credits) {
        this.profile = new Profile(name, major);
        this.credits = credits;
    }

    /**
     * Creates Student student object type with two variables.
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     */
    public Student(String name, String major) {
        this.profile = new Profile(name, major);
        this.credits = 0;
    }

    /**
     * Calculates the tuition due for a student.
     */
    public void tuitionDue() {
    }

    /**
     * Profile object helps uniquely identify students in roster.
     * @return profile object
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Returns credits of student object
     * @return student's credit int
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets current student object's credit value to credits.
     * @param credits int value.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Returns date of most recent tuition payment
     * @return Date value
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Sets the lastPaymentDate to lastPaymenyDate
     * @param lastPaymentDate date value
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Returns the last tuition payment amount made.
     * @return double value
     */
    public double getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    /**
     * Sets the LastPaymentAmount to paymentAmount 
     * Adds last payment amount to previous payment amount.
     * @param paymentAmount double value
     */
    public void setLastPaymentAmount(double paymentAmount) {
        lastPaymentAmount += paymentAmount;
    }

    /**
     * Return tuition type of student object
     * @return double value of tution
     */
    public double getTuition() {
        return tuition;
    }

    /**
     * Sets the setTuition to tuition 
     * @param tuition double value tuition fee
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
        setIsTuitionCalculated(true);
    }

    /**
     * Checks if the tuition has been calculated or not
     * @return true if tuition has been calculated, false if not
     */
    public boolean getIsTuitionCalculated() {
        return isTuitionCalculated;
    }

    /**
     * Sets isTuitionCalculated to isTuitionCalculated
     * @param isTuitionCalculated local value
     */
    public void setIsTuitionCalculated(boolean isTuitionCalculated) {
        this.isTuitionCalculated = isTuitionCalculated;
    }

    /**
     * Validates if the tutiton payment and date paid are valid.
     * @param amount payment amount given by user
     * @param date payment date given by user
     * @return true if the payment is less than the tuition and has a valid date,
     * false otherwise
     */
    public boolean makePayment(double amount, Date date) {
        if (amount > tuition)
            return false;
        tuition -= amount;
        lastPaymentDate = date;
        lastPaymentAmount += amount;
        return true;
    }

    /**
     * Converts student information into a string printout.
     * @return student information
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.lastPaymentDate == null) {
            return profile.toString() + ":" + credits + " credit hours:tuition due:"
                    + new DecimalFormat("#,##0.00").format(tuition) + ":total payment:"
                    + new DecimalFormat("#,##0.00").format(lastPaymentAmount) + ":last payment date: " + "--/--/--";
        }
        return profile.toString() + ":" + credits + " credit hours:tuition due:"
                + new DecimalFormat("#,##0.00").format(tuition) + ":total payment:"
                + new DecimalFormat("#,##0.00").format(lastPaymentAmount) + ":last payment date: "
                + lastPaymentDate.toString();
    }

    /**
     * Compares student objects by profile.
     * @return returns true if the profile objects are equal, false otherwise 
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Student))
            return false;
        Student tmp = (Student) obj;
        return getProfile().equals(tmp.getProfile());
    }

}
