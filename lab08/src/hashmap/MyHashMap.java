package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int initialCapacity;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this.initialCapacity = 16;
        loadFactor = 0.75;
        size = 0;
        buckets = new Collection[initialCapacity];
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        loadFactor = 0.75;
        size = 0;
        buckets = new Collection[initialCapacity];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        size = 0;
        buckets = new Collection[initialCapacity];
    }


    @Override
    public void put(K key, V value) {
        //location of bucket
        int location = Math.floorMod(key.hashCode(),initialCapacity);
        //create a new bucket
        if(buckets[location] == null){
            buckets[location] = this.createBucket();
        }
        for(Node node : buckets[location]){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        buckets[location].add(new Node(key, value));
        size++;
        if((double) size / initialCapacity >= loadFactor){
            this.resize();
        }
    }

    @Override
    public V get(K key) {
        int location = Math.floorMod(key.hashCode(),initialCapacity);
        if(buckets[location] == null){
            return null;
        }
        for(Node node : buckets[location]){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int location = Math.floorMod(key.hashCode(),initialCapacity);
        if(buckets[location] == null){
            return false;
        }
        for(Node node : buckets[location]){
            if(node.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new Collection[initialCapacity];
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    private class hashMapIterator implements Iterator<K> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public K next() {
            return null;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }

    private void resize(){
        initialCapacity = initialCapacity * 2;
        Collection<Node>[] newBuckets = new Collection[initialCapacity];
        for(int i = 0; i < initialCapacity / 2; i++){
            if(buckets[i] == null){
                continue;
            }
            for(Node node : buckets[i]){
                int location = Math.floorMod(node.key.hashCode(),initialCapacity);
                if(newBuckets[location] == null) {
                    newBuckets[location] = this.createBucket();
                }
                newBuckets[location].add(node);
            }
        }
        buckets = newBuckets;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
