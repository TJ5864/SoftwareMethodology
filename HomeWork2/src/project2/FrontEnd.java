package project2;
import util.Date;

import java.io.File;
import java.util.Scanner;
import java.util.Calendar;
/** Connects all classes through the front end, takes in commands and information related to students, from here
 * we run different checks on students and courses
 * @author tjt97
 * @author mss444*/
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
                case "AR":
                    doAddResident(tokens);
                    break;
                case "AN":
                    doAddNonResident(tokens);
                    break;
                case "AT":
                    doAddTriState(tokens);
                    break;
                case "AI":
                    doAddInternational(tokens);
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
                case "L":
                    doReadList(tokens);
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
                case "PT":



                    break;
                case "PG":
                    studentlist.printGraduatingStudents();
                    break;
                case "S":
                    doSetScholarship(tokens);
                    break;
                case "Q":
                    System.out.println("Registration System is terminated.");
                    return;

                default:
                    System.out.println(command + " is an invalid command!");
            }

        }
    }


    private void doReadList(String [] tokens) {
        java.io.File file = new java.io.File("students.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "R":
                        doAddResident(parts);
                        break;
                    case "N":
                        doAddNonResident(parts);
                        break;
                    case "T":
                        doAddTriState(parts);
                        break;
                    case "I":
                        doAddInternational(parts);
                        break;
                    default:
                        System.out.println(parts[0] + " is an invalid command!");
                }
            }
            fileScanner.close();

        } catch (java.io.FileNotFoundException e) {
            System.out.println("students.txt not found.");
        }
    }



    /** Set scholarship for a full-time resident student
     * @param tokens input containing fname, lname, dob, and scholarship amount */
    private void doSetScholarship(String[] tokens) {
        String fname = tokens[1];
        String lname = tokens[2];
        String inputDate = tokens[3];
        int amount;
        try {
            amount = Integer.parseInt(tokens[4]);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: amount is not an integer.");
            return;
        }
        Date dob = checkDate(inputDate);
        if (dob == null) return;
        Profile profile = new Profile(fname, lname, dob);
        Student student = studentlist.getStudent(profile);
        if (student == null) {
            System.out.println("[" + fname + " " + lname + " " + inputDate + "] does not exist.");
            return;
        }
        if (!(student instanceof Resident)) {
            System.out.println("[" + fname + " " + lname + " " + inputDate + "] is a non-resident not eligible for the scholarship.");
            return;
        }
        if (schedule.getTotalCredits(student) < 12) {
            System.out.println("[" + fname + " " + lname + " " + inputDate + "] enrolled less than 12 credits, not eligible for the scholarship.");
            return;
        }
        if (amount <= 0 || amount > 10000) {
            System.out.println("INVALID: scholarship amount cannot be 0 or negative or greater than $10,000.");
            return;
        }
        ((Resident) student).setScholarship(amount);
        System.out.println("Scholarship $" + String.format("%,d", amount) + " updated for [" + fname + " " + lname + " " + inputDate + "]");
    }

    /** Add a Resident student to the student list
     * @param tokens fname lname dob major credits */
    private void doAddResident(String[] tokens) {
        if (tokens.length < 6) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens[1], lname = tokens[2], inputDate = tokens[3];
        Date dob = checkDate(inputDate);
        if (dob == null) return;
        Profile profile = checkProfile(fname, lname, dob);
        if (profile == null) return;
        Major major = checkMajor(tokens[4]);
        if (major == null) return;
        Integer credits = checkCredit(tokens[5]);
        if (credits == null) return;
        studentlist.add(new Resident(profile, major, credits));
        System.out.println(profile + "[Resident] added to the list.");
    }

    /** Add a NonResident student to the student list
     * @param tokens fname lname dob major credits */
    private void doAddNonResident(String[] tokens) {
        if (tokens.length < 6) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens[1], lname = tokens[2], inputDate = tokens[3];
        Date dob = checkDate(inputDate);
        if (dob == null) return;
        Profile profile = checkProfile(fname, lname, dob);
        if (profile == null) return;
        Major major = checkMajor(tokens[4]);
        if (major == null) return;
        Integer credits = checkCredit(tokens[5]);
        if (credits == null) return;
        studentlist.add(new NonResident(profile, major, credits));
        System.out.println(profile + "[Noresident] added to the list.");
    }

    /** Add a TriState student to the student list
     * @param tokens fname lname dob major credits state */
    private void doAddTriState(String[] tokens) {
        if (tokens.length < 7) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens[1], lname = tokens[2], inputDate = tokens[3];
        Date dob = checkDate(inputDate);
        if (dob == null) return;
        Profile profile = checkProfile(fname, lname, dob);
        if (profile == null) return;
        Major major = checkMajor(tokens[4]);
        if (major == null) return;
        Integer credits = checkCredit(tokens[5]);
        if (credits == null) return;
        String state = tokens[6].toUpperCase();
        if (!state.equals("NY") && !state.equals("CT")) {
            System.out.println(state + ": Invalid state code.");
            return;
        }
        studentlist.add(new TriState(profile, major, credits, state));
        System.out.println(profile + "[Tristate: " + state + "] added to the list.");
    }

    /** Add an International student to the student list
     * @param tokens fname lname dob major credits isStudyAbroad */
    private void doAddInternational(String[] tokens) {
        if (tokens.length < 7) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens[1], lname = tokens[2], inputDate = tokens[3];
        Date dob = checkDate(inputDate);
        if (dob == null) return;
        Profile profile = checkProfile(fname, lname, dob);
        if (profile == null) return;
        Major major = checkMajor(tokens[4]);
        if (major == null) return;
        Integer credits = checkCredit(tokens[5]);
        if (credits == null) return;
        boolean isStudyAbroad = Boolean.parseBoolean(tokens[6]);
        studentlist.add(new International(profile, major, credits, isStudyAbroad));
        String typeLabel = isStudyAbroad ? "[International study abroad]" : "[International]";
        System.out.println(profile + typeLabel + " added to the list.");
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
           System.out.println("INVALID: location " + roomNum + " does not exist.");
           return;
       }
       Instructor instructor = findInstructor(inputInstructor);
       if (instructor == null) {
           System.out.println("INVALID: faculty " + inputInstructor + " does not exist.");
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
            System.out.println(courseInput.toUpperCase()+" "+ time.getTime() + " does not exist.");
            return;
        }
        if(!section.isEmpty()){
            int x = section.getNumStudents();
            System.out.println(courseInput.toUpperCase()+ " "+ time + " cannot be removed ["+x+" student(s) enrolled]");
            return;
        }
        schedule.remove(section);
        System.out.println(courseInput.toUpperCase()+ " " + time + " removed.");
    }
    /** doEnroll, enroll student in a course
     * @param tokens input info*/
    private void doEnroll(String[] tokens) {
        if (tokens.length < 6) {
            System.out.println("INVALID: missing arguments for command");
            return;
        }
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
        if(date == null) return;
        Profile tempP = new Profile(fname, lname, date);
        Student student = studentlist.getStudent(tempP);
        if(student == null){
            System.out.println("INVALID: "+ tempP +" does not exist." );
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
            System.out.println("INVALID: " + courseInput.toUpperCase()+ " "+ time+ " does not exist.");
            return;
        }
        if (schedule.isEnrolledInCourse(student, course)) {   // MUST be public
            System.out.println("["+fname + " "+ lname + " "+ dob +"] already enrolled in "+ courseInput.toUpperCase() );
            return;
        }
        int currentCredits = schedule.getTotalCredits(student);
        int newTotal = currentCredits + section.getCourse().getCreditHours();
        if (newTotal  > 18) {
            System.out.println("Cannot enroll [" + fname + " "+ lname+ " "+ dob+ "]; now has "+ currentCredits + " will exceed credit limit of 18.");
            return;
        }
        if (student.getStanding().compareTo(course.getStanding()) < 0) {
            System.out.println("Prereq: "+ course.getStanding().getStanding() + " - [" + fname + " "+ lname+ " "+ dob+"] ["+student.getStanding().getStanding()+"]");
            return;
        }
        if (course.getMajor() != null && student.getMajor() != course.getMajor()) {
            System.out.println("Prereq: major only - [" + fname + " "+ lname + " "+ dob+ "] ["+ student.getMajor().getMajor() +"]");
            return;
        }
        if (schedule.hasTimeConflict(student, time)) {
            System.out.println("Time conflict: ["+ fname + " "+ lname+ " "+ dob+ "] enrolled in another class at period "+ time.getPeriodNum());
            return;
        }

        if (section.isFull()) {
            System.out.println("Cannot enroll ["+ fname+" "+lname+" "+dob+"], "+ course.getCourseNum()+" "+ time+" is full.");
            return;
        }
        section.enroll(student);
        System.out.println(tempP + " added to "+ course.getCourseNum()+ " "+ time.getTime());
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
        if (date == null) return;
        Profile profile = new Profile(fname, lname, date);
        Student student = studentlist.getStudent(profile);
        if(student == null){
            System.out.println("[" + fname +" "+ lname+" "+ inputDate+ "] does not exist.");
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
            System.out.println("INVALID: "+ inputCourse.toUpperCase()+ " "+ time+ " does not exist.");
            return;
            }
        if(!section.contains(student)){
            System.out.println("["+fname+ " " + lname+ " "+inputDate+"] is not enrolled in this section.");
            return;
        }
        section.drop(student);
        System.out.println("["+fname+" "+lname+" "+inputDate+"] dropped from "+ course.getCourseNum()+" "+time);
        
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
        Student student = studentlist.getStudent(profile);
        if(student == null){
            System.out.println("["+first+" "+last+" "+date + "] is not in the student list.");
            return;
        }
        if(schedule.isStudentEnrolled(student)){
            System.out.println("["+ first+" "+last+" "+date+ "] is currently enrolled in a section.");
            return;

        }
        studentlist.remove(student);
        System.out.println("["+first+" "+last+" "+date + "] removed from the list.");
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
        if (studentlist.getStudent(profile) != null){
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
            System.out.println("INVALID: "+ num +" is not an integer!");
            return null;
        }

        if (credits <= 0) {
            System.out.println("INVALID: "+ num + " credit is negative!");
            return null;
        }
        return credits;

    }


}
