package com.example.bitanalize;

import java.math.BigInteger;
import java.sql.Array;

public class MyTest {
    public static void main(String[] args) {
        // Creating BigInteger object
        BigInteger b1;
        b1 = new BigInteger("321456");

        // create radix
        int radix = 2;

        // apply toString(radix) method
        String b1String = b1.toString(radix);

        // print String
        System.out.println("Binary String of BigInteger "
                + b1 + " is equal to " + b1String);
    }
}

