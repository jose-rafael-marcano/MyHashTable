package com.servicenow.hashtable;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        Hashtable<String,Integer> hashtable= new Hashtable();

        hashtable.put("jose",1);
        System.out.println(hashtable.get("jose"));
        System.out.println("size="+hashtable.getSize());
        hashtable.put("jose",100);
        System.out.println(hashtable.get("jose"));
        System.out.println(hashtable.containsKey("jose"));
        System.out.println("size="+hashtable.getSize());
        hashtable.remove("jose");
        System.out.println(hashtable.containsKey("jose"));
        System.out.println(hashtable.get("jose"));
        System.out.println("size="+hashtable.getSize());


        hashtable.put("carlos",10);
        System.out.println(hashtable.get("carlos"));
        System.out.println("size="+hashtable.getSize());


        hashtable.put("abc",11);
        System.out.println(hashtable.get("abc"));
        System.out.println("size="+hashtable.getSize());

        System.out.println(hashtable.remove("jose"));
        System.out.println(hashtable.containsKey("jose"));
        System.out.println(hashtable.get("jose"));
        System.out.println("size="+hashtable.getSize());

        System.out.println(hashtable.remove("abc"));
        System.out.println(hashtable.containsKey("abc"));
        System.out.println(hashtable.get("abc"));
        System.out.println("size="+hashtable.getSize());


        System.out.println("-----");
        hashtable.put("01",1);
        System.out.println(hashtable.get("10"));
        System.out.println("size="+hashtable.getSize());



        assertTrue( true );
    }
}
