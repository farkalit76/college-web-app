package org.usman.api.college.common.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 11-03-2024
 * @since 1.0
 */
@Slf4j
public class NumberUtils {

    public static void main(String[] args) {

        for (int i = 0; i <= 100; i++) {
            //givenUsingPlainJava();
            givenUsingPlainJava2();
            givenUsingJava8();
            givenUsingJava82();
            givenUsingApache();
        }
    }

    /**
     * givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect
     */
    public static void givenUsingPlainJava() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        System.out.println(generatedString);
    }

    /**
     * givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect
     */
    public static void givenUsingPlainJava2() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        System.out.println(generatedString);
    }

    /**
     * givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(
     */
    public static void givenUsingJava8() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
    }

    /**
     * givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect
     */
    public static void givenUsingJava82() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
    }

    /**
     * givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect
     */
    public static Long givenUsingApache() {
        int length = 8;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(generatedString);
        return Long.valueOf(generatedString);
    }

    public static String getRollNumber() {
        // Create a Random object
        Random random = new Random();
        // Generate a random long number
        long randomLong = Math.abs(random.nextLong(10000L)); // Example: between 0 and 10K

        return "2025-"+randomLong;
    }

    public static Long randomNumber() {
        // Create a Random object
        Random random = new Random();

        // Generate a random long number
        // randomLong = random.nextLong(1000000000000L); // Example: between 0 and 1 trillion
        long randomLong = Math.abs(random.nextLong(1000000L)); // Example: between 0 and 1 million

        // Print the generated long number
        //System.out.println("Generated random long: " + randomLong);

        // If you need a positive long (e.g., for database IDs where negative values are not desired)
        //long positiveRandom = Math.abs(random.nextLong());

        return randomLong;
    }

    public static String randomNumberWithTimestamp(int count){

        String random = RandomStringUtils.randomAlphanumeric(count);
        return  random+String.valueOf(System.currentTimeMillis());
    }

    public static String getEnrollNumber() {
        int length = 6; //length of generated number

        char[] ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder random = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random()*ALPHANUMERIC.length);
            random.append(ALPHANUMERIC[index]);
        }
        return random.toString();
    }

    private static void differentRandomNumbers() {
        int number = 10; //0-9
        int alphabets = 26; //A-Z
        int smallAlpha = 26; //a-z

        //Total number of 11-digits formed with these alphanumeric will be as follows
        //(10) to the power 11 only for digits 0-9
        BigDecimal totalDigitsNumbersFromZeroToTNineNumbers = BigDecimal.valueOf(Math.pow(10, 11));
        log.info("NumberUtil totalDigitsNumbersFromZeroToTNineNumbers.:{}", totalDigitsNumbersFromZeroToTNineNumbers.toPlainString());
        //(10+26) to the power 11 only for digits 0-9 & A-Z
        BigDecimal totalDigitsAlphaNumericOnlyCapitalLetters = BigDecimal.valueOf(Math.pow(36, 11));
        log.info("NumberUtil totalDigitsAlphaNumericOnlyCapitalLetters:{}", totalDigitsAlphaNumericOnlyCapitalLetters.toPlainString());
        //(10+26+26) to the power 11 only for digits 0-9 & A-Z & a-z
        BigDecimal totalDigitsAlphaNumericWithSmallLetters = BigDecimal.valueOf(Math.pow(62, 11));
        log.info("NumberUtil totalDigitsAlphaNumericWithSmallLetters..:{}", totalDigitsAlphaNumericWithSmallLetters.toPlainString());

        double start = 36;
        log.info("NumberUtil startPrint count(1):{}", start);
        for (int i = 2; i <= 11; i++) {
            start = start * 36;
            BigDecimal startPrint = BigDecimal.valueOf(start);
            log.info("NumberUtil startPrint count({}):{}", i, startPrint.toPlainString());
        }

        double startCalc = 62d;
        log.info("NumberUtil startPrint count(1):{}", startCalc);
        for (int i = 2; i <= 11; i++) {
            startCalc = startCalc * 62;
            BigDecimal startPrint = BigDecimal.valueOf(startCalc);
            log.info("NumberUtil startPrint count({}):{}", i, startPrint.toPlainString());
        }

        for (int i = 0; i <= 100; i++) {
            log.info("NumberUtil randomAlphaNumeric Numbers count({}):{}", i, getEnrollNumber());//you may set any digit-size.
        }

        for (int i = 0; i <= 100; i++) {
            String random = RandomStringUtils.randomAlphanumeric(10);
            log.info("NumberUtil Apache randomAlphaNumeric Numbers count({}):{}", i, random);
        }
        for (int i = 0; i <= 100; i++) {
            String random = RandomStringUtils.randomAlphanumeric(10);
            log.info("NumberUtil Apache randomAlphaNumeric Numbers with timestamp count({}):{}", i, randomNumberWithTimestamp(7));
        }
    }
}
