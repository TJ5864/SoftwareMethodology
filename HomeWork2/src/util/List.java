package util;
import java.util.Iterator;

public class List<E> implements Iterable<E> {
    private static final int CAPACITY = 4;
    private E[] objects;
    private int size;
    private final int NOT_FOUND = -1;


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

    private void grow() {
        @SuppressWarnings("unchecked")
        E[] newList = (E[]) new Object[objects.length + CAPACITY];

        for (int i = 0; i < size; i++) {
            newList[i] = objects[i];
        }

        objects = newList;
    }

    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    public void add(E e) {
        if (size == objects.length) {
            grow();
        }

        objects[size] = e;
        size++;
    }

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

    public boolean isEmpty() {
        return size == 0;
    }


    //what is this method supposed to do? the directions say nothing about it lol
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

    public int indexOf(E e){
        return find(e);
    }



    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next(){ return objects[current++];
        }
    }

}
