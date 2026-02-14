package project1;
/** Create a resizable list of Student objects, manages students and the class section they are in
 * @author tjt97   */

public class StudentList {
    private static final int NOT_FOUND = -1;
    private static final int  CAPACITY = 4;
    private Student[] list;
    private int size;

    /** Create an student list of the current size*/
    public StudentList(){
        list = new Student[CAPACITY];
        size = 0;
    }
    /** Find the index of the student in the list
     * @param student the student we are looking for
     * @return index or NOT_FOUND*/
    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(list[i].equals(student)){
                return i;
            }
        }
        return NOT_FOUND;

    }
    /** Increase the array capacity by 4*/
    private void  grow(){
        Student[] newList = new Student[list.length + CAPACITY];

        for(int i = 0; i < size; i++){
            newList[i] = list[i];
        }
        list = newList;
    }

    /** Add student to the end of the list
     * @param student the student we are adding*/
    public void add(Student student){
        if (size == list.length){
            grow();
        }
        list[size] = student;
        size++;

    }

    /** Remove the last student in the list
     * replaces with last element
     * @param student student we are removing*/
    public void remove(Student student){
        int index = find(student);
        if(index == NOT_FOUND){
            return;
        }
        list[index] = list[size-1];
        list[size-1] = null;
        size--;

    }

    /**
     * check if student is in the list
     * @param student student we are looking for
     * @return true if student in list false if otherwise*/
    public boolean contains(Student student){
        return find(student) != NOT_FOUND;
    }

    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                return false;
            }
        }
        return true;
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
        for(int i= 0; i< size; i++){
            System.out.println(list[i]);
        }
        System.out.println("* end of list **");

    }

    /**Sorts the student for the printing method
     * uses CompareTo function*/
    private void sortStudent(){
        for(int i = 0; i<size; i++){
            int min = i;

            for(int j = i+1; j < size; j++){
                if(list[j].compareTo(list[min])<0){
                    min = j;
                }
            }
            Student temp = list[i];
            list[i] = list[min];
            list[min] = temp;
        }

    }

}
