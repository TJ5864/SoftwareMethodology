package util;
import java.util.Iterator;

/** Generic resizable array-backed list, does not use ArrayList or Java Collections
 * @param <E> the type of elements stored in this list
 * @author tjt97
 * @author mss444*/
public class List<E> implements Iterable<E> {
    private static final int CAPACITY = 4;
    private E[] objects;
    private int size;
    private final int NOT_FOUND = -1;


    /** Constructs an empty list with initial capacity */
    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[CAPACITY];
        size = 0;

    }
/** Find is the same as find in student list, looks for object in list E
 * @param e object we are looking for
 * @return position of e in the list or -1 if not in the list*/
    private int find(E e){
        if (e == null) {
            return NOT_FOUND;
        }

        for(int i = 0; i<size; i++){
            if(objects[i] != null && objects[i].equals(e)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /** Increases the capacity of the backing array by CAPACITY when the list is full */
    private void grow() {
        @SuppressWarnings("unchecked")
        E[] newList = (E[]) new Object[objects.length + CAPACITY];

        for (int i = 0; i < size; i++) {
            newList[i] = objects[i];
        }

        objects = newList;
    }

    /** Checks whether the list contains the given element
     * @param e the element to search for
     * @return true if found, false otherwise */
    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    /** Adds the given element to the end of the list, growing the list if needed
     * @param e the element to add */
    public void add(E e) {
        if (size == objects.length) {
            grow();
        }

        objects[size] = e;
        size++;
    }

    /** Removes the given element from the list and shifts remaining elements left
     * @param e the element to remove */
    public void remove(E e) {
        int targetIndex = find(e);
        if (targetIndex == NOT_FOUND) {
            return;
        }

        for (int i = targetIndex; i < size - 1; i++) {
            objects[i] = objects[i+1];
        }

        objects[size - 1] = null;
        size--;
    }

    /** Checks whether the list has no elements
     * @return true if size is 0, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }


    /** Returns the number of elements currently in the list
     * @return the current size of the list */
    public int size() {
        return size;
    }
/** Get method returns the object at the given index
 * @param index the index of the object we want
 * @return the object located at the index*/
    public E get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return objects[index];
    }

    /** Set methods sets and object e to a given index in list
     * @param index the index we want to replace
     * @param e the object we want to put at the given index*/
    public void set(int index, E e){
        if(index < 0 || index >= size){
            return;
        }
        objects[index] = e;

    }

    /** Returns the index of the given element in the list, or -1 if not found
     * @param e the element to search for
     * @return index of the element or -1 if not present */
    public int indexOf(E e){
        return find(e);
    }



    /** Returns an iterator over the elements in this list
     * @return an Iterator for this list */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /** Inner class implementing Iterator for traversing the list */
    private class ListIterator implements Iterator<E> {
        private int current = 0;

        /** Returns true if there are more elements to iterate over
         * @return true if next element exists, false otherwise */
        @Override
        public boolean hasNext() {
            return current < size;
        }

        /** Returns the next element in the list and advances the cursor
         * @return the next element */
        @Override
        public E next(){ return objects[current++];
        }
    }

}
