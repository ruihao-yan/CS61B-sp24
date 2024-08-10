import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import deque.MaxArrayDeque61B;
import org.junit.jupiter.api.*;

import java.util.Comparator;
//import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) { return a.length() - b.length(); }
    }
    private static class numberComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) { return a - b; }
    }
    private static class alphabeticalComparator implements Comparator<String> {
        public int compare(String a, String b) { return a.compareTo(b); }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
        assertThat(mad.max(new alphabeticalComparator())).isEqualTo("fury road");
    }
    @Test
    public void basicTest1(){
        MaxArrayDeque61B<Integer> max = new MaxArrayDeque61B<>(new numberComparator());
        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);
        assertThat(max.max()).isEqualTo(3);
    }
    @Test
    public void basicTest2(){
        MaxArrayDeque61B<String> max = new MaxArrayDeque61B<>(new alphabeticalComparator());
        max.addFirst("a");
        max.addFirst("b");
        max.addFirst("c");
        max.addLast("z");
        assertThat(max.max()).isEqualTo("z");
    }

}
