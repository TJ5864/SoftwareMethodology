package project2;

import util.List;
import util.Sort;

/** Create a resizable list of Student objects, manages students and the class section they are in
 * @author tjt97   */
public class StudentList extends List<Student> {

    /** Print out the student name lastname/firstname , then dob
     * */
    public void print(){
        if (isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        Sort.sortStudents(this);

        System.out.println("* Student list ordered by last, first name, DOB *");
        printStudents();
        System.out.println("* end of list **");
    }

    /** Prints each student in the list using their toString representation */
    public void printStudents() {
        for (int i = 0; i < size(); i++) {
            System.out.println(get(i));
        }
    }


/** Get student method, used to enroll, check students profiles and return matching student based on profile
 * @param  p profile we are looking for
 * @return Student the student object with matching profile*/
    public Student getStudent(Profile p){
        for(int i = 0; i < size(); i++){
            if(get(i).getProfile().equals(p)){
                return get(i);
            }
        }
        return null;

    }

    /** Prints students who meet the 120-credit graduation requirement, ordered by major
     * @param schedule the schedule used to calculate total credits enrolled */
    public void printGraduatingStudents(Schedule schedule) {
        StudentList graduatingStudents = new StudentList();

        for (int i = 0; i < size(); i++) {
            if (get(i).getCreditCompleted() + schedule.getTotalCredits(get(i)) >= 120){
                graduatingStudents.add(get(i));
            }
        }

        if (graduatingStudents.isEmpty()){
            System.out.println("Schedule is empty!");
            return;
        }

        Sort.sortByMajor(graduatingStudents);

        System.out.println("* List of students eligible for graduation, ordered by major *");
        for( int i = 0; i < graduatingStudents.size(); i++) {
            Student s = graduatingStudents.get(i);
            System.out.println(s.getProfile().toString() + "[" + s.getMajor().toString() + "]" );
        }
        System.out.println("* end of list *");
    }
    /** Print tuition report for the PT command, ordered by student profile.
     * Lists each enrolled section then prints the total tuition due.
     * International non-study-abroad students with less than 12 credits receive an error message.
     * @param schedule the schedule used to look up each students enrolled sections and credits */
    public void printTuition(Schedule schedule) {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }
        Sort.sortStudents(this);
        System.out.println("* Tuition dues ordered by student. *");
        for (int i = 0; i < size(); i++) {
            Student s = get(i);
            System.out.println(s.getProfile() + getTypeLabel(s));

            List<Section> sections = schedule.getSectionsFor(s);
            for (int j = 0; j < sections.size(); j++) {
                Section sec = sections.get(j);
                System.out.println("\t\t" + sec.getCourse().getCourseNum() +
                        "[" + sec.getTime().getTime() + "] [credit: " +
                        sec.getCourse().getCreditHours() + "]");
            }
            int totalCredits = schedule.getTotalCredits(s);
            if (s instanceof International && !((International) s).isStudyAbroad() && totalCredits < 12) {
                System.out.println("\t\t**International student must enroll at least 12 credits.");
            } else if (totalCredits == 0) {
                System.out.println("\t\t**not enrolled.");
            } else {
                double tuition = s.tuition(totalCredits);
                System.out.println("\t\t**Total credits enrolled: " + totalCredits +
                        " [tuition due: $" + String.format("%,.2f", tuition) + "]");
            }
        }
        System.out.println("* end of list *");
    }

    /** Returns the type label string for a student based on their subclass, used in the PT report.
     * @param s the student whose type label we want
     * @return String label such as [Resident], [Noresident], [Tristate: NY], [International], etc. */
    private String getTypeLabel(Student s) {
        if (s instanceof International) {
            if (((International) s).isStudyAbroad()) return "[International study abroad]";
            return "[International]";
        }
        if (s instanceof TriState) return "[Tristate: " + ((TriState) s).getState() + "]";
        if (s instanceof NonResident) return "[Noresident]";
        return "[Resident]";
    }

}
