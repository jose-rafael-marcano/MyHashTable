package com.servicenow.hashtable;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertionError;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HastableTest {

    @Test
    public void testHashTablePut(){
        Hashtable<Character,Integer> myHashtable= new Hashtable();
        myHashtable.put('a',97);
        myHashtable.put('b',1);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(myHashtable.get('a')).isNotNull().isEqualTo('a').as(" get method of my hashtable");
            softly.assertThat(myHashtable.get('b')).isGreaterThan(0).isNotNull().isEqualTo(1).as(" Value of the hashtable");
            softly.assertThat(myHashtable.get('d')).isNull();
            softly.assertThat(myHashtable.getSize()).isEqualTo(2);
        }
    }

    @Test
    public void testErrorCases(){
        Hashtable<Character,Integer> myHashtable= new Hashtable();
        assertThatThrownBy(() -> {
            myHashtable.put(null,null);
        }).isInstanceOf(Exception.class).hasMessageContaining("Key or value cannot be null");

        assertThatThrownBy(() -> {
            myHashtable.remove(null);
        }).isInstanceOf(Exception.class).hasMessageContaining("Key cannot be null");

        assertThatThrownBy(() -> {
            myHashtable.get(null);
        }).isInstanceOf(Exception.class).hasMessageContaining("Key cannot be null");


    }

    @Test
    public void testRemoveElement() throws Exception {
        Hashtable<String,Integer> myHashtable= new Hashtable();

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(myHashtable.get("a")).isNull();
            softly.assertThat(myHashtable.getSize()).isEqualTo(0);

            myHashtable.put("a",1);
            myHashtable.put("b",2);
            myHashtable.put("c",3);
            myHashtable.put("4",4);
            myHashtable.put("5",5);

            softly.assertThat(myHashtable.getSize()).isEqualTo(5);

            softly.assertThat(myHashtable.get("b")).isGreaterThan(0).isNotNull().isEqualTo(2).as(" Value of the hashtable");
            softly.assertThat(myHashtable.get("40")).isNull();

            myHashtable.remove("a");
            softly.assertThat(myHashtable.getSize()).isEqualTo(4);

            myHashtable.remove("4");
            myHashtable.remove("5");
            myHashtable.remove("a");
            softly.assertThat(myHashtable.getSize()).isEqualTo(2);

            softly.assertThat(myHashtable.get("c")).isGreaterThan(0).isNotNull().isEqualTo(3).as(" Value of the hashtable");

            myHashtable.put("c",30);

            softly.assertThat(myHashtable.get("c")).isGreaterThan(0).isNotNull().isEqualTo(30).as(" Value of the hashtable");


            softly.assertThat(myHashtable.getSize()).isEqualTo(2);

            myHashtable.put("11",30);
            myHashtable.put("12",30);
            myHashtable.put("13",30);
            myHashtable.put("14",30);
            myHashtable.put("15",30);
            myHashtable.put("16",16);
            myHashtable.put("17",31);
            myHashtable.put("18",30);
            myHashtable.put("19",30);
            myHashtable.put("20",30);

            softly.assertThat(myHashtable.get("19")).isGreaterThan(0).isNotNull().isEqualTo(30).as(" Value of the hashtable");
            softly.assertThat(myHashtable.get("18")).isGreaterThan(0).isNotNull().isEqualTo(30);
            softly.assertThat(myHashtable.get("17")).isGreaterThan(0).isNotNull().isEqualTo(31);
            softly.assertThat(myHashtable.get("16")).isGreaterThan(0).isNotNull().isEqualTo(16);

            softly.assertThat(myHashtable.getSize()).isEqualTo(12);




        }



    }
}
