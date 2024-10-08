package deque;
import org.jaxen.util.LinkedIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class LinkedListDeque61B<T> implements Deque61B<T> {
    public Node sentinel;
    public class Node{
        public Node front;
        public T middle;
        public Node back;
        public Node(Node front,T middle,Node back){
            this.front = front;
            this.middle=middle;
            this.back=back;
        }
    }
    public int size=0;
    public LinkedListDeque61B(){
        sentinel=new Node(null, null, null);
        sentinel.front=sentinel;
        sentinel.back=sentinel;
    }

    @Override
    public void addFirst(T x) {
        sentinel.back = new Node(sentinel, x, sentinel.back);
        sentinel.front.front = sentinel.back;
        this.size+=1;
    }

    @Override
    public void addLast(T x) {
        sentinel.front.back = new Node(sentinel.front, x, sentinel);
        sentinel.front = sentinel.front.back;
        this.size+=1;
    }

    @Override
    public List<T> toList() {
        List<T> lt=new ArrayList<>();
        Node temp=sentinel;
        while(temp.back != sentinel){
            temp=temp.back;
            lt.add(temp.middle);
        }
        return lt;
    }

    @Override
    public boolean isEmpty() {
        if(sentinel.back == sentinel){
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
        if (sentinel.front == sentinel){
            return null;
        }
        //Perfect!!!
        Node temp =sentinel.back;
        sentinel.back = sentinel.back.back;
        sentinel.back.front = null;
        sentinel.back.front = sentinel;
        return temp.middle;
    }

    @Override
    public T removeLast() {
        //sentinel don't remove itself!
        if(sentinel.front == sentinel){
            return null;
        }
        Node temp=sentinel.front;
        sentinel.front = sentinel.front.front;
        sentinel.front.back = null;
        sentinel.front.back = sentinel;
        return temp.middle;
    }

    @Override
    public T get(int index) {
        if (this.size < index){
            return null;
        }
        Node temp = sentinel.back;
        int i = 0;
        while(i < index){
            i++;
            temp = temp.back;
        }
        return temp.middle;
    }
    @Override
    public T getRecursive(int index) {
        if(this.size < index || index == 0){
            return null;
        }
        return this.getRecursivehelper(sentinel.back,index);
    }
    public T getRecursivehelper(Node x,int index){
        if(index == 1){
            return x.middle;
        }
        return getRecursivehelper(x.back, index-1);
    }
    private class LinkedIterator implements Iterator<T>{
        Node temp = sentinel.back;
        @Override
        public boolean hasNext() {
            return (temp.front.back != sentinel);
        }

        @Override
        public T next() {
            T val = temp.middle;
            temp = temp.back;
            return val;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public boolean equals(Deque61B<T> other) {
        if(other instanceof LinkedListDeque61B otherLink){
            if(size != otherLink.size){
                return false;
            }
            Iterator<T> temp = other.iterator();
            T val = temp.next();
            for(T i : this){
               if(i != val){
                   return false;
               }
               val = temp.next();
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder();
        temp.append("[");
        int count = 0;
        for(T i : this){
            temp.append(i);
            count ++;
            if(count < size) {
                temp.append(", ");
            }
        }
        temp.append("]");
        return temp.toString();
    }
   
}
