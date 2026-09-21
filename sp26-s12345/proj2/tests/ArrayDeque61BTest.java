import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    /* Test addFirst method. */
    public void addFirstTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("as");
        ad.addFirst("sd");
        ad.addFirst("df");
        ad.addFirst("fg");
        ad.addFirst("hj");
        ad.addFirst("lk");
        ad.addFirst("ok");
        ad.addFirst("ko");
        assertThat(ad.toList()).containsExactly("as", "ko", "ok", "lk", "hj", "fg", "df", "sd").inOrder();
    }

    @Test
    /* Test addLast method. */
    public void addLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(3);
        ad.addLast(78);
        ad.addLast(965);
        ad.addLast(8);
        ad.addLast(0);
        ad.addLast(77);
        ad.addLast(45);
        ad.addLast(63);
        assertThat(ad.toList()).containsExactly(63, 3, 78, 965, 8, 0, 77, 45).inOrder();
    }

    @Test
    /* Interspersedly test addFirst and addLast methods. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(78);
        ad.addFirst(26);
        ad.addFirst(50);
        ad.addLast(112);
        ad.addFirst(25);
        ad.addLast(14);
        ad.addLast(-7);
        ad.addFirst(8);
        assertThat(ad.toList()).containsExactly(26, 78, 112, 14, -7, 8, 25, 50).inOrder();
    }

    @Test
    /* Test getFirst method, including empty-array case. */
    public void getFirstTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        assertWithMessage("empty: ").that(ad.getFirst()).isNull();
        ad.addFirst(50);
        assertThat(ad.getFirst()).isEqualTo(50);
        ad.addLast(-7);
        ad.addFirst(8);
        assertThat(ad.getFirst()).isEqualTo(8);
    }

    @Test
    /* Test getFirst method, including empty-array case. */
    public void getLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        assertWithMessage("empty: ").that(ad.getLast()).isNull();
        ad.addFirst(50);
        ad.addFirst(8);
        assertThat(ad.getLast()).isEqualTo(50);
        ad.addLast(-7);
        assertThat(ad.getLast()).isEqualTo(-7);
        ad.addLast(66);
        assertThat(ad.getLast()).isEqualTo(66);
    }
}
