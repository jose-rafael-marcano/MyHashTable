package com.servicenow.hashtable;


import java.util.Map;

public class Hashtable<K extends Comparable<K>, V > {
    private int capacity=10;
    private int size;
    private HashTableEntry<K,V>[] entries;
    private double loadFactor=0.75;
    private int threshold;


    public Hashtable(){
        entries=new HashTableEntry[capacity];
        threshold = (int) (capacity * loadFactor);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(K key,V value){
        if (key==null || value==null)
            throw new IllegalArgumentException("Key or value cannot be null");


        int hash= hash(key);//return index of array
        HashTableEntry linkedListOfHashTableEntry= entries[hash];
        if (linkedListOfHashTableEntry==null){
            entries[hash]=new HashTableEntry(key,value,null);
            size++;
            return;
        }

        for (HashTableEntry x=linkedListOfHashTableEntry; x!=null; x=x.getNext()){
            if (x.getKey().compareTo(key)==0){
                x.setValue(value);
                return;
            }
        }

        //we didn't find the element in the linked list so add the new element
        HashTableEntry newHead=new HashTableEntry(key,value,linkedListOfHashTableEntry);
        entries[hash]=newHead;
        size++;

        //if elements > threshold rehash
        if (size>threshold){
            resize();
        }
    }

    /**
     *  Create a new array with double of the size and copy previous entries in the new array
     *
     */
    private void resize() {
        HashTableEntry<K, V>[] currentElements = entries;
        int newCapacity = (entries.length * 2) + 1;
        threshold = (int) (newCapacity * loadFactor);
        entries = (HashTableEntry<K, V>[]) new HashTableEntry[newCapacity];

        //copy current entries to new array
        for (int i = currentElements.length - 1; i >= 0; i--){
            HashTableEntry<K, V> entry = currentElements[i];
            while (entry != null){
                int hash = hash(entry.getKey());//get new hash

                HashTableEntry<K, V> newEntry = entries[hash];//see if there are elements in the new position
                if (newEntry != null) { //if there are elements add the new element at the end of the linked list
                    HashTableEntry nextEntry = newEntry.getNext();
                    while (nextEntry != null){
                        newEntry.setNext(  nextEntry.getNext());
                        nextEntry.setNext( newEntry.getNext());
                    }
                    newEntry.setNext(entry);
                }else{
                    entries[hash] = entry;
                }

                HashTableEntry<K, V> next = entry.getNext();
                entry.setNext( null);
                entry = next;
            }

        }



    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key){
        if (key==null)
            throw new IllegalArgumentException("Key cannot be null");

        int hash= hash(key);//return index of array
        HashTableEntry<K,V> linkedListOfHashTableEntry= entries[hash];
        if (linkedListOfHashTableEntry==null)
           return null;

        for (HashTableEntry<K,V> x=linkedListOfHashTableEntry; x!=null; x=x.getNext()){
            if (x.getKey().compareTo(key)==0){
                return  x.getValue();
            }
        }
        return null;
    }


    /**
     *
     * @param key
     * @return V element removed
     * @throws Exception
     */
    public V remove(K key) throws Exception {
        if (key==null)
            throw new IllegalArgumentException("Key cannot be null");

        int hash= hash(key);//return index of array
        HashTableEntry<K,V> linkedListOfHashTableEntry= entries[hash];

        if (linkedListOfHashTableEntry==null)
            return null;

        for (HashTableEntry<K,V> x=linkedListOfHashTableEntry, previousEntry=null; x!=null; x=x.getNext(),previousEntry=x){
            if (x.getKey().compareTo(key)==0){
                if (previousEntry==null && x.getNext()==null){
                    entries[hash]=null;
                }else {
                    previousEntry.setNext(x.getNext());
                }
                size--;
                return x.getValue();
            }
        }
        return null;
    }


    /**
     *  to avoid outbound exception  divide  module of array length
     *
     *  To avoid java.lang.ArrayIndexOutOfBoundsException: -2, use Math.abs
     *  hashtable.put("carlos",10); produces -2 for an array of length 10
     * @param key
     * @return
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % entries.length);
    }

    /**
     *
     * @return
     */
    public boolean containsKey(K key){
        if (key==null)
            throw new IllegalArgumentException("Key cannot be null");

        int hash= hash(key);//return index of array
        HashTableEntry<K,V> linkedListOfHashTableEntry= entries[hash];
        if (linkedListOfHashTableEntry==null)
            return false;

        for (HashTableEntry x=linkedListOfHashTableEntry;x!=null;x=x.getNext()){
            if (x.getKey().compareTo(key)==0)
                return true;
        }
        return false;
    }


    /**
     *
     * @return
     */
    public int getInitialCapacity() {
        return capacity;
    }


    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }



}


