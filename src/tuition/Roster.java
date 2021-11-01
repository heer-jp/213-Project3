package tuition;

/**
 * Collects and manages Student objects in roster array.
 * Performs functions called by TuitionManager class. Functions include
 * adding and removing Student objects from the array and modifying Student objects.
 * @author Sai Maduri, Heer Patel
 */
public class Roster {
    private Student[] roster;
    private int size;

    public static final int NOT_FOUND = -1;
    public static final int ROSTER_INCREASE_SIZE = 4;
    public static final Date NO_PAYMENT_DATE = new Date("1/1/2021");

    /**
     * Creates roster object
     */
    public Roster() {
        this.roster = new Student[ROSTER_INCREASE_SIZE];
        this.size = 0;
    }

    /**
     * Finds if an student already exists in array roster.
     * @param student object being searched for.
     * @return index of where the album object is in the array.
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student))
                return i;
        }
        return NOT_FOUND;
    }

    /**
     *  Grows array length if array is full.
     */
    private void grow() {
        Student[] tmp = new Student[size + ROSTER_INCREASE_SIZE];
        for (int i = 0; i < size; i++) {
            tmp[i] = roster[i];
        }
        roster = tmp;
    }

    /**
     * Adds student object to albums array.
     * @param student object to be added
     * @return false if album is not added, true if it is added.
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND)
            return false;
        if (size == roster.length)
            grow();
        roster[size++] = student;
        return true;
    }

    /**
     * Removes student from array if it exists in the array.
     * @param student Student object to be deleted
     * @return false if student is not in array, true if it exists and is removed.
     */
    public boolean remove(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND)
            return false;
        for (int j = studentIndex; j < size - 1; j++) {
            roster[j] = roster[j + 1];
        }
        roster[size - 1] = null;
        size--;
        return true;
    }

    /**
     * Checks if student can receive financial aid by verifying if student is a resident.
     * @param student object of student being verified
     * @return index of student in roster if student can receive financial aid.
     * Returns null if not.
     */
    public Student canReceiveFinancialAid(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND)
            return null;
        if (roster[studentIndex] instanceof Resident)
            return roster[studentIndex];
        return null;
    }

    /**
     * Checks if a student can study abroad by verifying if student is international.
     * @param student object of student being verified
     * @return index of student in roster if student can study abroad, null if not
     */
    public Student canStudyAbroad(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND)
            return null;
        if (roster[studentIndex] instanceof International)
            return roster[studentIndex];
        return null;
    }

    /**
     * Gets the index value of student object in roster 
     * @param student object being retrieved
     * @return index of student in array
     */
    public Student getStudent(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND) {
            return null;
        }
        return roster[studentIndex];
    }

    /**
     * Gets the size of the roster.
     * @return size of array
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints all students in student roster
     */
    public String print() {
        StringBuilder print = new StringBuilder();
        if (size != 0) {
            print.append("* list of students in the roster **\n");
            for (Student student : roster)
                if (student != null)
                    print.append(student+"\n");
            print.append("* end of roster **");
        } else {
            print.append("Student roster is empty!");
        }
        return print.toString();
    }

    /**
     * Prints all students in student roster by student name
     */
    public String printByName() {
        for (int i = 0; i < roster.length - 1; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < roster.length; j++) {
                if (roster[j] != null && roster[swapIndex] != null)
                    if (roster[j].getProfile().getName().compareTo(roster[swapIndex].getProfile().getName()) < 0)
                        swapIndex = j;
            }
            Student tmpStudent = roster[swapIndex];
            roster[swapIndex] = roster[i];
            roster[i] = tmpStudent;
        }
        StringBuilder print = new StringBuilder();
        if (size != 0) {
            print.append("* list of students ordered by name **\n");
            for (Student student : roster)
                if (student != null)
                    print.append(student+"\n");
            print.append("* end of roster **");
        } else {
            print.append("Student roster is empty!");
        }
        return print.toString();
    }

    /**
     * Prints all students in student roster by payment date
     */
    public String printByPaymentDate() {
        int paymentCount = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPaymentDate() != null)
                paymentCount++;
        }
        for (int i = 0; i < roster.length - 1; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < roster.length; j++) {
                if (roster[j] != null && roster[swapIndex] != null) {
                    if (roster[swapIndex].getLastPaymentDate() == null && roster[j].getLastPaymentDate() == null) {
                        continue;
                    } else if (roster[swapIndex].getLastPaymentDate() == null) {
                        if (roster[j].getLastPaymentDate().compareTo(NO_PAYMENT_DATE) > 0)
                            swapIndex = j;
                    } else if (roster[j].getLastPaymentDate() == null) {
                        if (NO_PAYMENT_DATE.compareTo(roster[swapIndex].getLastPaymentDate()) > 0)
                            swapIndex = j;
                    } else if (roster[j].getLastPaymentDate().compareTo(roster[swapIndex].getLastPaymentDate()) > 0) {
                        swapIndex = j;
                    }
                }
            }
            Student tmpStudent = roster[swapIndex];
            roster[swapIndex] = roster[i];
            roster[i] = tmpStudent;
        }
        StringBuilder print = new StringBuilder();
        if (size != 0) {
            print.append("* list of students made payments ordered by payment date **\n");
            for (int i = 0; i < size; i++) {
                if (roster[i] != null && roster[i].getLastPaymentDate() != null)
                    print.append(roster[i]+"\n");
            }
            print.append("* end of roster **");
        } else {
            print.append("Student roster is empty!");
        }
        return print.toString();
        
    }

    /**
     * Calculates tuition of all students in student roster
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            if (!(roster[i].getIsTuitionCalculated())) {
                roster[i].tuitionDue();
            }
        }
    }

}
