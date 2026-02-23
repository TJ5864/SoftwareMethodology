package project2;

import util.Date;
import util.List;

/** Create a resizable list of Student objects, manages students and the class section they are in
 * @author tjt97   */
public class StudentList extends List<Student> {



    /**Sorts the student for the printing method
     * sorts students in Alphabetical order, uses CompareTo function*/
    private void sortStudent(){
        for(int i = 0; i < size(); i++){
            int min = i;

            for(int j = i+1; j < size(); j++){
                if(get(j).compareTo(get(min)) < 0){
                    min = j;
                } else if(get(j).compareTo(get(min)) == 0) {
                    Date minAge = get(min).getProfile().getDob();
                    Date jAge = get(j).getProfile().getDob();

                    if(jAge.compareTo(minAge) < 0) {
                        min = j;
                    }
                }
            }
            Student temp = get(i);
            set(i, get(min));
            set(min, temp);
        }
    }





    /** Print out the student name lastname/firstname , then dob
     * */
    public void print(){
        if (isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        sortStudent();
        System.out.println("* Student list ordered by last, first name, DOB *");
        for(int i = 0; i < size(); i++){
            System.out.println(get(i));
        }
        System.out.println("* end of list **");

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
