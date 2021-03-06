public class ArrayDeque<T> implements Deque<T> {
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

    private void resize(int cap) {
        int originalCap = this.capacity;
        this.capacity = cap;
        T[] newArray = (T[]) new Object[capacity];
        if (nextFirst >= nextLast) {
            System.arraycopy(items, nextFirst + 1, newArray, 0, originalCap - nextFirst - 1);
            System.arraycopy(items, 0, newArray, originalCap - nextFirst - 1, nextLast);
        } else {
            System.arraycopy(items, nextFirst + 1, newArray, 0, nextLast - nextFirst - 1);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newArray;
    }

    @Override
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(2 * capacity);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(2 * capacity);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int i = (nextFirst + 1) % capacity;
        while (i != (nextLast - 1 + capacity) % capacity) {
            System.out.print(items[i] + " ");
            i = (i + 1) % capacity;
        }
    }

    private double usageRatio() {
        return size * 1.0 / capacity;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % capacity;
        T rst = items[nextFirst];
        size--;
        if (usageRatio() < 0.25) {
            resize(capacity / 2);
        }
        return rst;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + capacity) % capacity;
        T rst = items[nextLast];
        size--;
        if (usageRatio() < 0.25) {
            resize(capacity / 2);
        }
        return rst;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        index = (nextFirst + 1 + index) % capacity;
        return items[index];
    }

}
