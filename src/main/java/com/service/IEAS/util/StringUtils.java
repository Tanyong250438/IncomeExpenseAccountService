package com.service.IEAS.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tanyong
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String APPLICATION_VALUE_TYPE_INTEGER = "Integer";
    public static final String APPLICATION_VALUE_TYPE_FLOAT = "Float";
    public static final String APPLICATION_VALUE_TYPE_DOUBLE = "Double";
    public static final String APPLICATION_VALUE_TYPE_LONG = "Long";
    public static final String APPLICATION_VALUE_TYPE_BIGINTEGER = "BigInteger";
    public static final String APPLICATION_VALUE_TYPE_BOOLEAN = "Boolean";

    public static boolean isNotEmptyOrNull(String str) {
        return !(null == str || "".equals(str.trim()) || str.trim().length() == 0 || "null".equalsIgnoreCase(str));
    }

    public static boolean isEmptyOrNull(String str) {
        return (str == null || "".equals(str.trim()) || str.trim().length() == 0);
    }

    public static boolean validateFieldsByTypeOrRegex(String input, String validateType, String regex) {

        if (isNotEmptyOrNull(input)) {
            if (isNotEmptyOrNull(regex)) {
                Pattern pattern = Pattern.compile(regex);
                if (!pattern.matcher(input).matches()) {
                    return false;
                }
            }
            if (APPLICATION_VALUE_TYPE_INTEGER.equalsIgnoreCase(validateType)) {
                try {
                    Integer.parseInt(input);

                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }

            }
            if (APPLICATION_VALUE_TYPE_FLOAT.equalsIgnoreCase(validateType)) {
                try {
                    Float.parseFloat(input);

                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }

            }
            if (APPLICATION_VALUE_TYPE_DOUBLE.equalsIgnoreCase(validateType)) {
                try {
                    Double.parseDouble(input);

                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }
            }
            if (APPLICATION_VALUE_TYPE_LONG.equalsIgnoreCase(validateType)) {
                try {
                    Long.parseLong(input);

                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }
            }
            if (APPLICATION_VALUE_TYPE_BIGINTEGER.equalsIgnoreCase(validateType)) {
                try {
                    BigInteger.valueOf(Long.parseLong(input));

                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }
            }
            if (APPLICATION_VALUE_TYPE_BOOLEAN.equalsIgnoreCase(validateType)) {
                return "true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input);
            }
        }

        return true;
    }

    public static boolean validateFieldsByLength(String input, int maxLength) {
        return input != null && (input.length() <= maxLength);
    }

    public static String parseStringToNumberWithIgnore(String number, String find, String replace) {
        try {
            if (isNotEmptyOrNull(number)) {
                if (find != null && replace != null) {
                    return String.valueOf(new BigDecimal(number.replace(find, replace)).setScale(2, RoundingMode.HALF_UP));
                }

            } else {
                return null;
            }

        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static String markDash(String str) {
        return !(null == str || "".equals(str.trim()) || str.trim().length() == 0 || "null".equalsIgnoreCase(str)) ? str : "-";
    }

    public static boolean checkWhiteSpace(String input) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
