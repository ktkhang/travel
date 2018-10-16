package com.major.project.travel.util;

import com.major.project.travel.exception.RestException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by HUY on 9/9/2018
 */
public class Utility {

    public static final int UID_LENGTH = 12;

    private static final String ERROR_MESSAGE_DELIMITER = ";";

    private static final String LETTER_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_LIST = "1234567890";
    private static final String SYMBOL_LIST = "!@$%^*";
    private static final String WORD_LIST = LETTER_LIST + NUMBER_LIST;
    private static final String CHAR_LIST = WORD_LIST + SYMBOL_LIST;

    /**
     * Random sequence characters
     *
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

    /**
     * Validate errors
     *
     * @param errors
     * @throws RestException
     */
    public static void validateErrorsRequest(Errors errors) throws RestException {
        StringBuilder errorMessage = new StringBuilder();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                errorMessage.append(ERROR_MESSAGE_DELIMITER + error.getDefaultMessage());
            }
            throw new RestException(errorMessage.substring(1), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static String randomUid() {
        Random random = new Random();
        int idx = random.nextInt(LETTER_LIST.length());
        char first = LETTER_LIST.charAt(idx);
        return first + randomSequenceCharacters(WORD_LIST, UID_LENGTH - 1);
    }

}
