package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * A class that handles input and output for the entire Tuition Manager GUI.
 * Takes in input through TextFields and Button groups to display the appropriate data on the output panel. 
 * @author Sai Maduri, Heer Patel
 *
 */
public class Controller {

    private Roster roster = new Roster();
    private String majorModifyRoster = "", majorMakePayment = "", majorCalculateTuition = "";

    @FXML
    private TextField studentNameTextFieldModifyRoster;

    @FXML
    private ToggleGroup studentMajor;

    @FXML
    private RadioButton baMajorModifyRoster;

    @FXML
    private RadioButton csMajorModifyRoster;

    @FXML
    private RadioButton eeMajorModifyRoster;

    @FXML
    private RadioButton itMajorModifyRoster;

    @FXML
    private RadioButton meMajorModifyRoster;

    @FXML
    private ToggleGroup residency;

    @FXML
    private RadioButton residentResidency;

    @FXML
    private RadioButton nonresidentResidency;

    @FXML
    private ToggleGroup tristate;

    @FXML
    private RadioButton tristateResidency;

    @FXML
    private RadioButton nyResidency;

    @FXML
    private RadioButton ctResidency;

    @FXML
    private RadioButton internationalResidency;

    @FXML
    private RadioButton studyAbroadResidency;

    @FXML
    private TextField creditHoursTextField;

    @FXML
    private Button addStudent;

    @FXML
    private Button deleteStudent;

    @FXML
    private Button setStudyAbroad;

    @FXML
    private Button displayRosterButton;

    @FXML
    private Button displayRosterByNameButton;

    @FXML
    private Button displayRosterByPaymentDateButton;

    @FXML
    private TextArea outputTextArea;
    
    @FXML
    private TextField studentNameTextFieldMakePayment;

    @FXML
    private ToggleGroup studentMajorMakePayment;

    @FXML
    private RadioButton baMajorMakePayment;

    @FXML
    private RadioButton csMajorMakePayment;

    @FXML
    private RadioButton eeMajorMakePayment;

    @FXML
    private RadioButton itMajorMakePayment;

    @FXML
    private RadioButton meMajorMakePayment;
    
    @FXML 
    private TextField paymentAmountTextField;
    
    @FXML
    private DatePicker paymentDate;
    
    @FXML 
    private Button giveFinancialAid;
    
    @FXML 
    private Button makePayment;
    
    @FXML
    private TextField studentNameTextFieldCalculateTuition;
    
    @FXML
    private ToggleGroup studentMajorCalculateTuition;

    @FXML
    private RadioButton baMajorCalculateTuition;

    @FXML
    private RadioButton csMajorCalculateTuition;

    @FXML
    private RadioButton eeMajorCalculateTuition;

    @FXML
    private RadioButton itMajorCalculateTuition;

    @FXML
    private RadioButton meMajorCalculateTuition;
    
    @FXML
    private Button calculateStudent;
    
    @FXML 
    private Button calculateRoster;

    /**
     * Enables states to be selected for tristate residents, and disables non-tristate residency options (e.g. study abroad).
     */
    @FXML
    private void tristateSelected() {
        nyResidency.setDisable(false);
        ctResidency.setDisable(false);
        studyAbroadResidency.setDisable(true);
        studyAbroadResidency.setSelected(false);
    }

    /**
     * Disables non-resident residency options (e.g. study abroad, tristate)
     */
    @FXML
    private void residentSelected() {
        studyAbroadResidency.setDisable(true);
        studyAbroadResidency.setSelected(false);
        nyResidency.setDisable(true);
        nyResidency.setSelected(false);
        ctResidency.setDisable(true);
        ctResidency.setSelected(false);
    }

    /**
     * Disables non-nonresident residency options (e.g. study abroad, tristate)
     */
    @FXML
    private void nonresidentSelected() {
        tristateResidency.setDisable(false);
        internationalResidency.setDisable(false);
        studyAbroadResidency.setDisable(true);
        studyAbroadResidency.setSelected(false);
        nyResidency.setDisable(true);
        nyResidency.setSelected(false);
        ctResidency.setDisable(true);
        ctResidency.setSelected(false);
    }

    /**
     * Disables non-international residency options (e.g. tristate)
     */
    @FXML
    private void internationalSelected() {
        studyAbroadResidency.setDisable(false);
        nyResidency.setDisable(true);
        nyResidency.setSelected(false);
        ctResidency.setDisable(true);
        ctResidency.setSelected(false);
    }

    /**
     * Validates if the current user input can successfully create a student.
     * @return true if the input is valid, false if not.
     */
    private boolean checkValidAddStudentInput() {
        if (studentNameTextFieldModifyRoster.getText().isEmpty()) {
            outputTextArea.appendText("Enter a name for the student.\n");
            return false;
        } else if (majorModifyRoster.equals("")) {
            outputTextArea.appendText("Please select a major.\n");
            return false;
        } else if (residency.getSelectedToggle() == null) {
            outputTextArea.appendText("Please select a form of residency.\n");
            return false;
        } else if (!(creditHoursTextField.getText().matches("-?\\d+"))) {
            outputTextArea.appendText("Please use only digits for credit hours.\n");
            return false;
        } else if (Integer.parseInt(creditHoursTextField.getText()) < Student.MIN_CREDITS) {
            outputTextArea.appendText("Please use only credit hours greater than 2.\n");
            return false;
        } else if (Integer.parseInt(creditHoursTextField.getText()) > Student.MAX_CREDITS) {
            outputTextArea.appendText("Please use only credit hours less than 24.\n");
            return false;
        } else if (studyAbroadResidency.isSelected() && Integer.parseInt(creditHoursTextField.getText()) > Student.MIN_FULLTIME_CREDITS) {
            outputTextArea.appendText("Students studying abroad can not have more than 12 credits.\n");
            return false;
        } else if (internationalResidency.isSelected() && !studyAbroadResidency.isSelected() && Integer.parseInt(creditHoursTextField.getText()) < Student.MIN_FULLTIME_CREDITS) {
            outputTextArea.appendText("International students must enroll as full time.\n");
            return false;
        } else if (tristateResidency.isSelected() && !nyResidency.isSelected() && !ctResidency.isSelected()) {
            outputTextArea.appendText("Please select a state.\n");
            return false;
        }
        return true;
    }
    
    /**
     * Validates if the current user input can successfully modify a student.
     * @return true if the input is valid, false if not.
     */
    private boolean checkValidStudentInputModifyRoster() {
        if (studentNameTextFieldModifyRoster.getText().isEmpty()) {
            outputTextArea.appendText("Enter a name for the student.\n");
            return false;
        } else if (majorModifyRoster.equals("")) {
            outputTextArea.appendText("Please select a major.\n");
            return false;
        }
        return true;
    }
    
    /**
     * Validates if the current user input can successfully make a payment.
     * @return true if the input is valid, false if not.
     */
    private boolean checkValidStudentInputMakePayment() {
        if (studentNameTextFieldMakePayment.getText().isEmpty()) {
            outputTextArea.appendText("Enter a name for the student.\n");
            return false;
        } else if (majorMakePayment.equals("")) {
            outputTextArea.appendText("Please select a major.\n");
            return false;
        }
        return true;
    }
    
    /**
     * Validates if the current user input can successfully calculate tuition.
     * @return true if the input is valid, false if not.
     */
    private boolean checkValidStudentInputCalculateTuition() {
        if (studentNameTextFieldCalculateTuition.getText().isEmpty()) {
            outputTextArea.appendText("Enter a name for the student.\n");
            return false;
        } else if (majorCalculateTuition.equals("")) {
            outputTextArea.appendText("Please select a major.\n");
            return false;
        }
        return true;
    }
    
    /**
     * Sets the student's major in the modify roster page on selection of a radio button.
     */
    @FXML
    private void setMajorModifyRoster() {
        if (csMajorModifyRoster.isSelected()) {
            majorModifyRoster = "CS";
        } else if (baMajorModifyRoster.isSelected()) {
            majorModifyRoster = "BA";
        } else if (eeMajorModifyRoster.isSelected()) {
            majorModifyRoster = "EE";
        } else if (meMajorModifyRoster.isSelected()) {
            majorModifyRoster = "ME";
        } else if (itMajorModifyRoster.isSelected()) {
            majorModifyRoster = "IT";
        }
    }
    
    /**
     * Sets the student's major in the make payment page on selection of a radio button.
     * Also disables financial aid if the student is not a Resident or not a fulltime student,
     * and disables payment if the student could not be found in the roster.
     */
    @FXML
    private void setMajorMakePayment() {
        if (csMajorMakePayment.isSelected()) {
            majorMakePayment = "CS";
        } else if (baMajorMakePayment.isSelected()) {
            majorMakePayment = "BA";
        } else if (eeMajorMakePayment.isSelected()) {
            majorMakePayment = "EE";
        } else if (meMajorMakePayment.isSelected()) {
            majorMakePayment = "ME";
        } else if (itMajorMakePayment.isSelected()) {
            majorMakePayment = "IT";
        }
        if (!checkValidStudentInputMakePayment()) {
            return;
        }
        Student student = roster.getStudent(new Student(studentNameTextFieldMakePayment.getText(), majorMakePayment));
        if (student == null) {
            giveFinancialAid.setDisable(true);
            makePayment.setDisable(true);
            outputTextArea.appendText("Could not find student.\n");
        } else if (!(student instanceof Resident) || student.getCredits() < Student.MIN_FULLTIME_CREDITS) {
            giveFinancialAid.setDisable(true);
            makePayment.setDisable(false);
        } else {
            giveFinancialAid.setDisable(false);
            makePayment.setDisable(false);
        }
            
    }
    
    /**
     * Sets the student's major in the calculate tuition page on selection of a radio button.
     */
    @FXML
    private void setMajorCalculateTuition() {
        if (csMajorCalculateTuition.isSelected()) {
            majorCalculateTuition = "CS";
        } else if (baMajorCalculateTuition.isSelected()) {
            majorCalculateTuition = "BA";
        } else if (eeMajorCalculateTuition.isSelected()) {
            majorCalculateTuition = "EE";
        } else if (meMajorCalculateTuition.isSelected()) {
            majorCalculateTuition = "ME";
        } else if (itMajorCalculateTuition.isSelected()) {
            majorCalculateTuition = "IT";
        }
    }

    /**
     * Adds a student to the roster based on user input specifications.
     * The method prints out the status of the action—whether the student was added to the roster,
     * or if the student is already inside the roster.
     */
    @FXML
    private void addStudentToRoster() {
        Student student = null;
        if (checkValidAddStudentInput()) {
            if (residentResidency.isSelected()) {
                student = new Resident(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                        Integer.parseInt(creditHoursTextField.getText()));
            } else if (nonresidentResidency.isSelected()) {
                student = new NonResident(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                        Integer.parseInt(creditHoursTextField.getText()));
            } else if (internationalResidency.isSelected()) {
                if (studyAbroadResidency.isSelected()) {
                    student = new International(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                            Integer.parseInt(creditHoursTextField.getText()), true);
                } else {
                    student = new International(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                            Integer.parseInt(creditHoursTextField.getText()), false);
                }
            } else if (tristateResidency.isSelected()) {
                if (nyResidency.isSelected()) {
                    student = new TriState(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                            Integer.parseInt(creditHoursTextField.getText()), "NY");
                } else {
                    student = new TriState(studentNameTextFieldModifyRoster.getText(), majorModifyRoster,
                            Integer.parseInt(creditHoursTextField.getText()), "CT");
                }
            }
            if (roster.add(student)) {
                outputTextArea.appendText(studentNameTextFieldModifyRoster.getText() + " was added to roster.\n");
            } else {
                outputTextArea.appendText(studentNameTextFieldModifyRoster.getText() + " student is already in roster.\n");
            }
        }
    }

    /**
     * Deletes a student from the roster.
     * If the student exists in the roster, it is deleted and the appropraite statemenet
     * is printed. If the student is not in the array, a message outlining that the student
     * is not in the roster is printed.
     */
    @FXML
    private void deleteStudentFromRoster() {
        if (checkValidStudentInputModifyRoster()) {
            Student student = new Student(studentNameTextFieldModifyRoster.getText(), majorModifyRoster);
            if (roster.remove(student)) {
                outputTextArea.appendText(studentNameTextFieldModifyRoster.getText() + " was removed.\n");
            } else {
                outputTextArea.appendText(studentNameTextFieldModifyRoster.getText() + " is not in the roster.\n");
            }
        }
    }
    
    /**
     * Prints out the entire roster.
     */
    @FXML
    private void displayRoster() {
        outputTextArea.appendText(roster.print() + "\n");
    }

    /**
     * Prints out the entire roster in alphabetical order by student name.
     */
    @FXML
    private void displayRosterByName() {
        outputTextArea.appendText(roster.printByName() + "\n");
    }

    /**
     * Prints out the entire roster in order of last payment date.
     */
    @FXML
    private void displayRosterByPaymentDate() {
        outputTextArea.appendText(roster.printByPaymentDate() + "\n");
    }

    /**
     * Sets an international student to study abroad. The student's tuition and
     * payment dates are recalculated.
     */
    @FXML
    private void setStudyAbroadStatus() {
        if (checkValidStudentInputModifyRoster()) {
            Student student = roster.getStudent(new Student(studentNameTextFieldModifyRoster.getText(), majorModifyRoster));
            if (student == null || !(student instanceof International)) {
                outputTextArea.appendText("International student not found.\n");
            } else {
                International international = (International) student;
                international.setStudyAbroad(studyAbroadResidency.isSelected());
                outputTextArea.appendText(studentNameTextFieldModifyRoster.getText() + "'s study abroad status was updated.\n");
            }

        }
    }
    
    /**
     * Checks to see if the given student is able to receive financial aid, and updates the student's financial aid given an input value.
     */
    @FXML
    private void  giveFinancialAid() {
        if (checkValidStudentInputMakePayment()) {
            Student student = roster.getStudent(new Student(studentNameTextFieldMakePayment.getText(), majorMakePayment));
            if (student == null) {
                outputTextArea.appendText("Student not found.\n");
            } else if (paymentAmountTextField.getText() == null) {
                outputTextArea.appendText("Payment amount not found.\n");
            } else if (!(paymentAmountTextField.getText().matches("-?\\d+"))) {
                outputTextArea.appendText("Please use digits for the amount.\n");
            }else if (Integer.parseInt(paymentAmountTextField.getText()) == 0) {
                outputTextArea.appendText("Please enter payment amount greater than zero.\n");
            } else if (Integer.parseInt(paymentAmountTextField.getText()) < 0) {
                outputTextArea.appendText("Please enter a non-negative payment amount.\n");
            } else if (student.getTuition() <= 0) {
                outputTextArea.appendText("Student has no tuition due.\n");
            } else if (Integer.parseInt(paymentAmountTextField.getText()) > Resident.MAX_FINANCIAL_AID_AMOUNT) {
                outputTextArea.appendText("Invalid financial aid amount.\n");
            } else if (roster.canReceiveFinancialAid(student) != null) {
                Resident resident = (Resident) student;
                if (resident.getFinancialAidStatus()) {
                    outputTextArea.appendText("Awarded once already.\n");
                } else if (resident.getCredits() < Student.MIN_FULLTIME_CREDITS) {
                    outputTextArea.appendText("Parttime student doesn't qualify for the award.\n");
                } else {
                    resident.giveFinancialAid(Double.valueOf(paymentAmountTextField.getText()));
                    outputTextArea.appendText("Tuition updated.\n");
                }
            } else {
                outputTextArea.appendText("Not a resident student.\n");
            }
        }
    }
    
    /**
     * Checks to see if the given student has tuition due, and updates the student's total payment amount given an input.
     */
    @FXML
    private void  makePayment() {
        if (checkValidStudentInputMakePayment()) {
            Student student = roster.getStudent(new Student(studentNameTextFieldMakePayment.getText(), majorMakePayment));
            if (student == null) {
                outputTextArea.appendText("Student not found.\n");
            } else if (paymentAmountTextField.getText() == null || paymentAmountTextField.getText() == "") {
                outputTextArea.appendText("Payment amount not found.\n");
            } else if (!(paymentAmountTextField.getText().matches("-?\\d+"))) {
                outputTextArea.appendText("Please use digits for the amount.\n");
            } else if (Integer.parseInt(paymentAmountTextField.getText()) == 0) {
                outputTextArea.appendText("Please enter payment amount greater than zero.\n");
            } else if (Integer.parseInt(paymentAmountTextField.getText()) < 0) {
                outputTextArea.appendText("Please enter a non-negative payment amount.\n");
            } else if (student.getTuition() <= 0) {
                outputTextArea.appendText("Student has no tuition due.\n");
            } else if (paymentDate.getValue() == null) {
                outputTextArea.appendText("Please enter a valid date.\n");
            } else {
                String[] tmpDate = paymentDate.getValue().toString().split("-");
                Date date = new Date(String.join("/", new String[] {tmpDate[1], tmpDate[2], tmpDate[0]}));
                if (!date.isValid()) {
                    outputTextArea.appendText("Please enter a valid date.\n");
                } else if (student.makePayment(Integer.parseInt(paymentAmountTextField.getText()), date)){
                    outputTextArea.appendText("Tuition due after payment:" + student.getTuition()+"\n");
                } else {
                    outputTextArea.appendText("Payment is greater than the tuition.\n");
                }
            }

        }
    }
    
    /**
     * Calculates the tuition due for a single student, provided by user input.
     */
    @FXML
    private void  calculateStudentTuition() {
        if (checkValidStudentInputCalculateTuition()) {
            Student student =  roster.getStudent(new Student(studentNameTextFieldCalculateTuition.getText(), majorCalculateTuition));
            if (student == null) {
                outputTextArea.appendText("Student not found.\n");
            } else {
                student.tuitionDue();
                outputTextArea.appendText(studentNameTextFieldCalculateTuition.getText() + " tuition due:" + student.getTuition()+"\n");
            }
        }
    }
    
    /**
     * Calculates the tuition due for all students in the roster.
     */
    @FXML
    private void  calculateRosterTuition() {
        roster.calculateTuition();
        outputTextArea.appendText("Calculated tuition due for all students in the roster.\n");
    }

}