package project1;

/**
 * @author mss444
 * Creates the Section objects
 */

public class Section {
    private Course course; // in progress
    private Instructor instructor; // not complete
    private Classroom classroom; // not complete
    private Time time; //can use other data types // not complete
    private Student[] roster; // not complete
    private int numStudents;  // not complete


    public void enroll(Student student) {} //add student to roster
    public void drop(Student student) {}//remove student from roster
    public boolean contains(Student student) { }
    public boolean isFull() { }
    public void print() { }