package project2;

/**
 * Creates the Section object containing information about the course, instructor, classroom, time, enrolled students,
 * and the number of students in the section
 * @author mss444
 */

public class Section {
    private Course course; // done
    private Instructor instructor; // done
    private Classroom classroom; // done
    private Time time; //can use other data types // done
    private StudentList roster;

    private static final int MAX_CAPACITY = 4;



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
        this.roster = new StudentList();
    }

    /**
     * Getter method for the Classroom Object
     * @return classroom Object containing information about location of the section
     */
    public Classroom getClassroom(){
        return classroom;
    }

    /**
     * Getter method for the Course object
     * @return course Object containing information about the course, its credit hours, and its prereqs
     */
    public Course getCourse(){
        return course;
    }

    /**
     * Getter method for the Time object
     * @return time Object containg the period/ 80 min time slot that the section occupies
     */
    public Time getTime(){
        return time;
    }

    /**
     * Getter method for the Instructor object
     * @return instructor Object containing the instructor assigned to the section
     */
    public Instructor getInstructor(){
        return instructor;
    }

    /**
     * Getter method for number of students enrolled in the section
     * @return numStudents int of number of students enrolled in this section
     */
    public int getNumStudents(){
        return roster.size();
    }

    /**
     * enrolls a student to the section, checking for roster availaiblity and enrollment status prior
     * @param student Object containing information about the student like name and major
     */
    public void enroll(Student student) {
        if (isFull()) {
           return;
        }
        if (roster.contains(student)){
            return;
        }
        roster.add(student);
    }

    /**
     * unenroll a student from a section, checking to see if roster is empty or if student is enrolled to section prior.
     * @param student Object containing information about student such as name and major
     */
    public void drop(Student student) {
        if (roster.isEmpty()) {
            return;
        }

        roster.remove(student);
    }


    /**
     * Method to check if a section has a student enrolled
     * @param student object containing information about a student
     * @return true if a student is registered to a section, else false
     */
    public boolean contains(Student student) {
        return roster.contains(student);
    }

    /**
     * Checks to see if a section is full or not
     * @return True if  the number of students is equal to the max capacity (i.e. 4) or False if numStudents less than MAXCAPACITY
     */
    public boolean isFull() {
        return roster.size() == MAX_CAPACITY; //will return True of the number of students is equal to the max capacity (i.e. 4 Students)
    }
    /** Check to see if section is Empty
     * @return True if there are 0 students in the class, False if otherwise*/
    public boolean isEmpty(){
        return roster.isEmpty();
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

    /**
     * method necessary to print the roster out for a section.
     */
    public void printRoster() {
        if (isEmpty()) {
            System.out.println("\t**No students enrolled**");
            return;
        }
        System.out.println("\t**Roster**");

        for (int i = 0; i < roster.size(); i++) {
            System.out.println("\t" + roster.get(i).getProfile());
        }


    }

    /**
     * main method testing functionality of methods
     * @param args
     */
//    public static void main(String[] args) {
//        Classroom classroom1 = Classroom.HIL114;
//        //System.out.println(classroom1.getClassroomNum());
//        Instructor instructor1 = Instructor.LIM;
////        System.out.println(instructor1.getName());
////        System.out.println(instructor1.checkAvailability(1));
////        instructor1.fillAvailability(1);
////        instructor1.fillAvailability(1);
////        System.out.println(instructor1.checkAvailability(1));
////        instructor1.freeAvailability(1);
////        instructor1.freeAvailability(1);
////        System.out.println(instructor1.checkAvailability(1));
//        Course course1 = Course.CS200;
////        System.out.println(course1.getCourseNum() + course1.getCreditHours() + course1.getStanding() + course1.getMajor());
//        Time time1 = Time.PERIOD1;
////        System.out.println(time1.getHour() + ":" + time1.getMinute() + " " + time1.getMeridiem() );
//
//
//        Date dob1 = new Date(12, 7, 2003);
//        Profile profile1 = new Profile("Mostafa", "Shalan", dob1);
////        System.out.println(profile1.toString());
//        Major major1 = Major.CS;
////        System.out.println(major1.toString());
//        Student student1 = new Student(profile1, major1, 114);
//
//
//
//        Section section1 = new Section(course1, instructor1,classroom1,time1);
////        section1.print();
//
//
//        Course course2 = Course.CS200;
//        Instructor instructor2 = Instructor.PATEL;
//        Classroom classroom2 = Classroom.ARC103;
//        Time time2 = Time.PERIOD1;
//
//        Section section2 = new Section(course2, instructor2, classroom2, time2);
////        section2.print();
//
////        System.out.println(section1.equals(section1)); // should return true because it is itself
////        System.out.println(section1.equals(course2)); // should return false because not instance of section object
////        System.out.println(section1.equals(section2)); // should return true because courseNum and periodId are same
//
//        section1.enroll(student1);
////        System.out.println(section1.isFull());
////        for (int i = 0; i < 4; i++) {
////            System.out.println(section1.roster[i]);
////        }
//        section1.enroll(student1); // should prohibit from allowing student to enroll to the same section
//        System.out.println(section1.contains(student1));
//
//
//    }
}