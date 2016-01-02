import java.util.List;

/**
 * Created by eden on 6/19/15.
 */
public class linked {
    Node head;//, tail;
    public linked() {};
    public class Node {
        Node next;
        String data;

        public Node(String o) {
            this.data = o;
        }
    }
    public void insertInFront(String o) {
        Node newHead = new Node(o);
        newHead.next = head;
        head = newHead;
        //return newHead;
    }/*
    public void insertAtEnd(String o) {
        Node newTail = new Node(o);
        tail.next = newTail;
        tail = newTail;
    }*/
    public void insertAtEndSingly(String o) {
        Node newEnd = new Node(o);

//        System.out.println(test.data);
        findEnd().next = newEnd;

    }
    public Node findEnd() {
        Node test = head;
        while(test!=null) {
            System.out.println(test.data);
            if(test.next == null) { break; }
            test=test.next;
        }
        return test;
    }
    public String toString() {
        Node test = head;
        while(test != null) {
            System.out.println(test.data + " " + test.next);
            test = test.next;
        }
        return "end of list";
    }
    public static void main(String[] args) {
        linked l = new linked();
        l.insertInFront("the first head");
        //l.insertAtEnd("the first tail");
        l.insertInFront("the second head");
        l.insertInFront("the third head");
        l.insertAtEndSingly("the single end");
        //l.insertAtEnd("the second tail");
        System.out.println(l);

    }
}
