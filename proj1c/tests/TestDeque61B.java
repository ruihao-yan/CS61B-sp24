import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;


public class TestDeque61B {
    @Test
    public void testLinked(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }
    @Test
    public void testLinked2(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1).containsExactly();
    }
    @Test
    public void testLinked3(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        lld1.addFirst("middle");
        lld1.addFirst("back");
        assertThat(lld1).containsExactly("back","middle","front");
    }
    @Test
    public void testLinked4(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addFirst("back");
        assertThat(lld1).containsExactly("back","middle","front");
    }
    @Test
    public void testLinked5(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        assertThat(lld1).containsExactly("front");
    }
    @Test
    public void testArray(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }
    @Test
    public void testArray2(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        assertThat(lld1).containsExactly();
    }
    @Test
    public void testArray3(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("front");
        lld1.addFirst("middle");
        lld1.addFirst("back");
        assertThat(lld1).containsExactly("back","middle","front");
    }
    @Test
    public void testArray4(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addFirst("back");
        assertThat(lld1).containsExactly("back","middle","front");
    }
    @Test
    public void testArray5(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("front");
        assertThat(lld1).containsExactly("front");
    }
    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.addFirst("Linked");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.addFirst("Linked");

        assertThat(lld1.equals(lld2)).isTrue();
    }
    @Test
    public void testEqualDeques61BArray() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.addFirst("Linked");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.addFirst("Linked");


        assertThat(lld1.equals(lld2)).isTrue();
    }
    @Test
    public void testToStringArray(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.addFirst("ch");
        lld1.addFirst("zhu");
        assertThat(lld1.toString()).isEqualTo("[zhu, ch, front, middle, back]");
    }
    @Test
    public void testToStringLinked(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.addFirst("ch");
        lld1.addFirst("zhu");

        assertThat(lld1.toString()).isEqualTo("[zhu, ch, front, middle, back]");
    }

}
