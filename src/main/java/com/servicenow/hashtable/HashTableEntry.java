package com.servicenow.hashtable;

/**
 * Linked data structure used for Hashtable Key, value pair.
 *
 * @param <K>
 * @param <V>
 */
public class HashTableEntry <K extends Comparable<K>,V> {

    private K key;
    private V value;
    private HashTableEntry next;

    public HashTableEntry(K key,V value, HashTableEntry next){
        this.key=key;
        this.value=value;
        this.next=next;
    }


    /**
     * Only get to avoid removed the current key, we can change the value of entry but not the key,
     * Key must be immutable
     * @return
     */
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashTableEntry getNext() {
        return next;
    }

    public void setNext(HashTableEntry next) {
        this.next = next;
    }


}
