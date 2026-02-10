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
    } //add student to roster

    public void drop(Student student) {
    }//remove student from roster

    public boolean contains(Student student) {
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

}