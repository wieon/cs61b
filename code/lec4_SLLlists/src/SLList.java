import edu.princeton.cs.algs4.In;

public class SLList {

    /* Nested class definition. */
    // private: Users have no access to IntNode.
    // static: Instance variables inside cannot access elements of outer class.
    private static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            // "this" can be omitted
            item = i;  // store value
            next = n;  // point to the next node
        }
    }

    private IntNode first;  // instance variables
    private int size;

    /* The first item, if exits, is at sentinel.next. */
    private IntNode sentinel;

    public SLList(int x) {  // methods of SLList
        sentinel = new IntNode(0, null);  // 哨兵节点
//        first = new IntNode(x, null);  // constructor (initialize instance)
        size = 1;
    }

    /* Add item x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
//        first = new IntNode(x, first);
        size += 1;
    }

    /* Get the first item in the list. */
    public int getFirst() {
//        return first.item;
        return sentinel.next.item;
    }

    /* Add item x to the end of the list. */
    public void addLast(int x) {
        size += 1;
//        IntNode p = first;
        IntNode p = sentinel;
        while (p.next != null){  // Scan p until it reaches the end of the list.
            p = p.next;
        }
        p.next = new IntNode(x, null);

    }

    /* A private recursive helper method */
//    private int size(IntNode p) {
//        if (p.next == null) {
//            return  1;
//        }
//        return 1 + size(p.next);
//    }
//
//    public int size() {
//        return size(first);
//    }

    /* Return the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList(5);
        L.addFirst(10);
        System.out.println(L.getFirst());
    }
}
