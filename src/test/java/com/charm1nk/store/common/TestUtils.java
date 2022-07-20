package com.charm1nk.store.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class TestUtils {
    public static String randomString() {
        return randomString(null, null, null);
    }

    public static String randomString(Integer length, Boolean withLetters, Boolean withNumbers) {
        if (length == null) {
            length = 10;
        }
        if (withLetters == null) {
            withLetters = true;
        }
        if (withNumbers == null) {
            withNumbers = true;
        }

        return RandomStringUtils.random(length, withLetters, withNumbers);
    }

    public static Integer randomNumber() {
        if (new Random().nextInt() % 2 == 0) {
            return randomPositiveNumber(null, null);
        } else {
            return randomNegativeNumber(null, null);
        }
    }

    public static Integer randomPositiveNumber() {
        return randomPositiveNumber(null, null);
    }

    public static Integer randomPositiveNumber(Integer startFrom, Integer endWith) {
        if (startFrom == null) {
            startFrom = 0;
        }
        if (endWith == null) {
            endWith = Integer.MAX_VALUE;
        }

        return RandomUtils.nextInt(startFrom, endWith);
    }

    public static Integer randomNegativeNumber() {
        return randomNegativeNumber(null, null);
    }

    public static Integer randomNegativeNumber(Integer startFrom, Integer endWith) {
        if (startFrom == null) {
            startFrom = 0;
        }
        if (endWith == null) {
            endWith = Integer.MAX_VALUE;
        }

        return RandomUtils.nextInt(startFrom, endWith) - Integer.MAX_VALUE;
    }

    public static String asJsonString(Object data) {
        try {
            return PreparedObjectMapper.getInstance().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return "null";
        }
    }
}
