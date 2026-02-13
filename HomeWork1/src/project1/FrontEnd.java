package project1;
import java.time.Period;
import java.util.Scanner;
import java.util.Calendar;

public class FrontEnd {

    private StudentList studentlist;
    private Schedule schedule;
    private static final int OFFERARGS = 5;

    public FrontEnd(){
        studentlist = new StudentList();
        schedule = new Schedule();
    }
/** Run the program, take in user input and based on the input command trigger a method*/
    public void run(){
        System.out.println("Registration System is running.");

        Scanner scanner = new Scanner(System.in);

        while (true){
            String line = scanner.nextLine().trim();

            if(line.isEmpty()){
                continue;
            }
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            if (!command.matches("[A-Z]+")) {
                System.out.println(command + " is an invalid command!");
                continue;
            }

            switch (command){
                case "A":
                    doAdd(tokens);
                    break;
                case "R":
                    doRemove(tokens);
                    break;
                case "O":
                    doAddSection(tokens);
                    break;
                case "C":
                    doClose(tokens);
                    break;
                case "E":
                    doEnroll(tokens);
                    break;
                case "D":
                    doDrop(tokens);
                    break;
                case "PS":
                    studentlist.print();
                    break;
                case "PL":
                    schedule.printByClassroom();
                    break;
                case "PC":
                    schedule.printByCourse();
                    break;
                case "Q":
                    System.out.println("Registration System is terminated.");
                    return;

                default:
                    System.out.println(command + " is an invalid command!");
            }

        }
    }

/** Add a student to student list based on input features
 * @param tokens input list of features for the student */
    private void doAdd(String[] tokens){
        String firstName = tokens[1];
        String lastName = tokens[2];
        String date = tokens[3];
        String majorT = tokens[4];
        String creditT = tokens[5];

        Date dob = checkDate(date);
        if (dob == null) return;

        Profile profile = checkProfile(firstName, lastName, dob);
        if(profile == null) {
            //System.out.println("Invalid" );
            return;
        }

        Major major = checkMajor(majorT);
        if(major == null) {
            //System.out.println("INVALID: "+ majorT +" does not exist");
            return;
        }

        Integer credits = checkCredit(creditT);
        if(credits == null) return;

        Student s = new Student(profile, major, credits);
        studentlist.add(s);
        System.out.println("["+firstName+" "+ lastName+ " "+ date+ "] added to the list.");



    }
/** doAddSection addes a section of a course to the schedule
 * @param tokens info for the course we are adding */
    private void doAddSection(String[] tokens){

        if (tokens.length < OFFERARGS) {
            System.out.println("Not enough arguments");
            return;
        }

       String courseNum = tokens[1];
       int inputPeriod = Integer.parseInt(tokens[2]);
       String inputInstructor = tokens[3];
       String roomNum = tokens[4];

       Course course;
       try{
           course = Course.valueOf(courseNum.toUpperCase());
       }catch (IllegalArgumentException e){
           System.out.println("INVALID: course name " + courseNum + " does not exist.");
           return;
       }
       Time time = findTimePeriod(inputPeriod);
       if(time == null){
           System.out.println("INVALID: period " + inputPeriod + " does not exist.");
           return;
       }
       Classroom classroom = findClassroom(roomNum);
       if(classroom == null) {
           System.out.println("INVALID: classroom " + roomNum + " does not exist.");
           return;
       }
       Instructor instructor = findInstructor(inputInstructor);
       if (instructor == null) {
           System.out.println("INVALID: instructor " + inputInstructor + " does not exist.");
           return;
       }

       Section section = new Section(course, instructor, classroom, time);
       if (schedule.contains(section)) {
           System.out.println("INVALID: " + section.getCourse().getCourseNum() + " period " + section.getTime().getPeriodNum() + " already exists." );
           return;
       }
       if (!(instructor.checkAvailability(inputPeriod))) {
           System.out.println("INVALID: " + inputInstructor.toUpperCase() + " time conflict." );
           return;
       }
       if (!(classroom.checkAvailability(inputPeriod))) {
           System.out.println("INVALID: [" + classroom.getClassroomNum() + ", " + classroom.getBuilding() + ", " + classroom.getCampus() + "] not available.");
           return;
       }

       schedule.add(section);
       instructor.fillAvailability(inputPeriod);
       classroom.fillAvailability(inputPeriod);
       System.out.println(section + " added to the schedule.");


    }
/** Close a section on the schedule
 * @param tokens input info*/
    private void doClose(String[] tokens){
        String courseInput  = tokens[1];
        int inputPeriod = Integer.parseInt(tokens[2]);
        Course course;
        try{
            course = Course.valueOf(courseInput.toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("INVALID: course name " + courseInput + " does not exist.");
            return;
        }
        Time time = findTimePeriod(inputPeriod);
        if(time == null){
            System.out.println("INVALID: period " + inputPeriod + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, time);
        if (section == null) {
            System.out.println("Section does not exist.");
            return;
        }
        if(!section.isEmpty()){
            System.out.println("Section roster is not empty.");
            return;
        }
        schedule.remove(section);
        System.out.println("Section closed successfully.");
    }
    /** doEnroll, enroll student in a course
     * @param tokens input info*/
    private void doEnroll(String[] tokens) {
        String fname = tokens[1];
        String lname = tokens[2];
        String dob = tokens[3];
        String courseInput = tokens[4];
        String inputSection = tokens[5];
        int period;
        try {
            period = Integer.parseInt(inputSection);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: period " + inputSection + " does not exist.");
            return;
        }
        Date date = checkDate(dob);
        Profile tempP = new Profile(fname, lname, date);
        Student student = new Student(tempP, null, 0);
        if(!studentlist.contains(student)){
            System.out.println("Student not found");
            return;
        }
        Course course;
        try{
            course = Course.valueOf(courseInput.toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("INVALID: course name " + courseInput + " does not exist.");
            return;
        }
        Time time = findTimePeriod(period);
        if(time == null){
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, time);
        if (section == null) {
            System.out.println("Section does not exist.");
            return;
        }
        if (schedule.isEnrolledInCourse(student, course)) {   // MUST be public
            System.out.println("Student already enrolled in this course.");
            return;
        }
        if (student.getStanding().compareTo(course.getStanding()) < 0) {
            System.out.println("Student does not meet standing requirement.");
            return;
        }
        if (course.getMajor() != null && student.getMajor() != course.getMajor()) {
            System.out.println("Student does not meet major requirement.");
            return;
        }
        if (schedule.hasTimeConflict(student, time)) {
            System.out.println("Time conflict detected.");
            return;
        }
        int currentCredits = schedule.getTotalCredits(student);
        if (currentCredits + course.getCreditHours() > 18) {
            System.out.println("Credit limit exceeded.");
            return;
        }
        if (section.isFull()) {
            System.out.println("Section is full.");
            return;
        }
        section.enroll(student);
        System.out.println("Student successfully enrolled to this section.");
    }
/** doDrop, drop a student from a section
 * @param tokens info for student and the course and section*/
    private void doDrop(String [] tokens){
        String fname = tokens[1];
        String lname = tokens[2];
        String inputDate = tokens[3];
        String inputCourse = tokens[4];
        String inputPeriod = tokens[5];

        Date date = checkDate(inputDate);
        Profile profile = new Profile(fname, lname, date);
        Student student = new Student(profile, null, 0);
        if(!studentlist.contains(student)){
            System.out.println("Student " + fname +" "+ lname+ " does not exist.");
            return;
        }
        Course course;
        try{
            course = Course.valueOf(inputCourse.toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("INVALID: course name " + inputCourse + " does not exist.");
            return;
        }
        int period;
        try {
            period = Integer.parseInt(inputPeriod);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: period " + inputPeriod + " does not exist.");
            return;
        }
        Time time = findTimePeriod(period);
        if(time == null){
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }
        Section section = schedule.getSection(course, time);
        if (section == null) {
            System.out.println("Section does not exist.");
            return;
            }
        if(!section.contains(student)){
            System.out.println("Section does not contain Student: "+fname+ " "+ lname+".");
            return;
        }
        section.drop(student);
        System.out.println("Student successfully dropped from section.");
        
    }




/** find time helper method, used to return time when given period
 * @param input integer period that was input
 * @return time the time which the period occurs */
    private Time findTimePeriod(int input){
        for(Time t : Time.values()){
            if(t.getPeriodNum() == input){
                return t;
            }
        }
        return null;
    }

    /**
     * find course helper method, used to return course when given the course num
     * @param courseNum the identifying key of the course
     * @return course object
     */
    private Course findCourse(String courseNum){
        for (Course c: Course.values()) {
            if (c.getCourseNum().equalsIgnoreCase(courseNum)) {
                return c;
            }
        }
        return null;
    }

    /**
     * find classroom helper method, used to return classroom when given the classroom num
     * @param classroomNum identifying key for enum class classroom
     * @return classroom object
     */
    private Classroom findClassroom(String classroomNum) {
        for (Classroom cl: Classroom.values()) {
            if (cl.getClassroomNum().equalsIgnoreCase(classroomNum)){
                return cl;
            }
        }
        return null;
    }

    /**
     * find instructor helper method, used to return instructo when given instructor's name
     * @param instructorName name of instructor
     * @return instructor object
     */
    private Instructor findInstructor (String instructorName) {
        for (Instructor i: Instructor.values()) {
            if (i.getName().equalsIgnoreCase(instructorName)) {
                return i;
            }
        }
        return null;
    }

    /** Remove a student based on input commands
     * @param tokens input list of features*/
    private void doRemove(String[] tokens){
        String first = tokens[1];
        String last = tokens[2];
        String date = tokens[3];

        Date dob = checkDate(date);
        if(dob == null) return;
        Profile profile = new Profile(first,last,dob);
        Student student = new Student(profile, null, 0);
        if(!studentlist.contains(student)){
            System.out.println(profile + " is not in the student list.");
            return;
        }
        if(schedule.isStudentEnrolled(student)){
            System.out.println("Student is currently enrolled in a section.");
            return;

        }
        studentlist.remove(student);
        System.out.println(profile + " removed from the list.");
    }

    /** Check if the date is valid
     * check if date is older than 16 years old *
     * @param dateToken string from input that contains input date
     */

    private Date checkDate(String dateToken){
        String[] date = dateToken.split("/");
        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        Date dob = new Date(month, day, year);

        if(!dob.isValid()){
            System.out.println("INVALID: "+dob+" is not a valid calendar date!");
            return null;
        }
        Calendar dobcalendar = dob.toCalendar();
        Calendar today = Calendar.getInstance();
        if(!dobcalendar.before(today)){
            System.out.println("INVALID: " + dob + " cannot be today or a future day.");
            return null;
        }
        Calendar sixteen = Calendar.getInstance();
        sixteen.add(Calendar.YEAR, -16);
        if(dobcalendar.after(sixteen)){
            System.out.println("INVALID: "+ dob + " younger than 16 years old.");
            return null;
        }
        return dob;


    }
    /** This checks if the profile is already made
     * @param first first name input
     * @param last last name input
     * @param  dob checked dob input*/
    private Profile checkProfile(String first, String last, Date dob){
        Profile profile = new Profile(first, last, dob);
        if (studentlist.contains(new Student(profile, null, 0))){
            System.out.println("["+ first+" "+ last+ " "+ dob+  "] student is already in the list.");
            return null;
        }
        return profile;

    }
    /** Check if the input major exists
     * @param majorT the input major */
    private Major checkMajor(String majorT){
        try {
            return Major.valueOf(majorT.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("INVALID: " + majorT+ " major does not exist.");
            return null;
        }

    }
    /** Check to make sure the input credit number is valid
     * @param num input number of credits */
    private Integer checkCredit(String num){
        int credits;
        try {
            credits = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: "+ num +" is not a number.");
            return null;
        }

        if (credits <= 0) {
            System.out.println("INVALID: "+ num + " credit is negative!");
            return null;
        }
        return credits;

    }


}
