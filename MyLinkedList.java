package dsalg;

/**
 *
 * @author nmorla
 * @since Aug 17, 2020
 */
public class MyLinkedList {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        System.out.println(" " + (o1 == o2));

        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        System.out.println("===============" + list.size() +" - "+ list.getLast());
        list.printElements();
        System.out.println("===============" + list.size() +" - "+ list.getLast());
        list.add(0, 10);
        list.printElements();
        System.out.println("===============" + list.size() +" - "+ list.getLast());
        list.add(0, 15);
        list.printElements();
        System.out.println("===============" + list.size() +" - "+ list.getLast());
        list.add(0, 5);
        list.printElements();
        System.out.println("===============" + list.size() +" - "+ list.getLast());
        list.add(3, 23);
        list.printElements();
        System.out.println("===============" + list.size() +" <--> "+ list.getLast());
//        Load data into LinkedList
        for (int i = 5; i < 10; i++) {
            list.add(i);
        }
//        Print the data
        list.printElements();
        System.out.println("===============" + list.size() +" <-> "+ list.getLast());
        list.addFirst(200);
        list.printElements();
        System.out.println("===============" + list.size() +" <-> "+ list.getLast());
        list.addLast(-50);
        list.printElements();
        System.out.println("===============" + list.size() +" <--> "+ list.getLast());
//Check if linked list has an element
        System.out.println("List has 50 <==> " + list.contains(50));
        System.out.println("List has 5 <==> " + list.contains(5));
        list.remove(0);
        list.printElements();
        System.out.println("===============" + list.size() +" <-> "+ list.getLast());
        list.removeLast();
        list.printElements();
        System.out.println("===============" + list.size() +" <-> "+ list.getLast());
    }
}

class SingleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SingleLinkedList() {
        head = null;
        tail = null;
    }

    public void add(T t) {
//        Node<T> n = new Node(t);
//        if (head == null) {
//            head = n;
//            tail = n;
//        } else {
//            tail.next = n;
//            tail = tail.next;
//        }
        add(size, t);
    }

    public int size() {
        return size;
    }

    public void printElements() {
        if (head == null) {
            System.out.println("No Data");
            return;
        }
        Node t = head;
        while (t != null) {
            System.out.print(t.data + "\t");
            t = t.next;
        }
        System.out.println("");
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("LinkedList size::" + size + ", Given index :: " + index);
        }
        Node n = new Node(element);
        if (index == 0) {
            Node t = head;
            head = n;
            tail = tail == null ? head : tail;
            n.next = t;
        } else {
            int i = 0;
            Node firstPart = head;
            while (i < index - 1) {
                firstPart = firstPart.next;
                i++;
            }
            if (firstPart == tail) {
                tail = n;
            }
            Node secondPart = firstPart.next;
            firstPart.next = n;
            n.next = secondPart;
        }
        size++;
    }

    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("LinkedList size::" + size + ", Given index :: " + index);
        }
        if (index == 0) {
            head = head.next;
            tail = head == null ? null : tail;
        } else {
            int i = 0;
            Node elem = head;
            Node prev = head;
            while (i < index) {
                prev = elem;
                elem = elem.next;
                i++;
            }
            prev.next = elem.next;
            if (elem == tail) {
                tail = prev;
            }
        }
        size--;
    }

    public void removeLast() {
        remove(size-1);
    }

    public void addFirst(T ele) {
        add(0, ele);
    }

    public void addLast(T ele) {
        add(size, ele);
    }

    public T poll() {
        if (head == null) {
            return null;
        }
        T t = head.data;
        head = head.next;
        return t;
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    public boolean contains(T v) {
        if (head == null) {
            System.out.println("No Data");
            return false;
        }
        Node t = head;
        while (t != null) {
            if (t.data.equals(v)) {
                return true;
            }
            t = t.next;
        }
        return false;
    }

    private class Node<T> {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

    }
}
