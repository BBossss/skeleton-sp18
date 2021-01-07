public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int capacity;

    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private void resize(int capacity) {
        this.capacity = capacity;
        T[] newArray = (T[]) new Object[capacity];
        if (nextFirst >= nextLast) {
            System.arraycopy(items, nextFirst + 1, newArray, 0, size - nextFirst);
            System.arraycopy(items, 0, newArray, size - nextFirst, nextLast);
        } else {
            System.arraycopy(items, nextFirst + 1, newArray, 0, nextLast - nextFirst - 1);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newArray;
    }

    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(2 * capacity);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(2 * capacity);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty())
            return;
        int i = (nextFirst + 1) % capacity;
        while (i != (nextLast - 1 + capacity) % capacity) {
            System.out.print(items[i] + " ");
            i = (i + 1) % capacity;
        }
    }

    private double usageRatio() {
        return size * 1.0 / capacity;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;
        nextFirst = (nextFirst + 1) % capacity;
        T rst = items[nextFirst];
        size--;
        if (usageRatio() < 0.25) {
            resize(capacity / 2);
        }
        return rst;
    }

    public T removeLast() {
        if (isEmpty())
            return null;
        nextLast = (nextLast - 1 + capacity) % capacity;
        T rst = items[nextLast];
        size--;
        if (usageRatio() < 0.25) {
            resize(capacity / 2);
        }
        return rst;
    }

    public T get(int index) {
        if (index >= size)
            return null;
        return items[index];
    }

}
