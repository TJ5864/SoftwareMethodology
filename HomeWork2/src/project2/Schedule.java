package project2;

import util.List;
import util.Sort;

/** Schedule class created a schedule of all the sections for each course
 * @author mss444
 * @author tjt97*/
public class Schedule extends List<Section> {

    /** Remove a section from the schedule, also frees up instructor and classroom availability
     * @param section the section we want to remove
     */
    @Override
    public void remove(Section section) {
        if (!contains(section)) {
            return;
        }
        int index = indexOf(section);

        Instructor instructor = get(index).getInstructor();
        int period = get(index).getTime().getPeriodNum();
        Classroom classroom = get(index).getClassroom();

        instructor.freeAvailability(period);
        classroom.freeAvailability(period);

        super.remove(section);
    }

    /**
     * enrolls the student to a section
     * @param section we will enroll the student in
     * @param student we want to enroll to the section
     */
    public void enroll(Section section, Student student) {
        if (!contains(section)) {
            return;
        }
        int sectionIdx = indexOf(section);
        get(sectionIdx).enroll(student);
    }

    /**
     * will remove a student from a section
     * @param section contains the target section the student wants to drop
     * @param student is who wants to drop the section
     */
    public void drop(Section section, Student student) {
        if (!contains(section)) {
            return;
        }
        int sectionIdx = indexOf(section);
        get(sectionIdx).drop(student);
    }

    /** Prints the sections and its roster for the schedule */
    public void printSchedule() {
        for (int i = 0; i < size(); i++) {
            get(i).print();
            get(i).printRoster();
        }
    }

    /**
     * isEnrolledInCourse method is a helper for our front end, we are checking if a student is enrolled in other
     * sections of same course
     * @param student the student we are looking for
     * @param course the specific course we are going to check
     * @return boolean True if student is in another section False if otherwise
     */
    public boolean isEnrolledInCourse(Student student, Course course) {
        for (int i = 0; i < size(); i++) {
            if (get(i).contains(student) && get(i).getCourse() == course) {
                return true;
            }
        }
        return false;
    }

    /** Is Student Enrolled, we use this to check if the student is enrolled in any courses before we remove them
     * @param student the student we are looking for
     * @return True if student is in a course, False otherwise
     */
    public boolean isStudentEnrolled(Student student) {
        for (int i = 0; i < size(); i++) {
            if (get(i).contains(student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get section is a helper method that helps us find the section from other methods
     * used in Frontend
     * @param course course we are looking for
     * @param time the time the course is at
     * @return section if found, null otherwise
     */
    public Section getSection(Course course, Time time) {
        Section temp = new Section(course, null, null, time);
        int index = indexOf(temp);
        if (index == -1) {
            return null;
        }
        return get(index);
    }

    /**
     * hasTimeConflict Used in front end to check if a student is in a section at the same time they
     * are trying to enroll in
     * @param student student whose schedule we are checking
     * @param time the time we are checking for conflicts at
     * @return boolean True if there is a time conflict false otherwise.
     * @author tjt97
     */
    public boolean hasTimeConflict(Student student, Time time) {
        for (int i = 0; i < size(); i++) {
            if (get(i).contains(student) && get(i).getTime() == time) {
                return true;
            }
        }
        return false;
    }

    /**
     * getTotalCredits, looks for what sections/courses a student is taking and calculates total credits
     * @param student the student whose credits we want to check
     * @return int total number of credits they are taking
     * @author tjt97
     */
    public int getTotalCredits(Student student) {
        int total = 0;
        for (int i = 0; i < size(); i++) {
            if (get(i) != null && get(i).contains(student)) {
                total += get(i).getCourse().getCreditHours();
            }
        }
        return total;
    }

    /**
     * prints sections out in order by campus and then building. (Insertion sort)
     */
    public void printByClassroom() {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }

        Sort.sortByClassroom(this);

        System.out.println("* List of sections ordered by campus, building *");
        printSchedule();
        System.out.println("* end of list **");
    }

    /**
     * prints sections in order of coursenum and then period (also insertion sort)
     */
    public void printByCourse() {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }

        Sort.sortByCourse(this);

        System.out.println("* List of sections ordered by course number, section time *");
        printSchedule();
        System.out.println("* end of list *");
    }
}
