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
        for(int i = 0; i<size; i++){
            if(objects[i].equals(e)){
                return i;
            }
        }
        return NOT_FOUND;
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
        public E next() {
            return objects[current++];
        }
    }

}
