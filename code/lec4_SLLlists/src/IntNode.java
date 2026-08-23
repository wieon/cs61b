public class IntNode {

    public int item;
    public IntNode next;

    // Node
    public IntNode(int i, IntNode n) {
        // "this" can be omitted
        item = i;  // store value
        next = n;  // point to the next node
    }


    public static void main(String[] args) {
        IntNode L = new IntNode(4, null);
        L = new IntNode(8, L);
        L = new IntNode(12, L);
    }

}