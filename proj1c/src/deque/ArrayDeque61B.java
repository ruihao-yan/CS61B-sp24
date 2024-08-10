package deque;
import java.util.*;
import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T> {
    protected T[] ArrayDeque;
    protected int sizeFirst;
    protected int sizeLast;
    private int size;
    public ArrayDeque61B() {
        ArrayDeque = (T[]) new Object[8];
        sizeFirst = 0;
        sizeLast = 1;
        size = 0;
    }
    public ArrayDeque61B(Comparator<T> c) {
        ArrayDeque = (T[]) new Object[8];
        sizeFirst = 0;
        sizeLast = 1;
        size = 0;
    }

    private int usage(){
        int i = 0;
        for(T element : toList()){
            i++;
        }
        return i;
    }

    private void transfer(T[] list){
        int location = 0;
        for(T element : toList()){
            list[location] = element;
            location++;
        }
        sizeFirst = -1;
        sizeLast = list.length/2;
        ArrayDeque = list;
    }
    private void Resizeusage(){
        T[] a = (T[]) new Object[ArrayDeque.length/2];
        transfer(a);
    }
    private void Resize(){
        T[] a = (T[]) new Object[ArrayDeque.length*2];
        transfer(a);
    }
    @Override
    public void addFirst(T x) {
        if(size == ArrayDeque.length) {
            this.Resize();
        }
        ArrayDeque[Math.floorMod(sizeFirst,ArrayDeque.length)] = x;
        sizeFirst--;size++;
    }

    @Override
    public void addLast(T x) {
        if(size == ArrayDeque.length){
            this.Resize();
        }
        ArrayDeque[Math.floorMod(sizeLast,ArrayDeque.length)] = x;
        size++;sizeLast++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if(sizeFirst == 0 && sizeLast == 1){
            return returnList;
        }
        for(int tempFirst = sizeFirst + 1; tempFirst <sizeLast;tempFirst++){
            returnList.add(ArrayDeque[Math.floorMod(tempFirst,ArrayDeque.length)]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        sizeFirst++;
        T item =ArrayDeque[Math.floorMod(sizeFirst,ArrayDeque.length)];
        int usage = usage();
        if(ArrayDeque.length > 8 && usage <= ArrayDeque.length * 0.25){
            this.Resizeusage();
        }
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        sizeLast--;
        T item =ArrayDeque[Math.floorMod(sizeLast,ArrayDeque.length)];
        int usage = usage();
        if(ArrayDeque.length > 8 && usage <= ArrayDeque.length * 0.25){
            this.Resizeusage();
        }
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0){
            return null;
        }
        List<T> lst = this.toList();
        return lst.get(index);
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        return null;
    }



    private class ArrayDequeIterator implements Iterator<T>{
        int Arraylocation = sizeFirst+1;

        @Override
        public boolean hasNext() {
            return (ArrayDeque[Math.floorMod(Arraylocation,ArrayDeque.length)] != null);
        }

        @Override
        public T next() {
            T temp = ArrayDeque[Math.floorMod(Arraylocation,ArrayDeque.length)];
            Arraylocation++;
            return temp;
        }
    }
    @Override
    public Iterator<T> iterator() {
       return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Deque61B<T> other) {
        if(other instanceof ArrayDeque61B otherArray){
           if(size != otherArray.size()){
               return false;
           }
           int count = 0;
           for(int location = sizeFirst + 1; count < size; count++, location++){
               if(ArrayDeque[Math.floorMod(location,ArrayDeque.length)] != otherArray.ArrayDeque[Math.floorMod(location, ArrayDeque.length)]){
                   return false;
               }
           }
           return true;
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder nc = new StringBuilder();
        nc.append("[");
        int tmp = sizeFirst + 1;
        while(tmp < sizeLast - 1){
            nc.append(ArrayDeque[Math.floorMod(tmp,ArrayDeque.length)].toString());
            nc.append(", ");
            tmp++;
        }
        nc.append(ArrayDeque[Math.floorMod(tmp,ArrayDeque.length)].toString());
        nc.append("]");
        return nc.toString();
    }
}
