package project1;

/**
 * @author mss444
 * Creates the Section objects
 */

public class Section {
    private Course course; // done
    private Instructor instructor; // done
    private Classroom classroom; // done
    private Time time; //can use other data types // done
    private Student[] roster; // not complete
    private int numStudents;  // not complete

    private static final int MAX_CAPACITY = 4;
    private static final int EMPTY = 0;



    /**
     * Section constructor
     * @param course enum object tracking courseNum, credits, standing, and major req
     * @param instructor enum object containing instructor name and availability
     * @param classroom enum object containing classroomNum, building, and campus
     * @param time enum object tracking the time periods throughout the daily schedule
     */
    Section(Course course, Instructor instructor, Classroom classroom, Time time) {
        this.course = course;
        this.instructor = instructor;
        this.classroom = classroom;
        this.time = time;
        this.roster = new Student[MAX_CAPACITY];
        this.numStudents = 0;
    }

    public Classroom getClassroom(){
        return classroom;
    }
    public Course getCourse(){
        return course;
    }
    public Time getTime(){
        return time;
    }
    public Instructor getInstructor(){
        return instructor;
    }
    public int getNumStudents(){
        return numStudents;
    }


    public void enroll(Student student) {
        if (isFull()) {
            //System.out.println("Section capacity is full. Cancelling Operation...");
        }
        else if (numStudents == 0) {
            roster[0] = student;
            numStudents = numStudents + 1;
            //System.out.println(numStudents);
        }
        else if (contains(student)) {
            //System.out.println("Student already enrolled in this section. Cancelling Operation...");
        }
        else {
            for (int i = 0; i < MAX_CAPACITY; i ++) {
                if(roster[i] == null) {
                    roster[i] = student;
                    numStudents = numStudents + 1;
                    //System.out.println((numStudents));
                    //System.out.println("Student successfully enrolled to this section.");
                    break;
                }
            }
        }
    } //add student to roster

    public void drop(Student student) {
        if (numStudents == 0) {
            //System.out.println("Section roster is empty. Cancelling Operation...");
        }
        else if (!(contains(student))) {
            //System.out.println("Student not found in roster. Cancelling Operation....");
        }
        else {
            for (int i = 0; i < MAX_CAPACITY; i++) {
                if (roster[i] != null && roster[i].equals(student)) {
                    roster[i] = null;
                    numStudents = numStudents - 1;
                    //System.out.println("Student successfully dropped from section.");
                }
            }
        }
        }


    /**
     *
     * @param student object containing information about a student
     * @return true if a student is registered to a section  else false
     */
    public boolean contains(Student student) {

        for (int i = 0; i < numStudents; i++) {
            if (roster[i]!= null && roster[i].equals(student)) {
                return true; //loop will terminate if the student is found.
            }
        }
        return false; //if it goes through  the entire array and doesn't find the student, it will just return false
    }

    /**
     * Checks to see if a section is full or not
     * @return True if  the number of students is equal to the max capacity (i.e. 4) or False if numStudents < MAXCAPACITY
     */
    public boolean isFull() {
        return numStudents == MAX_CAPACITY; //will return True of the number of students is equal to the max capacity (i.e. 4 Students)
    }
    /** Check to see if section is Empty
     * @return True if there are 0 students in the class, False if otherwise*/
    public boolean isEmpty(){
        return numStudents == EMPTY;
    }

    /**
     * prints out the data string retrieved from the toString() method
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * creates string containing info about course num, time, instructor, and c
     * @return String containing information about the section
     */
    @Override
    public String toString() {
        return "[" + course.getCourseNum() + " " + time.getHour() + ":" + time.getMinute() + "] [" +instructor.getName() + "] [" + classroom.getClassroomNum() + ", " + classroom.getBuilding() + ", " + classroom.getCampus() + "]";
    }

    /**
     * checks to see if two sections are the same by checking if the course id and the timings are the same
     * @param section2 section object to compare current section object to
     * @return true if two objects are the same (courseid and periods are same); false otherwise
     */
    @Override
    public boolean equals(Object section2) {
        if (this == section2){
            return true;
        }
        if (!(section2 instanceof Section)) {
            return false;
        }
        Section section = (Section) section2;
        String classroomNum = section.course.getCourseNum();
//        System.out.println(classroomNum);
        int periodID = section.time.getPeriodNum();
//        System.out.println(periodID);
//        System.out.println(course.getCourseNum());
        return time.getPeriodNum() == periodID && course.getCourseNum().equals(classroomNum);
    }

    public void printRoster() {
        if (isEmpty()) {
            System.out.println("\t**No students enrolled**");
            return;
        }
        System.out.println("\t**Roster**");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\t" + roster[i].getProfile());
        }


    }

/** Used to test the method*/
    public static void main(String[] args) {
        Classroom classroom1 = Classroom.HIL114;
        //System.out.println(classroom1.getClassroomNum());
        Instructor instructor1 = Instructor.LIM;
//        System.out.println(instructor1.getName());
//        System.out.println(instructor1.checkAvailability(1));
//        instructor1.fillAvailability(1);
//        instructor1.fillAvailability(1);
//        System.out.println(instructor1.checkAvailability(1));
//        instructor1.freeAvailability(1);
//        instructor1.freeAvailability(1);
//        System.out.println(instructor1.checkAvailability(1));
        Course course1 = Course.CS200;
//        System.out.println(course1.getCourseNum() + course1.getCreditHours() + course1.getStanding() + course1.getMajor());
        Time time1 = Time.PERIOD1;
//        System.out.println(time1.getHour() + ":" + time1.getMinute() + " " + time1.getMeridiem() );


        Date dob1 = new Date(12, 7, 2003);
        Profile profile1 = new Profile("Mostafa", "Shalan", dob1);
//        System.out.println(profile1.toString());
        Major major1 = Major.CS;
//        System.out.println(major1.toString());
        Student student1 = new Student(profile1, major1, 114);



        Section section1 = new Section(course1, instructor1,classroom1,time1);
//        section1.print();


        Course course2 = Course.CS200;
        Instructor instructor2 = Instructor.PATEL;
        Classroom classroom2 = Classroom.ARC103;
        Time time2 = Time.PERIOD1;

        Section section2 = new Section(course2, instructor2, classroom2, time2);
//        section2.print();

//        System.out.println(section1.equals(section1)); // should return true because it is itself
//        System.out.println(section1.equals(course2)); // should return false because not instance of section object
//        System.out.println(section1.equals(section2)); // should return true because courseNum and periodId are same

        section1.enroll(student1);
//        System.out.println(section1.isFull());
//        for (int i = 0; i < 4; i++) {
//            System.out.println(section1.roster[i]);
//        }
        section1.enroll(student1); // should prohibit from allowing student to enroll to the same section
        System.out.println(section1.contains(student1));


    }
}