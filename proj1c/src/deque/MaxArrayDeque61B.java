package deque;
import java.util.ArrayDeque;
import java.util.Comparator;
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque61B(Comparator<T> c) {
        super(c);
        comparator = c;
    }
    public T max(){
        if(this.size() == 0) {return null;}
        T maxval = ArrayDeque[Math.floorMod(sizeFirst + 1, ArrayDeque.length)];
        for(int temp = sizeFirst + 2; temp < sizeLast; temp++){
            T comparaval = ArrayDeque[Math.floorMod(temp, ArrayDeque.length)];
            if(comparator.compare(maxval,comparaval) < 0){
                maxval = comparaval;
            }
        }
        return maxval;
    }
    public T max(Comparator<T> com){
        comparator = com;
        return this.max();
    }
}
