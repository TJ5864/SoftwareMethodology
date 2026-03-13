package project2;

import util.List;
import util.Sort;

/** Create a resizable list of Student objects, manages students and the class section they are in
 * @author tjt97   */
public class StudentList extends List<Student> {

    /**
     * Returns the student list sorted by last name, first name, and DOB as a formatted string.
     * @return String listing all students in order, or a message if the list is empty
     */
    public String print(){
        if (isEmpty()) {
            return "Student list is empty!";
        }
        Sort.sortStudents(this);
        return "* Student list ordered by last, first name, DOB *\n" +
                printStudents() +
                "* end of list **";
    }

    /**
     * Returns each student in the list as a formatted string.
     * @return String with one student per line using their toString representation
     */
    public String printStudents() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(get(i)).append("\n");
        }
        return sb.toString();
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

    /**
     * Returns students who meet the 120-credit graduation requirement, ordered by major.
     * @param schedule the schedule used to calculate total credits enrolled
     * @return String listing eligible graduating students, or a message if none qualify
     */
    public String printGraduatingStudents(Schedule schedule) {
        StudentList graduatingStudents = new StudentList();

        for (int i = 0; i < size(); i++) {
            if (get(i).getCreditCompleted() + schedule.getTotalCredits(get(i)) >= 120){
                graduatingStudents.add(get(i));
            }
        }

        if (graduatingStudents.isEmpty()){
            return "No students eligible for graduation.";
        }

        Sort.sortByMajor(graduatingStudents);

        StringBuilder sb = new StringBuilder("* List of students eligible for graduation, ordered by major *\n");
        for (int i = 0; i < graduatingStudents.size(); i++) {
            Student s = graduatingStudents.get(i);
            sb.append(s.getProfile().toString()).append("[").append(s.getMajor().toString()).append("]\n");
        }
        sb.append("* end of list *");
        return sb.toString();
    }
    /**
     * Returns a tuition report ordered by student profile.
     * Lists each enrolled section then the total tuition due.
     * International non-study-abroad students with less than 12 credits receive an error message.
     * @param schedule the schedule used to look up each student's enrolled sections and credits
     * @return String containing the full tuition report, or a message if no students exist
     */
    public String printTuition(Schedule schedule) {
        if (isEmpty()) {
            return "Student list is empty!";
        }
        Sort.sortStudents(this);
        StringBuilder sb = new StringBuilder("* Tuition dues ordered by student. *\n");
        for (int i = 0; i < size(); i++) {
            Student s = get(i);
            sb.append(s.getProfile()).append(getTypeLabel(s)).append("\n");

            List<Section> sections = schedule.getSectionsFor(s);
            for (int j = 0; j < sections.size(); j++) {
                Section sec = sections.get(j);
                sb.append("\t\t").append(sec.getCourse().getCourseNum())
                        .append("[").append(sec.getTime().getTime())
                        .append("] [credit: ").append(sec.getCourse().getCreditHours()).append("]\n");
            }
            int totalCredits = schedule.getTotalCredits(s);
            if (s instanceof International && !((International) s).isStudyAbroad() && totalCredits < 12) {
                sb.append("\t\t**International student must enroll at least 12 credits.\n");
            } else if (totalCredits == 0) {
                sb.append("\t\t**not enrolled.\n");
            } else {
                double tuition = s.tuition(totalCredits);
                sb.append("\t\t**Total credits enrolled: ").append(totalCredits)
                        .append(" [tuition due: $").append(String.format("%,.2f", tuition)).append("]\n");
            }
        }
        sb.append("* end of list *");
        return sb.toString();
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
