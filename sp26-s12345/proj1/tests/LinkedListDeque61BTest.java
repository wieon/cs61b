import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    /** In the test, whether the list is empty or non-empty, it is currently copied and returned. */
    public void toListTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("toLIst is empty: ").that(lld1.toList()).containsExactly();
        lld1.addLast(0);
        assertWithMessage("toLIst has a single item: ").that(lld1.toList()).containsExactly(0);
        lld1.addFirst(-1);
        lld1.addLast(2);
        assertWithMessage("toLIst has multiple items: ").that(lld1.toList()).containsExactly(-1, 0, 2);
    }

    @Test
    /** Test when the list is empty and non-empty. */
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("Empty list").that(lld1.isEmpty()).isTrue();
        lld1.addLast(0);
        assertWithMessage("Non-empty list").that(lld1.isEmpty()).isFalse();
    }

    @Test
    /** This test checks the size of the list. */
    public void sizeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.size()).isEqualTo(0);
        lld1.addFirst(-1);
        assertWithMessage("lld1 has a node: ").that(lld1.size()).isEqualTo(1);
        lld1.addFirst(8);
        lld1.addLast(10);
        assertWithMessage("lld1 has 3 nodes: ").that(lld1.size()).isEqualTo(3);
    }

    @Test
    /** Test the getFirst method, including empty and non-empty cases. */
    public void getFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.getFirst()).isNull();
        lld1.addFirst(-7);  // -7
        assertWithMessage("lld1 has one item: ").that(lld1.getFirst()).isEqualTo(-7);
        lld1.addFirst(4);  // 4, -7
        assertWithMessage("using addFirst method: ").that(lld1.getFirst()).isEqualTo(4);
        lld1.addLast(0);  // 4, -7, 0
        assertWithMessage("using addLast method: ").that(lld1.getFirst()).isEqualTo(4);
    }

    @Test
    /** Test the getLast method, including empty and non-empty cases. */
    public void getLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.getLast()).isNull();
        lld1.addFirst(-7);  // -7
        assertWithMessage("lld1 has one item: ").that(lld1.getLast()).isEqualTo(-7);
        lld1.addFirst(4);  // 4, -7
        assertWithMessage("using addFirst method: ").that(lld1.getLast()).isEqualTo(-7);
        lld1.addLast(0);  // 4, -7, 0
        assertWithMessage("using addLast method: ").that(lld1.getLast()).isEqualTo(0);
    }

    @Test
    /** In the test, iteratively get the element according to the index. Test the cases when the argument
     * is out of boundary, --too large or negative, which will return null. */
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.get(0)).isNull();
        lld1.addFirst(5);  // 5
        assertWithMessage("lld1 is non-empty: ").that(lld1.get(0)).isEqualTo(5);
        lld1.addLast(6);  // 5, 6
        lld1.addLast(-9);  // 5, 6, -9
        lld1.addLast(23);  // 5, 6, -9, 23
        assertWithMessage("receiving a valid argument: ").that(lld1.get(3)).isEqualTo(23);
        assertWithMessage("receiving an invalid argument: ").that(lld1.get(2325)).isNull();
        assertWithMessage("receiving a negative argument: ").that(lld1.get(-4)).isNull();
    }

    @Test
    /** In the test, recursively get the element according to the index. Test the cases when the argument
     * is out of boundary, --too large or negative, which will return null. */
    public void getRecursiveTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.getRecursive(0)).isNull();
        assertWithMessage("lld1 is empty: ").that(lld1.getRecursive(88)).isNull();
        assertWithMessage("lld1 is empty: ").that(lld1.getRecursive(-88)).isNull();
        lld1.addFirst(5);  // 5
        assertWithMessage("lld1 is non-empty: ").that(lld1.getRecursive(0)).isEqualTo(5);
        assertWithMessage("lld1 is non-empty: ").that(lld1.getRecursive(66)).isNull();
        assertWithMessage("lld1 is non-empty: ").that(lld1.getRecursive(-66)).isNull();
        lld1.addLast(6);  // 5, 6
        lld1.addLast(-9);  // 5, 6, -9
        lld1.addLast(23);  // 5, 6, -9, 23
        assertWithMessage("receiving a valid argument: ").that(lld1.getRecursive(3)).isEqualTo(23);
        assertWithMessage("receiving an invalid argument: ").that(lld1.getRecursive(2325)).isNull();
        assertWithMessage("receiving a negative argument: ").that(lld1.getRecursive(-4)).isNull();
    }

    @Test
    /** Test that removeFirst method works correctly. */
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("List is empty: ").that(lld1.removeFirst()).isNull();
        lld1.addFirst(5);  // 5
        lld1.addFirst(10);  // 10, 5
        lld1.addFirst(15);  // 15, 10, 5
        assertWithMessage("List in order: ").that(lld1.toList()).containsExactly(15, 10, 5).inOrder();
        assertWithMessage("Remove first: ").that(lld1.removeFirst()).isEqualTo(15);
        assertWithMessage("Remove first: ").that(lld1.removeFirst()).isEqualTo(10);
    }

    @Test
    /** Test that removeLast method works correctly. */
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("List is empty: ").that(lld1.removeLast()).isNull();
        lld1.addFirst(5);  // 5
        lld1.addFirst(10);  // 10, 5
        lld1.addFirst(15);  // 15, 10, 5
        assertWithMessage("List in order: ").that(lld1.toList()).containsExactly(15, 10, 5).inOrder();
        assertWithMessage("Remove last: ").that(lld1.removeLast()).isEqualTo(5);
        assertWithMessage("Remove last: ").that(lld1.removeLast()).isEqualTo(10);
    }

    @Test
    /** This test performs interspersed removeFirst and removeLast calls. */
    public void removeFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
    }

}