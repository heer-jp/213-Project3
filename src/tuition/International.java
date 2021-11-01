package tuition;

import java.text.DecimalFormat;

/**
 *  A class that stores information about an international student.
 *  This information includes a name, major, credits, and study abroad status.
 *  @author Sai Maduri, Heer Patel
 */
public class International extends NonResident {

    private boolean studyingAbroad;

    public static final int ADDITIONAL_FEE = 2650;

    /**
     * Creates International student object type
     * @param name The student's name
     * @param major The student's major
     * @param credits The student's total credit hours
     * @param studyingAbroad The student's studying abroad status
     */
    public International(String name, String major, int credits, boolean studyingAbroad) {
        super(name, major, credits);
        this.studyingAbroad = studyingAbroad;
        if (studyingAbroad && credits > MIN_FULLTIME_CREDITS)
            setCredits(12);
    }

    /**
     * Calculates the tuition due for an international student.
     */
    @Override
    public void tuitionDue() {
        // TODO Auto-generated method stub
        if (!studyingAbroad && getCredits() < MIN_FULLTIME_CREDITS) {
            setTuition(0);
        } else {
            int enrollmentTypeCost = studyingAbroad ? 0 : FULLTIME_TUITION_COST;
            int additionalCreditsCost = getCredits() > CREDIT_EXCEED_LIMIT
                    ? (getCredits() - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                    : 0;
            setTuition(enrollmentTypeCost + UNIVERSITY_FEE + ADDITIONAL_FEE + additionalCreditsCost);
        }
    }

    /**
     * Set international student's study abroad status.
     * @param studyingAbroad boolean given by user
     */
    public void setStudyAbroad(boolean studyingAbroad) {
        if (this.studyingAbroad == studyingAbroad)
            return;
        this.studyingAbroad = studyingAbroad;
        if (studyingAbroad && getCredits() > MIN_FULLTIME_CREDITS)
            setCredits(MIN_FULLTIME_CREDITS);
        tuitionDue();
        setLastPaymentDate(null);
        setLastPaymentAmount(-getLastPaymentAmount());
    }

    /**
     * Converts international student information into a string printout.
     * @return international student information
     */
    @Override
    public String toString() {
        if (studyingAbroad) {
            return super.toString() + ":international:study abroad";
        }
        return super.toString() + ":international";
    }
}
