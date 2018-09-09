package com.major.project.travel.util;

import java.util.Random;

/**
 * Created by HUY on 9/9/2018
 */
public class Utility {

    public static final int UID_LENGTH = 12;

    private static final String ERROR_MESSAGE_DELIMITER = ";";

    private static final String LETTER_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_LIST = "1234567890";
    //    private static final String SYMBOL_LIST = "!@#$%^*";
    private static final String SYMBOL_LIST = "!@$%^*";
    private static final String WORD_LIST = LETTER_LIST + NUMBER_LIST;
    private static final String CHAR_LIST = WORD_LIST + SYMBOL_LIST;

    /**
     * Random sequence characters
     * @param source
     * @param length
     * @return
     */
    private static String randomSequenceCharacters(String source, int length) {
        StringBuilder result = new StringBuilder(length);
        int maxNo = source.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(maxNo);
            char c = source.charAt(number);
            result.append(c);
        }
        return result.toString();
    }


    public static String randomUid() {
        Random random = new Random();
        int idx = random.nextInt(LETTER_LIST.length());
        char first = LETTER_LIST.charAt(idx);
        return first + randomSequenceCharacters(WORD_LIST, UID_LENGTH - 1);
    }

}
