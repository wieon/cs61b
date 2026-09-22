import edu.princeton.cs.algs4.ST;
import org.apache.commons.collections.iterators.ArrayIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public int length;
    private int upSizeFactor;
    private int downSizeFactor;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        length = 8;
        upSizeFactor = 2;
        downSizeFactor = 2;
    }

    /* Move the nextFirst pointer. */
    private void nextFirstHelper() {
        nextFirst = (nextFirst - 1 + length) % length;
    }

    /* Move the nextLast pointer. */
    private void nextLastHelper() {
        nextLast = (++nextLast) % length;
    }

    /* Create a new array which doubles the length, and put elements at first half. */
    private void upSizeHelper(int upSizeFactor) {
        T[] newItems = (T[]) new Object[length * upSizeFactor];
        for(int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        nextLast = size;
        length *= 2;
        nextFirst = length - 1;
        items = newItems;
    }

    private void downSizeHelper(int downSizeFactor) {
        T[] newItems = (T[]) new Object[length / upSizeFactor];
        for (int i = 0; i < size; i ++) {
            newItems[i] = get(i);
        }
        nextLast = 0;
        length /= downSizeFactor;
        nextFirst = length - 1;
        items = newItems;
    }


    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (size == length) {
            upSizeHelper(upSizeFactor);
        }
        items[nextFirst] = x;
        nextFirstHelper();
        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (size == length) {
            upSizeHelper(downSizeFactor);
        }
        items[nextLast] = x;
        nextLastHelper();
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        int i = (nextFirst + 1 + length) % length;
        return items[i];
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        int i = (nextLast - 1 + length) % length;
        return items[i];
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int i = (nextFirst + 1 + length) % length;
        T removed = items[i];
        items[i] = null;
        nextFirst = i;
        size--;
        if (length >= 16 && size * 4 <= length) {
            downSizeHelper(downSizeFactor);
        }
        return removed;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int i = (nextLast - 1 + length) % length;
        T removed = items[i];
        items[i] = null;
        nextLast = i;
        size--;
        if (length >= 16 && size * 4 <= length) {
            downSizeHelper(downSizeFactor);
        }
        return removed;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst+index+1)%length];  // 获取 deque 序号元素，而非 array 序号元素
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    /* Return an iterator. */
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T>{
        private int wizPos;
        public ArrayDeque61BIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        // this and other object reference the same object
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque61B<?> oad) {
            // check arrayDeque are of the same size
            if (oad.size != this.size) {
                return false;
            }
            // check that all of my items are in the other arrayDeque
//            for (T x : this) {
//                if (!oad.contains(x)) {
//                    return false;
//                }
//            }
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(oad.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // o is not an arrayDeque, so returns false
        return false;
    }

    @Override
    public String toString() {
//        String returnString = "{";
//        for (T item : this) {
//            returnString += item.toString();
//            returnString += ",";
//        }
//        returnString += "}";
//        return returnString;

        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size-1; i++) {
            returnSB.append(get(i));
            returnSB.append(",");
        }
        returnSB.append(get(size-1));
        returnSB.append("}");
        return returnSB.toString();
    }
}
