public class LinkedListDeque<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node f, Node s) {
            item = i;
            prev = f;
            next = s;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node((T) new Object(), null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        size++;
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        if (size == 1)
            sentinel.prev = node;
    }

    public void addLast(T item) {
        size++;
        Node node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node node = sentinel.next;
        int n = size;
        while (n != 1) {
            System.out.print(node.item + " ");
            node = node.next;
            n--;
        }
        System.out.print(node.item);
    }

    public T removeFirst() {
        if (size == 0)
            return null;

        size--;
        T rst = sentinel.next.item;
        if (size == 0) {
            sentinel.prev = sentinel.next = sentinel;
        } else {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
        }
        return rst;
    }

    public T removeLast() {
        if (size == 0)
            return null;

        size--;
        T rst = sentinel.prev.item;
        if (size == 0) {
            sentinel.prev = sentinel.next = sentinel;
        } else {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
        }
        return rst;
    }

    public T get(int index) {
        if (index >= size)
            return null;
        Node node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index >= size)
            return null;
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node node) {
        if (index == 0)
            return node.item;
        return getRecursiveHelper(--index, node.next);
    }

}
