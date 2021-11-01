package tuition;

import java.text.DecimalFormat;

/**
 *  A class that stores information about a resident student.
 *  This information includes a name, major, credits, financialAid.
 *  @author Sai Maduri, Heer Patel
 */
public class Resident extends Student {
    private boolean hasFinancialAid;
    private double financialAidAmount;

    public static final int PARTTIME_CREDIT_HOUR_COST = 404;
    public static final int FULLTIME_TUITION_COST = 12536;

    public static final int MAX_FINANCIAL_AID_AMOUNT = 10000;

    /**
     * Creates Resident student object type
     * @param name The student's name
     * @param major The student's major
     * @param credits The student's total credit hours
     */
    public Resident(String name, String major, int credits) {
        super(name, major, credits);
    }

    /**
     * Calculates the tuition due for an resident student.
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

        setTuition(enrollmentTypeCost + universityFee + additionalCreditsCost - getLastPaymentAmount() - financialAidAmount);

    }

    /**
     * Gives a resident financial aid if they have not previously received it.
     * @param financialAid amount to be provided
     * @return true if student receives the financial aid, false if not
     */
    public boolean giveFinancialAid(double financialAid) {
        if (hasFinancialAid)
            return false;
        setTuition(getTuition() - financialAid);
        hasFinancialAid = true;
        financialAidAmount = financialAid;
        return true;
    }

    /**
     * Returns a student's financial aid status.
     * @return true if student has already received financial aid, false if not
     */
    public boolean getFinancialAidStatus() {
        return hasFinancialAid;
    }

    /**
     * Converts non-resident student information into a string printout.
     * @return non-resident student information
     */
    @Override
    public String toString() {
        if (hasFinancialAid) {
            return super.toString() + ":resident:financial aid $"
                    + new DecimalFormat("#,##0.00").format(financialAidAmount);
        }
        return super.toString() + ":resident";
    }
}
