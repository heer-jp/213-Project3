package unittesting;

import static org.junit.Assert.*;
import org.junit.Test;
import tuition.*;

public class RosterTest {

    @Test
    public void add() {
        Roster roster = new Roster();
        // Test adding a student to roster
        Student student = new Student("Jane Doe", "CS", 11);
        assertTrue(roster.add(student));
        // Test adding an international student to roster
        student = new International("Sam Welsh", "It", 22, false);
        assertTrue(roster.add(student));
        // Test adding a resident student to roster
        student = new Resident("Jo Joe", "ee", 18);
        assertTrue(roster.add(student));
        // Test adding a non-resident student to roster
        student = new NonResident("Jay Park", "mE", 24);
        assertTrue(roster.add(student));
        // Test adding a tri-state student to roster
        student = new TriState("Lia Doe", "Ba", 5, "NY");
        assertTrue(roster.add(student));
        // Test adding an existing student to roster
        assertFalse(roster.add(student));
    }

    @Test
    public void remove() {
        Roster roster = new Roster();
        Student studentOne = new Student("Jane Doe", "CS", 11);
        Student studentTwo = new International("Sam Welsh", "It", 22, false);
        Student studentThree = new Resident("Jo Joe", "ee", 18);
        Student studentFour = new NonResident("Jay Park", "mE", 24);
        Student studentFive = new TriState("Lia Doe", "Ba", 5, "NY");
        roster.add(studentOne);
        roster.add(studentTwo);
        roster.add(studentThree);
        roster.add(studentFour);
        roster.add(studentFive);

        // Test removing a student, resident, non-resident, tri-state, and international
        assertTrue(roster.remove(studentOne));
        assertTrue(roster.remove(studentTwo));
        assertTrue(roster.remove(studentThree));
        assertTrue(roster.remove(studentFour));
        assertTrue(roster.remove(studentFive));

        // Test removing a student who has already been removed
        assertFalse(roster.remove(studentOne));
        assertFalse(roster.remove(studentFive));

        // Test removing a student who was never added to the roster
        Student studentSix = new Student("John Doe", "CS", 11);
        assertFalse(roster.remove(studentSix));
    }
}
