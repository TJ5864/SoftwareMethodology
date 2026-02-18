package project2;
/** Schedule class created a schedule of all the sections for each course
 * @author mss444
 * @author tjt97*/
public class Schedule {

    private static final int CAPACITY = 4;
    private static final int NOT_FOUND = -1;
    private Section[] sections;
    private int numSections;

    /**
     * Constructor, created the section list with capacity 4
     */
    public Schedule(){
        sections = new Section[CAPACITY];
        numSections = 0;
    }

    /**
     * Grow method used to increase the size of our section list when we are at capacity,
     * increases size by 4
     */
    private void grow(){
        Section[] newArray = new Section[sections.length + CAPACITY];
        for( int i = 0; i<numSections; i++){
            newArray[i] = sections[i];
        }
        sections = newArray;

    }

    /**
     * Find method searches for a section in sections list and returns index
     * @param section the section we are looking for
     * @return index of the section if found, -1 if not found
     */
    private int find(Section section){
        for(int i = 0; i < numSections; i++){
            if(sections[i].equals(section)){
                return i;
            }
        }
        return NOT_FOUND;

    }

    /** Contains section uses find to return if the section is in the section list
     * @param section the section we want to check for
     * @return True if in list false otherwise
     */
    public boolean contains(Section section){
        return find(section) != NOT_FOUND;
    }

    /** Add section to sections list
     * @param section the section we want to add
     */
    public void add(Section section){
        if(contains(section)){
            return;
        }
        if (numSections == sections.length){
            grow();
        }
        sections[numSections] = section;
        numSections++;
    }

    /** Remove a section from list sections
     * @param section we want to remove
     */
    public void  remove(Section section){
        if(!contains(section)){
            return;

        }
        int index = find(section);

        Instructor instructor = sections[index].getInstructor();
        int period = sections[index].getTime().getPeriodNum();
        Classroom classroom = sections[index].getClassroom();

        instructor.freeAvailability(period); //free the time slot for the professor
        classroom.freeAvailability(period); // and the classroom

        sections[index] = sections[numSections -1];
        sections[numSections-1] = null;
        numSections--;
    }

    /**
     * enrolls the student to a section
     * @param section we will enroll the student in
     * @param student we want to enroll to the section
     */
    public void enroll(Section section, Student student){
        if (!(contains(section))) {
            return;
        }

        int sectionIdx = find(section);
        sections[sectionIdx].enroll(student);
    }

    /**
     * will remove a student from a section
     * @param section contains the target section the student wants to drop
     * @param student is who wants to drop the section
     */
    public void drop(Section section, Student student) {
        if (!(contains(section))){
            return;
        }

        int sectionIdx = find(section);
        sections[sectionIdx].drop(student);
    }
/** Prints the sections and its roster for the schedule */
    public void printSchedule() {
        for (int i = 0; i < numSections; i++) {
            sections[i].print();
            sections[i].printRoster();
        }
    }

    /**
     * InEnrolledInCourse method is a helper for out front end, we are checking if a student is enrolled in other
     * sections of same course
     * @param student the student we are looking for
     * @param  course the specific course we are going to check
     * @return boolean True if student is in another section False if otherwise
     */
    public boolean isEnrolledInCourse(Student student, Course course) {

        for (int i = 0; i < numSections; i++) {
            if (sections[i].contains(student) &&
                    sections[i].getCourse() == course) {
                return true;
            }
        }
        return false;
    }

    /** Is Student Enrolled, we use this to check if the student is enrolled in nay courses before we remove them
     * @param student the student we are looking for
     * @return True if student is in a course, False otherwise
     */
    public boolean isStudentEnrolled(Student student) {

        for (int i = 0; i < numSections; i++) {
            if (sections[i].contains(student)) {
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
     * @return index of course if found
     */
    public Section getSection(Course course, Time time) {

        Section temp = new Section(course, null, null, time);

        int index = find(temp);

        if (index == NOT_FOUND) {
            return null;
        }

        return sections[index];
    }

     /**
      * hasTimeConflict Used in front end to check if a student is in a section at the same time they
      * are trying to enroll in
      * @param student student whos schedule we are checking
      * @param  time the time we are checking for conflicts at
      * @return boolean True if there is a time conflict false otherwise.
      * @author tjt97
      */
    public boolean hasTimeConflict(Student student, Time time) {

        for (int i = 0; i < numSections; i++) {

            if (sections[i].contains(student) &&
                    sections[i].getTime() == time) {

                return true;
            }
        }

        return false;
    }

    /**
     * getTotalCredits, looks for what sections/courses a student is taking and calculated total credits
     * @param student the student whos credits we want to check
     * @return int total number of credits they are taking
     *
     * @author tjt97
     */
    public int getTotalCredits(Student student) {
        int total = 0;
        for (Section s: sections) {
            if (s!=null && s.contains(student)) {
                total += s.getCourse().getCreditHours();
            }
        }
        return total;
    }

    public boolean isEmpty() {
        return numSections == 0;
    }

    /**
     * prints sections out in order by campus and then building. (Insertion sort)
     */
    public void printByClassroom () {

        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }

        for (int i = 1; i < numSections; i++) {

            Section key = sections[i];

            String keyCampus = key.getClassroom().getCampus();
            String keyBuilding = key.getClassroom().getBuilding();

            int j = i - 1;

            while (j >= 0) {

                String campusJ = sections[j].getClassroom().getCampus();
                String buildingJ = sections[j].getClassroom().getBuilding();

                int campusCompare = campusJ.compareToIgnoreCase(keyCampus);

                boolean shouldShift =
                        (campusCompare > 0) ||
                                (campusCompare == 0 &&
                                        buildingJ.compareToIgnoreCase(keyBuilding) > 0);

                if (!shouldShift) break;

                sections[j + 1] = sections[j];
                j--;
            }

            sections[j + 1] = key;
        }

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

        for(int i = 1; i < numSections; i++) {
            Section key = sections[i];
            String keyCourseNum = sections[i].getCourse().getCourseNum();
            int keyPeriod = sections[i].getTime().getPeriodNum();

            int j = i - 1;

            while (j >= 0) {
                String courseNumJ = sections[j].getCourse().getCourseNum();
                int periodJ = sections[j].getTime().getPeriodNum();

                int courseCompare = courseNumJ.compareToIgnoreCase(keyCourseNum);

                boolean shouldShift = (courseCompare > 0) || (courseCompare == 0 && periodJ > keyPeriod);

                if (!shouldShift) {
                    break;
                }

                sections[j+1] = sections[j];
                j--;
            }

            sections[j+1] = key;
        }
        System.out.println("* List of sections ordered by course number, section time *");
        printSchedule();
        System.out.println("* end of list *");

    }




}


