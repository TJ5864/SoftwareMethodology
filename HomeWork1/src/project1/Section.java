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

    private final int MAXCAPACITY = 4;

    public Section(Course course, Instructor instructor, Classroom classroom, Time time) {
        this.course = course;
        this.instructor = instructor;
        this.classroom = classroom;
        this.Time = time;
        this.roster = new Student[MAXCAPACITY];
        this.numStudents = 0;
    }


    public void enroll(Student student) {
        if (isFull()) {
            System.out.println("Section capacity is full. Cancelling Operation...");
        }
        else if (contains(student)) {
            System.out.println("Student already enrolled in this section. Cancelling Operation...");
        }
        else {
            for (int i = 0; i < MAXCAPACITY; i ++) {
                if(roster[i] == null) {
                    roster[i] = student;
                    numStudents = numStudents + 1;
                    System.out.println("Student successfully enrolled to this section.");
                    break;
                }
            }
        }
    } //add student to roster

    public void drop(Student student) {
        if (numStudents == 0) {
            System.out.println("Section roster is empty. Cancelling Operation...");
        }
        else if !(contains(student)) {
            System.out.println("Student not found in roster. Cancelling Operation....");
        }
        else {
            for (int i = 0; i < MAXCAPACITY; i++) {
                if (roster[i].equals(student)) {
                    roster[i] = null;
                    numStudents = numStudents - 1;
                    System.out.println("Student successfully dropped from section.")
                }
            }
        }
    }//remove student from roster

    /**
     *
     * @param student object containing information about a student
     * @return true if a student is registered to a section  else false
     */
    public boolean contains(Student student) {

        for (int i = 0; i < MAXCAPACITY; i++) {
            if (roster[i].equals(student)) {
                return true; //loop will terminate if the sutdent is found.
            }
        }
        return false //if it goes through  the entire array and doesn't find the student, it will just return false
    }

    /**
     * Checks to see if a section is full or not
     * @return True if  the number of students is equal to the max capacity (i.e. 4) or False if numStudents < MAXCAPACITY
     */
    public boolean isFull() {
        return numStudents == MAXCAPACITY; //will return True of the number of students is equal to the max capacity (i.e. 4 Students)
    }

    public void print() {

    }

    @Override
    public String toString() {
        return "[" + course.getCourseNum() + " " + time.getHour() + ":" + time.getMinute() + "] [" +instructor.getName() + "] [" + classroom.getClassroomNum() + ", " + classroom.getBuilding() + ", " + classroom.getCampus() + "]";
    }

    @Override
    public boolean equals(Section section2) {
        if (this == section2){
            return true;
        }
        if !(section2 instanceof Section) {
            return false;
        }
        Section section = (Section) section2;
        return classroom.equals(section.classroom) && time.equals(section.time) //need to write equals functions for classroom and time classes
    }

    public static void main(String[] args) {

    }
}