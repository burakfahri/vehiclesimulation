package com.tba.util;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    private RandomUtil(){}

    public static int createRandomBtw(int max,int min)
    {
        return random.nextInt(max - min) + min;
    }
}
