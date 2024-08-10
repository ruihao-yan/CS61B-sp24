import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void add_first_from_empty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        assertThat(Ad1.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void add_last_from_empty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addLast(1);
        assertThat(Ad1.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void add_first_nonempty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        assertThat(Ad1.toList()).containsExactly(2,1).inOrder();
    }
    @Test
    public void add_last_nonempty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addLast(1);
        Ad1.addLast(2);
        assertThat(Ad1.toList()).containsExactly(1,2).inOrder();
    }
    @Test
    public void add_first_trigger_resize(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        Ad1.addFirst(3);
        Ad1.addFirst(4);
        Ad1.addFirst(5);
        Ad1.addFirst(6);
        Ad1.addFirst(7);
        Ad1.addFirst(8);
        Ad1.addFirst(9);
        assertThat(Ad1.toList()).containsExactly(9,8,7,6,5,4,3,2,1).inOrder();
    }
    @Test
    public void add_last_trigger_resize(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addLast(1);
        Ad1.addLast(2);
        Ad1.addLast(3);
        Ad1.addLast(4);
        Ad1.addLast(5);
        Ad1.addLast(6);
        Ad1.addLast(7);
        Ad1.addLast(8);
        Ad1.addLast(9);
        Ad1.addLast(10);
        assertThat(Ad1.toList()).containsExactly(1,2,3,4,5,6,7,8,9,10).inOrder();
    }
    @Test
    public void add_first_after_remove_to_empty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        Ad1.addFirst(3);
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.addFirst(10);
        assertThat(Ad1.get(0)).isEqualTo(10);
    }
    @Test
    public void add_last_after_remove_to_empty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addLast(1);
        Ad1.addLast(2);
        Ad1.addLast(3);
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.addLast(10);
        assertThat(Ad1.get(0)).isEqualTo(10);
    }
    @Test
    public void get_valid(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        Ad1.addFirst(3);
        assertThat(Ad1.get(0)).isEqualTo(3);
        assertThat(Ad1.get(1)).isEqualTo(2);
        assertThat(Ad1.get(2)).isEqualTo(1);
        assertThat(Ad1.get(3)).isEqualTo(null);
        assertThat(Ad1.get(-1)).isEqualTo(null);
    }
    @Test
    public void is_empty_true(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        assertThat(Ad1.isEmpty()).isTrue();
    }
    @Test
    public void is_empty_false(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        assertThat(Ad1.isEmpty()).isFalse();
    }
    @Test
    public void to_list_empty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        assertThat(Ad1.toList()).isEmpty();
    }
    @Test
    public void to_list_nonempty(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        assertThat(Ad1.toList()).isNotEmpty();
    }

    @Test
    public void remove_first_and_add_first(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        Ad1.addFirst(3);
        Ad1.addFirst(4);
        Ad1.addFirst(5);
        Ad1.addFirst(6);
        Ad1.addFirst(7);
        Ad1.addFirst(8);
        Ad1.addFirst(9);
        Ad1.addLast(12);
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.addFirst(10);
        Ad1.addFirst(11);
        assertThat(Ad1.toList()).containsExactly(11,10,6,5,4,3,2,1,12).inOrder();
    }

    @Test
    public void remove_last_trigger_resize(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addLast(1);
        Ad1.addLast(2);
        Ad1.addLast(3);
        Ad1.addLast(4);
        Ad1.addLast(5);
        Ad1.addLast(6);
        Ad1.addLast(7);
        Ad1.addLast(8);
        Ad1.addLast(9);
        Ad1.addLast(10);
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        Ad1.removeLast();
        assertThat(Ad1.get(0)).isEqualTo(1);
        assertThat(Ad1.get(1)).isEqualTo(2);
        assertThat(Ad1.get(2)).isEqualTo(null);
    }
    @Test
    public void remove_first_trigger_resize(){
        ArrayDeque61B<Integer> Ad1 = new ArrayDeque61B<>();
        Ad1.addFirst(1);
        Ad1.addFirst(2);
        Ad1.addFirst(3);
        Ad1.addFirst(4);
        Ad1.addFirst(5);
        Ad1.addFirst(6);
        Ad1.addFirst(7);
        Ad1.addFirst(8);
        Ad1.addFirst(9);
        Ad1.addFirst(10);
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.removeFirst();
        Ad1.addFirst(123);
        Ad1.addLast(12);
        assertThat(Ad1.get(0)).isEqualTo(123);
        assertThat(Ad1.get(1)).isEqualTo(3);
        assertThat(Ad1.get(2)).isEqualTo(2);
        assertThat(Ad1.get(3)).isEqualTo(1);
        assertThat(Ad1.get(4)).isEqualTo(12);
        assertThat(Ad1.get(5)).isEqualTo(null);
    }



}
