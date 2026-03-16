package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import project2.*;
import util.Date;
import java.util.Calendar;

/**
 * Controller for the Student Registration System GUI.
 * Handles all user interactions from the view (View.fxml) and
 * delegates business logic to the model (StudentList, Schedule).
 *
 * @author tjt97
 * @author mss444
 */
public class Controller {

    // ==================== MODEL ====================

    /** The list of all registered students. */
    private StudentList studentList = new StudentList();

    /** The course schedule containing all offered sections. */
    private Schedule schedule = new Schedule();

    // ==================== STUDENTS TAB — FXML FIELDS ====================

    @FXML private ComboBox<String> cbStudentType;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfDob;
    @FXML private TextField tfCredits;
    @FXML private TextField tfScholarship;

    @FXML private Label lblState;
    @FXML private HBox hbState;
    @FXML private RadioButton rbNY;
    @FXML private RadioButton rbCT;

    @FXML private Label lblStudyAbroad;
    @FXML private CheckBox cbStudyAbroad;

    @FXML private RadioButton rbCS;
    @FXML private RadioButton rbMATH;
    @FXML private RadioButton rbECE;
    @FXML private RadioButton rbITI;
    @FXML private RadioButton rbBAIT;

    @FXML private TextArea taStudentOutput;

    // ==================== SCHEDULE TAB — FXML FIELDS ====================

    @FXML private TextField tfSchedFirstName;
    @FXML private TextField tfSchedLastName;
    @FXML private TextField tfSchedDob;

    @FXML private RadioButton rbCS213;
    @FXML private RadioButton rbCS214;
    @FXML private RadioButton rbCS336;
    @FXML private RadioButton rbITI301;
    @FXML private RadioButton rbITI403;
    @FXML private RadioButton rbEE111;
    @FXML private RadioButton rbMATH167;

    @FXML private RadioButton rbTime9;
    @FXML private RadioButton rbTime10;
    @FXML private RadioButton rbTime11;
    @FXML private RadioButton rbTime13;
    @FXML private RadioButton rbTime14;

    @FXML private RadioButton rbMenendez;
    @FXML private RadioButton rbVenugopal;
    @FXML private RadioButton rbSesh;
    @FXML private RadioButton rbKaplan;
    @FXML private RadioButton rbMalloy;

    @FXML private RadioButton rbAB2225;
    @FXML private RadioButton rbARC103;
    @FXML private RadioButton rbHLL114;
    @FXML private RadioButton rbBE100;
    @FXML private RadioButton rbTIL232;

    @FXML private TextArea taScheduleOutput;

    // ==================== REPORTS TAB — FXML FIELDS ====================

    @FXML private TextArea taReportOutput;

    // ==================== TOGGLE GROUPS ====================

    private ToggleGroup studentTypeGroup;
    private ToggleGroup majorGroup;
    private ToggleGroup stateGroup;
    private ToggleGroup courseGroup;
    private ToggleGroup timeGroup;
    private ToggleGroup instructorGroup;
    private ToggleGroup classroomGroup;

    // ==================== INITIALIZATION ====================

    /**
     * Called automatically by JavaFX after all @FXML fields are injected.
     * Wires all RadioButtons into their ToggleGroups and configures
     * listener on the student-type group to show/hide conditional fields
     * (state for TriState, study-abroad checkbox for International).
     */
    @FXML
    public void initialize() {

        //-----------student tab------------

        //major group toggle group
        majorGroup = new ToggleGroup();
        rbCS.setToggleGroup(majorGroup);
        rbMATH.setToggleGroup(majorGroup);
        rbECE.setToggleGroup(majorGroup);
        rbITI.setToggleGroup(majorGroup);
        rbBAIT.setToggleGroup(majorGroup);

        //student type Combo Box
        cbStudentType.getItems().addAll("Resident", "Tristate", "International");

        //if tristate is selected
        stateGroup = new ToggleGroup();
        rbNY.setToggleGroup(stateGroup);
        rbCT.setToggleGroup(stateGroup);

        // Hide optional fields by default
        hbState.setVisible(false);
        lblState.setVisible(false);

        cbStudyAbroad.setVisible(false);
        lblStudyAbroad.setVisible(false);

        // Listen for student type selection
        cbStudentType.setOnAction(e -> {
            String type = cbStudentType.getValue();

            if (type.equals("Tristate")) {
                hbState.setVisible(true);
                lblState.setVisible(true);

                cbStudyAbroad.setVisible(false);
                lblStudyAbroad.setVisible(false);
            }

            else if (type.equals("International")) {
                hbState.setVisible(false);
                lblState.setVisible(false);

                cbStudyAbroad.setVisible(true);
                lblStudyAbroad.setVisible(true);
            }

            else { // Resident
                hbState.setVisible(false);
                lblState.setVisible(false);

                cbStudyAbroad.setVisible(false);
                lblStudyAbroad.setVisible(false);
            }
        });






    }

    // ==================== STUDENTS TAB HANDLERS ====================

    /**
     * Handles the Add button.
     * Reads the student type RadioButton selection, then reads first name,
     * last name, date of birth, credits completed, and major from the input fields.
     * For TriState also reads the selected state (NY/CT).
     * For International also reads the study-abroad checkbox.
     * Validates all inputs and displays an error in taStudentOutput if any
     * field is missing or invalid; otherwise creates the correct Student subclass
     * and adds it to studentList, confirming success in taStudentOutput.
     *
     * @param e the ActionEvent fired by the Add button
     */
    @FXML
    private void handleAdd(ActionEvent e) {
        StringBuilder errors = new StringBuilder();
        boolean validInput = true;

        String studentType = cbStudentType.getValue();
        String fname = tfFirstName.getText().trim();
        String lname = tfLastName.getText().trim();
        String inputDate = tfDob.getText().trim();
        String creditText = tfCredits.getText().trim();

        // ---------- INPUT VALIDATION ONLY ----------
        if (studentType == null) {
            errors.append("Student type not selected.\n");
            validInput = false;
        }

        if (fname.isEmpty() || lname.isEmpty() || inputDate.isEmpty() || creditText.isEmpty()) {
            errors.append("Missing data tokens.\n");
            validInput = false;
        }

        Date dob = null;
        if (!inputDate.isEmpty()) {
            dob = checkDate(inputDate, errors);
            if (dob == null) {
                validInput = false;
            }
        }

        Integer credits = null;
        if (!creditText.isEmpty()) {
            credits = checkCredit(creditText, errors);
            if (credits == null) {
                validInput = false;
            }
        }

        Major major = null;
        if (rbCS.isSelected()) major = Major.CS;
        else if (rbMATH.isSelected()) major = Major.MATH;
        else if (rbECE.isSelected()) major = Major.ECE;
        else if (rbITI.isSelected()) major = Major.ITI;
        else if (rbBAIT.isSelected()) major = Major.BAIT;
        else {
            errors.append("Major not selected.\n");
            validInput = false;
        }

        if ("Tristate".equals(studentType)) {
            if (!rbNY.isSelected() && !rbCT.isSelected()) {
                errors.append("State not selected.\n");
                validInput = false;
            }
        }

        // If any input is invalid, show only input errors and stop
        if (!validInput) {
            taStudentOutput.setText(errors.toString());
            return;
        }

        // ---------- MAIN ADD LOGIC ----------
        Profile profile = checkProfile(fname, lname, dob, errors);
        if (profile == null) {
            taStudentOutput.setText("[" + fname + " " + lname + " " + dob + "] student is already in the list.");
            return;
        }

        if ("Resident".equals(studentType)) {
            studentList.add(new Resident(profile, major, credits));
            taStudentOutput.setText(profile + " [Resident] added to the list.");
            return;
        }

        if ("Tristate".equals(studentType)) {
            String state = rbNY.isSelected() ? "NY" : "CT";
            studentList.add(new TriState(profile, major, credits, state));
            taStudentOutput.setText(profile + " [Tristate: " + state + "] added to the list.");
            return;
        }

        if ("International".equals(studentType)) {
            boolean isStudyAbroad = cbStudyAbroad.isSelected();
            studentList.add(new International(profile, major, credits, isStudyAbroad));

            if (isStudyAbroad) {
                taStudentOutput.setText(profile + " [International study abroad] added to the list.");
            } else {
                taStudentOutput.setText(profile + " [International] added to the list.");
            }
        }
    }

    /**
     * Handles the Remove button.
     * Reads first name, last name, and date of birth to build a Profile.
     * Validates that the student is not currently enrolled in any section
     * before removing; displays an error in taStudentOutput if the student
     * is not found or is still enrolled, otherwise removes and confirms.
     *
     * @param e the ActionEvent fired by the Remove button
     */
    @FXML
    private void handleRemove(ActionEvent e) {

        StringBuilder errors = new StringBuilder();
        boolean validInput = true;

        String fname = tfFirstName.getText().trim();
        String lname = tfLastName.getText().trim();
        String inputDate = tfDob.getText().trim();

        // ---------- INPUT VALIDATION ----------
        if (fname.isEmpty()) {
            errors.append("First name missing.\n");
            validInput = false;
        }

        if (lname.isEmpty()) {
            errors.append("Last name missing.\n");
            validInput = false;
        }

        Date dob = null;
        if (inputDate.isEmpty()) {
            errors.append("Date of birth missing.\n");
            validInput = false;
        } else {
            dob = checkDate(inputDate, errors);
            if (dob == null) {
                validInput = false;
            }
        }

        // If validation failed → print all errors
        if (!validInput) {
            taStudentOutput.setText(errors.toString());
            return;
        }

        // ---------- MAIN LOGIC (same as doRemove) ----------

        Profile profile = new Profile(fname, lname, dob);
        Student student = studentList.getStudent(profile);

        if (student == null) {
            taStudentOutput.setText(profile.toString() + " is not in the student list.");
            return;
        }

        if (schedule.isStudentEnrolled(student)) {
            taStudentOutput.setText(profile.toString() + " is currently enrolled in a section.");
            return;
        }

        studentList.remove(student);
        taStudentOutput.setText(profile.toString() + " removed from the list.");
    }

    /**
     * Handles the Set Scholarship button.
     * Reads first name, last name, date of birth, and scholarship amount.
     * Validates that the student exists, is a Resident, and that the
     * scholarship amount is a valid non-negative integer.
     * Displays an error in taStudentOutput on any violation; otherwise
     * updates the scholarship and confirms in taStudentOutput.
     *
     * @param e the ActionEvent fired by the Set Scholarship button
     */
    @FXML
    private void handleSetScholarship(ActionEvent e) {
        StringBuilder errors = new StringBuilder();
        boolean validInput = true;

        String fname = tfFirstName.getText().trim();
        String lname = tfLastName.getText().trim();
        String inputDate = tfDob.getText().trim();
        String scholarshipText = tfScholarship.getText().trim();

        // ---------- INPUT VALIDATION ----------
        if (fname.isEmpty()) {
            errors.append("First name missing.\n");
            validInput = false;
        }

        if (lname.isEmpty()) {
            errors.append("Last name missing.\n");
            validInput = false;
        }

        Date dob = null;
        if (inputDate.isEmpty()) {
            errors.append("Date of birth missing.\n");
            validInput = false;
        } else {
            dob = checkDate(inputDate, errors);
            if (dob == null) {
                validInput = false;
            }
        }

        Integer amount = null;
        if (scholarshipText.isEmpty()) {
            errors.append("Scholarship amount missing.\n");
            validInput = false;
        } else {
            try {
                amount = Integer.parseInt(scholarshipText);
            } catch (NumberFormatException ex) {
                errors.append("INVALID: amount is not an integer.\n");
                validInput = false;
            }
        }

        // If input validation failed, print all input errors together
        if (!validInput) {
            taStudentOutput.setText(errors.toString());
            return;
        }

        // ---------- MAIN LOGIC ----------
        Profile profile = new Profile(fname, lname, dob);
        Student student = studentList.getStudent(profile);

        if (student == null) {
            taStudentOutput.setText("[" + fname + " " + lname + " " + inputDate + "] does not exist.");
            return;
        }

        if (!(student instanceof Resident)) {
            taStudentOutput.setText("[" + fname + " " + lname + " " + inputDate + "] is a non-resident not eligible for the scholarship.");
            return;
        }

        if (schedule.getTotalCredits(student) < 12) {
            taStudentOutput.setText("[" + fname + " " + lname + " " + inputDate + "] enrolled less than 12 credits, not eligible for the scholarship.");
            return;
        }

        if (amount <= 0 || amount > 10000) {
            taStudentOutput.setText("INVALID: scholarship amount cannot be 0 or negative or greater than $10,000.");
            return;
        }

        ((Resident) student).setScholarship(amount);
        taStudentOutput.setText("Scholarship $" + String.format("%,d", amount) +
                " updated for [" + fname + " " + lname + " " + inputDate + "]");
    }

    /**
     * Handles the Load from File button.
     * Opens a FileChooser dialog so the user can select a .txt student file.
     * Parses each line to create and add the appropriate Student subclass.
     * Displays the number of students loaded or any file/parse errors
     * in taStudentOutput.
     *
     * @param e the ActionEvent fired by the Load from File button
     */
    @FXML
    private void handleLoad(ActionEvent e) { }

    // ==================== SCHEDULE TAB HANDLERS ====================

    /**
     * Handles the Offer Section button.
     * Reads the selected course, time, instructor, and classroom RadioButtons.
     * Validates that all four selections are made and that the instructor and
     * classroom are both available at the chosen time.
     * Displays an error in taScheduleOutput on any conflict; otherwise adds
     * the new Section to the schedule and confirms in taScheduleOutput.
     *
     * @param e the ActionEvent fired by the Offer Section button
     */
    @FXML
    private void handleOffer(ActionEvent e) { }

    /**
     * Handles the Close Section button.
     * Reads the selected course and time RadioButtons to identify the section.
     * Validates that the section exists and has no enrolled students before
     * removing it from the schedule.
     * Displays an error in taScheduleOutput if not found or still has students;
     * otherwise removes and confirms.
     *
     * @param e the ActionEvent fired by the Close Section button
     */
    @FXML
    private void handleClose(ActionEvent e) { }

    /**
     * Handles the Enroll Student button.
     * Reads first name, last name, and date of birth to find the student, then
     * reads the selected course and time to find the section.
     * Validates that the student exists, the section exists, the student is not
     * already enrolled in another section of the same course, and there is no
     * time conflict with their existing schedule.
     * Displays an error in taScheduleOutput on any violation; otherwise enrolls
     * and confirms.
     *
     * @param e the ActionEvent fired by the Enroll Student button
     */
    @FXML
    private void handleEnroll(ActionEvent e) { }

    /**
     * Handles the Drop Student button.
     * Reads first name, last name, and date of birth to find the student, then
     * reads the selected course and time to find the section.
     * Validates that the student exists and is currently enrolled in that section.
     * Displays an error in taScheduleOutput if not found; otherwise drops the
     * student and confirms.
     *
     * @param e the ActionEvent fired by the Drop Student button
     */
    @FXML
    private void handleDrop(ActionEvent e) { }

    // ==================== REPORTS TAB HANDLERS ====================

    /**
     * Handles the Print Students button.
     * Retrieves the full student list sorted by last name, first name, and DOB
     * from studentList and displays it in taReportOutput.
     *
     * @param e the ActionEvent fired by the Print Students button
     */
    @FXML
    private void handlePrintStudents(ActionEvent e) { }

    /**
     * Handles the Print by Classroom button.
     * Retrieves the schedule sorted by campus and building from schedule
     * and displays it in taReportOutput.
     *
     * @param e the ActionEvent fired by the Print by Classroom button
     */
    @FXML
    private void handlePrintByClassroom(ActionEvent e) { }

    /**
     * Handles the Print by Course button.
     * Retrieves the schedule sorted by course name and section time from schedule
     * and displays it in taReportOutput.
     *
     * @param e the ActionEvent fired by the Print by Course button
     */
    @FXML
    private void handlePrintByCourse(ActionEvent e) { }

    /**
     * Handles the Print Tuition button.
     * Calculates and displays tuition for every student based on their enrolled
     * credits and student type. International non-study-abroad students enrolled
     * in fewer than 12 credits receive a warning instead of a tuition amount.
     * Output is displayed in taReportOutput.
     *
     * @param e the ActionEvent fired by the Print Tuition button
     */
    @FXML
    private void handlePrintTuition(ActionEvent e) { }

    /**
     * Handles the Print Graduating button.
     * Identifies students whose completed credits plus currently enrolled credits
     * total at least 120, sorts them by major, and displays the list in taReportOutput.
     *
     * @param e the ActionEvent fired by the Print Graduating button
     */
    @FXML
    private void handlePrintGraduating(ActionEvent e) { }


    private Date checkDate(String dateToken, StringBuilder errors) {
        String[] date = dateToken.split("/");

        if (date.length != 3) {
            errors.append("INVALID: ").append(dateToken)
                    .append(" is not a valid calendar date!\n");
            return null;
        }

        int month, day, year;
        try {
            month = Integer.parseInt(date[0]);
            day = Integer.parseInt(date[1]);
            year = Integer.parseInt(date[2]);
        } catch (NumberFormatException e) {
            errors.append("INVALID: ").append(dateToken)
                    .append(" is not a valid calendar date!\n");
            return null;
        }

        Date dob = new Date(month, day, year);

        if (!dob.isValid()) {
            errors.append("INVALID: ").append(dob)
                    .append(" is not a valid calendar date!\n");
            return null;
        }

        Calendar dobcalendar = dob.toCalendar();
        Calendar today = Calendar.getInstance();
        if (!dobcalendar.before(today)) {
            errors.append("INVALID: ").append(dob)
                    .append(" cannot be today or a future day.\n");
            return null;
        }

        Calendar sixteen = Calendar.getInstance();
        sixteen.add(Calendar.YEAR, -16);
        if (dobcalendar.after(sixteen)) {
            errors.append("INVALID: ").append(dob)
                    .append(" younger than 16 years old.\n");
            return null;
        }

        return dob;
    }

    /** Checks if a profile already exists in the student list
     * @param first first name input
     * @param last last name input
     * @param dob checked dob input
     * @return a new Profile if it does not already exist, or null if the student is already in the list */
    private Profile checkProfile(String first, String last, Date dob, StringBuilder errors) {
        Profile profile = new Profile(first, last, dob);
        if (studentList.getStudent(profile) != null) {
            errors.append("[").append(first).append(" ").append(last).append(" ").append(dob)
                    .append("] student is already in the list.\n");
            return null;
        }
        return profile;
    }

    private Integer checkCredit(String num, StringBuilder errors) {
        int credits;
        try {
            credits = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            errors.append("INVALID: ").append(num).append(" is not an integer!\n");
            return null;
        }

        if (credits < 0) {
            errors.append("INVALID: ").append(num).append(" credit is negative!\n");
            return null;
        }

        return credits;
    }

    }



