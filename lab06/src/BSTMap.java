import java.util.*;
import java.lang.Comparable;
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {
    private int size;
    private Node<K, V> bst;

    //tree's left and right
    private class Node<K,V>{
        public K key;
        public V value;
        public Node<K,V> left;
        public Node<K,V> right;
        public Node(K Key, V Value){
            key =  Key;
            value = Value;
            left = null;
            right = null;
        }
    }

    public BSTMap(){
        size = 0;
        bst = null;
    }


    @Override
    public void put(K key, V value) {
       bst = recursionPut(bst, key, value);
    }

    private Node<K,V> recursionPut(Node<K,V> n, K key, V value){
        if(n == null){
            size++;
            n = new Node<>(key,value);
        }
        else if(compareRoot(n,key) == 0){
            n.value = value;
        }
        else if(compareRoot(n,key) < 0){
            n.right = recursionPut(n.right, key, value);
        }
        else{
            n.left = recursionPut(n.left, key, value);
        }
        return n;
    }

    @Override
    public V get(K key) {
       return recursionGet(bst,key);
    }

    private V recursionGet(Node<K, V> n,K key){
       if(n == null){
           return null;
       }
       if(compareRoot(n,key) < 0){
           return recursionGet(n.right,key);
       }
       if(compareRoot(n,key) > 0){
           return recursionGet(n.left,key);
       }
       return n.value;
    }

    @Override
    public boolean containsKey(K key) {
        return recursionContain(bst,key);
    }
    private boolean recursionContain(Node<K, V> n,K key){
        if(n == null){
            return false;
        }
        if(compareRoot(n,key) < 0){
            return recursionContain(n.right,key);
        }
        if(compareRoot(n,key) > 0){
            return recursionContain(n.left,key);
        }
        return true;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        size = 0;
        bst = null;
    }

    public int compareRoot(Node<K, V> n,K o){
        return n.key.compareTo(o);
    }

    private List<K> printInOrder(Node<K, V> n){
        if(n == null){
            return null;
        }
        List<K> ls = new ArrayList<>();
        ls.add(n.key);
        if(n.left != null){
            ls = printInOrder(n.left);
        }

        printInOrder(n.right);
        return ls;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {

        return recursionRemove(this.bst,key);
    }
    private V recursionRemove(Node<K, V> n,K key){
       if(n == null){
           return null;
       }
       if(compareRoot(n,key) < 0){
           return recursionRemove(n.right,key);
       }
       if(compareRoot(n,key) > 0){
           return recursionRemove(n.left,key);
       }
       V temp = n.value;
       if(n.left == null && n.right == null){

       }
       return temp;
    }

    @Override
    public Iterator<K> iterator() {
        return new bstIteror();
    }
    private class bstIteror implements Iterator<K>{

        @Override
        public boolean hasNext() {
            return bst != null;
        }

        @Override
        public K next() {
            return null;
        }
    }
}
