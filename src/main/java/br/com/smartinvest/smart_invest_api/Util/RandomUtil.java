package br.com.smartinvest.smart_invest_api.Util;

import java.util.Random;

public class RandomUtil {

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String getRandomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(ALPHA_NUMERIC.charAt(RANDOM.nextInt(ALPHA_NUMERIC.length())));
        }

        return sb.toString();
    }
}
