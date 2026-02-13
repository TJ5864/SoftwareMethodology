package project1;
import java.time.Period;
import java.util.Scanner;
import java.util.Calendar;

public class FrontEnd {

    private StudentList studentlist;
    private Schedule schedule;

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

            switch (command){
                case "A":
                    doAdd(tokens);
                    break;
                case "R":
                    doRemove(tokens);
                    break;
                case "O":
                    doAddSection(tokens);
                case "C":
                case "E":
                    doEnroll(tokens);
                case "D":
                case "PS":
                    studentlist.print();
                    break;
                case "PL":
                case "PC":
                case "Q":
                    System.out.println("Registration System is terminated.");
                    return;

                default:
                    System.out.println("Invalid Command!");
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
        if(profile == null) return;

        Major major = checkMajor(majorT);
        if(major == null) return;

        Integer credits = checkCredit(creditT);
        if(credits == null) return;

        Student s = new Student(profile, major, credits);
        studentlist.add(s);



    }

    private void doAddSection(String[] tokens){
       String courseNum = tokens[1];
       String inputPeriod = tokens[2];
       String inputInstructor = tokens[3];
       String roomNum = tokens[4];

       Course course;
       try{
           course = Course.valueOf(courseNum.toUpperCase());
       }catch (IllegalArgumentException e){
           System.out.println("Invalid Course Number.");
           return;
       }
       int input = Integer.parseInt(inputPeriod);
       Time time = findTimePeriod(input);
       if(time == null){
           System.out.println("Invalid period.");
           return;
       }

    }

    private void doEnroll(String[] tokens){
        String fname = tokens[1];
        String lname = tokens[2];
        String dob = tokens[3];
        String courseInput = tokens[4];
        String periodInput = tokens[5];
        int period = Integer.parseInt(periodInput);


        Date date = checkDate(dob);
        Profile tempP = new Profile(fname, lname, date);

        Student temp = new Student(tempP,null,  0);




    }


/** find time helper method, used to return time when given period
 * @param input integer period that was input
 * @return Time the time the period occurs */
    private Time findTimePeriod(int input){
        for(Time t : Time.values()){
            if(t.getPeriodNum()== input){
                return t;
            }
        }
        return null;
    }
    /** Remove a student based on input commands
     * @param tokens input list of features*/
    private void doRemove(String[] tokens){
        String first = tokens[0];
        String last = tokens[1];
        String date = tokens[2];

        Date dob = checkDate(date);
        if(dob == null) return;

        Profile profile = new Profile(first,last,dob);
        Student student = new Student(profile, null, 0);

        if(!studentlist.contains(student)){
            System.out.println("Student does not exist.");
            return;
        }

        //check if student is in any section before we remove him

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
            System.out.println("Date of birth invalid");
            return null;
        }
        Calendar dobcalendar = dob.toCalendar();
        Calendar today = Calendar.getInstance();
        if(!dobcalendar.before(today)){
            System.out.println("Date of birth invalid: "+ dob);
            return null;
        }
        Calendar sixteen = Calendar.getInstance();
        sixteen.add(Calendar.YEAR, -16);
        if(dobcalendar.after(sixteen)){
            System.out.println("Student must be at least 16 years old!");
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
            System.out.println("Student already exists.");
            return null;
        }
        return profile;

    }
    /** Check if the input major exsists
     * @param majorT the input major */
    private Major checkMajor(String majorT){
        try {
            return Major.valueOf(majorT.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Major invalid: " + majorT);
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
            System.out.println("Credits completed invalid");
            return null;
        }

        if (credits <= 0) {
            System.out.println("Credits completed invalid: ");
            return null;
        }
        return credits;

    }


}
