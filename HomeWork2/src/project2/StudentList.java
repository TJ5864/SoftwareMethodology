package project2;

import util.Date;
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

}
