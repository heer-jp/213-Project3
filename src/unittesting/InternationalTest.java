package unittesting;

import static org.junit.Assert.*;
import org.junit.Test;
import tuition.International;

public class InternationalTest {

    @Test
    public void tuitionDueNotStudyingAbroad() {
        // Test international student not studying abroad with 12 credits
        International international = new International("Sam Walsh", "IT", 12, false);
        international.tuitionDue();
        assertEquals(35655.0, international.getTuition(), 0);

        // Test international student not studying abroad with over 12 credits
        international = new International("Jane Doe", "CS", 24, false);
        international.tuitionDue();
        assertEquals(43383.0, international.getTuition(), 0);
    }

    @Test
    public void tuitionDueStudyingAbroad() {
        // Test international student studying abroad with 12 credits
        International international = new International("Sam Walsh", "IT", 12, true);
        international.tuitionDue();
        assertEquals(5918.0, international.getTuition(), 0);

        // Test international student studying abroad with 24 credits
        international = new International("Jane Doe", "CS", 24, true);
        international.tuitionDue();
        assertEquals(5918.0, international.getTuition(), 0);
    }

    @Test
    public void tuitionDueInvalidCredits() {
        // Test international student studying abroad with 12 credits
        International international = new International("Sam Walsh", "IT", 4, true);
        international.tuitionDue();
        assertEquals(5918.0, international.getTuition(), 0);

        international = new International("Jane Doe", "CS", 11, false);
        international.tuitionDue();
        assertEquals(0.0, international.getTuition(), 0);
    }
}
