package com.servicenow.hashtable;

import org.assertj.core.api.AutoCloseableSoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class HashTableEntryTest {

    public void testHashTableEntry(){
        HashTableEntry<Integer,Integer> hashTableEntry=new HashTableEntry(1,1,null);
        assertThat(hashTableEntry).as("element of hash table").isInstanceOf(HashTableEntry.class);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(hashTableEntry.getKey()).isGreaterThan(0).isNotNull().isEqualTo(1).as(" Key of the hashtable");
            softly.assertThat(hashTableEntry.getValue()).isGreaterThan(0).isNotNull().isEqualTo(1).as(" Value of the hashtable");
            softly.assertThat(hashTableEntry.getNext()).isNull();
        }



    }
}
