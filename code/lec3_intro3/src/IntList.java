import edu.princeton.cs.algs4.In;

public class IntList {

    /* ----- Tail insertion method (positive order) ----- */
//    public int first;
//    public IntList rest;
//
//    public static void main(String[] args) {
//        IntList L = new IntList();
//        // Initialization
//        L.first = 5;
//        L.rest = null;
//
//        L.rest = new IntList();
//        L.rest.first = 10;
//        L.rest.rest = null;
//
//        L.rest.rest = new IntList();
//        L.rest.rest.first = 15;
//        L.rest.rest.rest = null;
//    }

    /* ----- Head insertion method (reverse order) ----- */
    //Declaration
    public int first;
    public IntList rest;

    // Node
    public IntList(int f, IntList r) {
        // "this" can be omitted
        this.first = f;  // store value
        this.rest = r;  // point to the next node
    }

    /* Return the size of the list using recursion */
    public int size() {
        if (rest == null) {
            return 1;
        } else {
            return 1 + this.rest.size();
        }
    }

    /* Return the size of the list using no recursion */
    public int iterativeSize() {
        int i;
        IntList p = this;
        for (i = 1; p.rest != null; i += 1) {
            p = p.rest;
        }
        return i;
    }

    /* Get the element value of specific position in the list */
    public int get(int i) {
        if (i > this.iterativeSize()-1) {
            System.out.println("Index out of range!");
            return 404;
        }
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    public static void main(String[] args) {
        IntList L = new IntList(4, null);
        L = new IntList(8, L);
        L = new IntList(12, L);

        System.out.println(L.iterativeSize());
        System.out.println(L.get(3));
    }

}