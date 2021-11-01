package tuition;

import java.text.DecimalFormat;

/**
 *  A class that stores information about a student from the tri-state area.
 *  This information includes a name, major, credits, and state.
 *  @author Sai Maduri, Heer Patel
 */
public class TriState extends NonResident {

    private String state;

    public static final int NY_TRISTATE_DISCOUNT = 4000;
    public static final int CT_TRISTATE_DISCOUNT = 5000;

    /**
     * Creates TriState student object type
     * @param name The student's name
     * @param major The student's major
     * @param credits The student's total credit hours
     * @param state The tri-state location the student is from
     */
    public TriState(String name, String major, int credits, String state) {
        super(name, major, credits);
        this.state = state;
    }

    /**
     * Calculates the tuition due for a tri-state student.
     */
    @Override
    public void tuitionDue() {
        // TODO Auto-generated method stub
        int credits = getCredits();
        int enrollmentTypeCost = 0;
        double universityFee = 0;
        int tristateDiscount = 0;
        int additionalCreditsCost = credits > CREDIT_EXCEED_LIMIT
                ? (credits - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                : 0;

        if (credits >= MIN_FULLTIME_CREDITS) {
            enrollmentTypeCost = FULLTIME_TUITION_COST;
            universityFee = UNIVERSITY_FEE;
            if (state.equals("CT")) {
                tristateDiscount = CT_TRISTATE_DISCOUNT;
            } else {
                tristateDiscount = NY_TRISTATE_DISCOUNT;
            }
        } else {
            enrollmentTypeCost = credits * PARTTIME_CREDIT_HOUR_COST;
            universityFee = UNIVERSITY_FEE * PARTTIME_FEE_RATE;
        }

        setTuition(enrollmentTypeCost + universityFee + additionalCreditsCost - tristateDiscount);
    }

    /**
     * Converts tri-state student information into a string printout.
     * @return tri-state student information
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "(tri-state):" + state;
    }

}
