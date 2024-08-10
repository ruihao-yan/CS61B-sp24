import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
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
    public void toListCase1(){
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.toList()).containsExactly().inOrder();
    }
    @Test
    public void toListCase2(){
         Deque61B<String> lld2 = new LinkedListDeque61B<>();
         lld2.addLast("front");
         lld2.addLast("middle");
         lld2.addLast("back");
         lld2.addFirst("LiHua");
         assertThat(lld2.toList()).containsExactly("LiHua","front", "middle", "back").inOrder();
    }
    @Test
    public void toListCase3(){
         Deque61B<Character> lld3 = new LinkedListDeque61B<>();
         lld3.addLast('a');
         lld3.addLast('b');
         lld3.addLast('c');
         lld3.addFirst('d');
         assertThat(lld3.toList()).containsExactly('d','a', 'b', 'c').inOrder();
    }
    @Test
    public void toListCase4(){
         Deque61B<String> lld4 = new LinkedListDeque61B<>();
         lld4.addFirst("perfect");
         assertThat(lld4.toList()).containsExactly("perfect").inOrder();
    }
    @Test
    public void toListCase5() {
        Deque61B<String> lld5 = new LinkedListDeque61B<>();
        lld5.addLast("perfect");
        assertWithMessage("actual is not expected").that(lld5.toList()).containsExactly("perfect").inOrder();
    }
    @Test
    public void removeFirstCase1() {
        Deque61B<Integer> lld6 = new LinkedListDeque61B<>();
        lld6.addLast(1);
        lld6.addLast(2);
        lld6.addLast(3);
        lld6.addLast(4);
        lld6.addFirst(5);
        int number = lld6.removeFirst();
        assertThat(number).isEqualTo(5);
    }
    @Test
    public void removeFirstCase2(){
         Deque61B<Integer> lld7 = new LinkedListDeque61B<>();
         assertThat(lld7.removeFirst()).isEqualTo(null);
    }
    @Test
    public void removeFirstCase3(){
         Deque61B<String> lld8 = new LinkedListDeque61B<>();
         lld8.addLast("Linux");
         lld8.addLast("Windows");
         lld8.addLast("Max OS");
         lld8.addFirst("Android");
         assertThat(lld8.removeFirst()).isEqualTo("Android");
    }
    @Test
    public void removeLastCase1() {
        Deque61B<Integer> lld9 = new LinkedListDeque61B<>();
        lld9.addLast(1);
        lld9.addLast(2);
        lld9.addLast(3);
        lld9.addFirst(4);
        assertThat(lld9.removeLast()).isEqualTo(3);
    }
    @Test
    public void removeLastCase2(){
         Deque61B<String> lld10 = new LinkedListDeque61B<>();
         assertThat(lld10.removeLast()).isEqualTo(null);
    }
    @Test
    public void removeLastCase3(){
         Deque61B<String> lld11 = new LinkedListDeque61B<>();
         lld11.addLast("Linux");
         lld11.addLast("Windows");
         lld11.addLast("Max OS");
         lld11.addLast("Android");
         assertThat(lld11.removeLast()).isEqualTo("Android");
    }
    @Test
    public void getCase1() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.get(1)).isEqualTo(1);
    }
    @Test
    public void getCase2(){
         Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
         lld2.addLast(1);
         lld2.addLast(2);
         lld2.addLast(3);
         assertThat(lld2.get(2)).isEqualTo(2);
    }
    @Test
    public void getCase3(){
         Deque61B<String> lld3 = new LinkedListDeque61B<>();
         lld3.addLast("Linux");
         lld3.addLast("Windows");
         lld3.addLast("Max OS");
         assertThat(lld3.get(3)).isEqualTo("Max OS");
    }
    @Test
    public void getCase4(){
         Deque61B<String> lld4 = new LinkedListDeque61B<>();
         assertThat(lld4.get(1)).isEqualTo(null);
         assertThat(lld4.get(0)).isEqualTo(null);
    }
    @Test
    public void getRecursiveCase1() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.getRecursive(1)).isEqualTo(1);
    }
    @Test
    public void getRecursiveCase2() {
         Deque61B<String> lld2 = new LinkedListDeque61B<>();
         lld2.addLast("Linux");
         lld2.addLast("Windows");
         lld2.addLast("Max OS");
         assertThat(lld2.getRecursive(2)).isEqualTo("Windows");
    }
    @Test
    public void getRecursiveCase3() {
         Deque61B<String> lld3 = new LinkedListDeque61B<>();
         assertThat(lld3.getRecursive(0)).isEqualTo(null);
         assertThat(lld3.getRecursive(1)).isEqualTo(null);
    }





}
