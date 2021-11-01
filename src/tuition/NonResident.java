package tuition;

import java.text.DecimalFormat;

/**
 *  A class that stores information about a non-resident student.
 *  This information includes a name, major, and credits.
 *  @author Sai Maduri, Heer Patel
 */
public class NonResident extends Student {

    public static final int PARTTIME_CREDIT_HOUR_COST = 966;
    public static final int FULLTIME_TUITION_COST = 29737;

    /**
     * Creates NonResident student object type
     * @param name The student's name
     * @param major The student's major
     * @param credits The student's total credit hours
     */
    public NonResident(String name, String major, int credits) {
        super(name, major, credits);
    }

    /**
     * Calculates the tuition due for a non-resident student.
     */
    @Override
    public void tuitionDue() {
        // TODO Auto-generated method stub
        int credits = getCredits();
        int enrollmentTypeCost = 0;
        double universityFee = 0;
        int additionalCreditsCost = credits > CREDIT_EXCEED_LIMIT
                ? (credits - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                : 0;

        if (credits >= MIN_FULLTIME_CREDITS) {
            enrollmentTypeCost = FULLTIME_TUITION_COST;
            universityFee = UNIVERSITY_FEE;
        } else {
            enrollmentTypeCost = credits * PARTTIME_CREDIT_HOUR_COST;
            universityFee = UNIVERSITY_FEE * PARTTIME_FEE_RATE;
        }

        setTuition(enrollmentTypeCost + universityFee + additionalCreditsCost);
    }

    /**
     * Converts non-resident student information into a string printout.
     * @return non-resident student information
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + ":non-resident";

    }
}
