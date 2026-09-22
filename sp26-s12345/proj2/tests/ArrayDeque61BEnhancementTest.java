import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BEnhancementTest {
    @Test
    /* Test toString method. */
    public void toStringTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        System.out.println(ad);
    }

    @Test
    /* Test equals method. */
    public void equalsTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        Deque61B<String> ad2 = new ArrayDeque61B<>();
        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");
        assertThat(ad.equals(ad2)).isTrue();
    }
}
