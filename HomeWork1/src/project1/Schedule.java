package project1;

public class Schedule {

    private static final int CAPACITY = 4;
    private static final int NOT_FOUND = -1;
    private Section[] sections;
    private int numSections;

    /** Constructor, created the section list with capacity 4 */
    public Schedule(){
        sections = new Section[CAPACITY];
        numSections = 0;
    }
/** Grow method used to increase the size of our section list when we are at capacity,
 * increases size by 4 */
    private void grow(){
        Section[] newArray = new Section[sections.length + CAPACITY];
        for( int i = 0; i<numSections; i++){
            newArray[i] = sections[i];
        }
        sections = newArray;

    }

/** Find method searches for a section in sections list and returns index
 * @param section the section we are looking for
 * @return index of the section if found, -1 if not found*/

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
     * @return True if in list false otherwise */
    public boolean contains(Section section){
        return find(section) != NOT_FOUND;
    }

/** Add section to sections list
 * @param section the section we want to add*/
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
     * @param  section we want to remove */

    public void  remove(Section section){
        if(!contains(section)){
            return;

        }
        int index = find(section);
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

        sectionIdx = find(section);
        sections[sectionIdx].drop(student);
    }

    public void printByClassroom () {

    }




}


