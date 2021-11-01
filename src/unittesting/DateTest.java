package unittesting;

import static org.junit.Assert.*;
import org.junit.Test;
import tuition.Date;

public class DateTest {

	//Test incorrect date format (dd/mm/yyyy)
    @Test
    public void testIsValidInvalidDate() {
        Date date = new Date("31/1/2021");
        assertFalse(date.isValid());
    }

    //Test correct date format (mm/dd/yyyy)
    @Test
    public void testIsValidValidDate() {
        Date date = new Date("1/31/2021");
        assertTrue(date.isValid());
    }

    //Test invalid month values (<1 or >12)
    @Test
    public void testIsValidInvalidMonth() {
        Date date = new Date("13/1/2021");
        assertFalse(date.isValid());
        date = new Date("0/1/2021");
        assertFalse(date.isValid());
    }

    //Test valid month value (>=1 or <=12)
    @Test
    public void testIsValidValidMonth() {
        Date date = new Date("9/1/2021");
        assertTrue(date.isValid());
    }

    //Test invalid leapyear date
    @Test
    public void testIsValidInvalidLeapyear() {
        Date date = new Date("2/29/2021");
        assertFalse(date.isValid());
    }

    //Test invalid year
    @Test
    public void testIsValidInvalidYear() {
        Date date = new Date("8/27/2020");
        assertFalse(date.isValid());
    }

    //Test valid year
    @Test
    public void testIsValidValidYear() {
        Date date = new Date("8/27/2021");
        assertTrue(date.isValid());
    }

    //test invalid date value (>31)
    @Test
    public void testIsValidInvalidDay() {
        Date date = new Date("9/32/2021");
        assertFalse(date.isValid());
    }

    //Test valid date value
    @Test
    public void testIsValidValidDay() {
        Date date = new Date("9/30/2021");
        assertTrue(date.isValid());
    }

    //Test invalid month for 31 date month
    @Test
    public void testIsValidInvalid31stDay() {
        Date date = new Date("9/31/2021");
        assertFalse(date.isValid());
    }

    //test valid month for 31 date month
    @Test
    public void testIsValidValid31stDay() {
        Date date = new Date("5/31/2021");
        assertTrue(date.isValid());
    }
}