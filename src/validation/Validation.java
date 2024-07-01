package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean validateBI (String bi) {
        Pattern pattern = Pattern.compile("^\\d{9}([a-z]{2})\\d{3}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(bi);

        if (!matcher.find()) {
            return false;
        }

        String code = matcher.group(1);
        String allowedCodes = "LA-BE-LN-LS-MO-ZE-HO-KN-KS-CA-UE-NE-CE-HA-BO-ME-BA-CC";

        if (!allowedCodes.contains(code)) {
            return false;
        }

        return true;
    }

    public static boolean validateName (String name) {
        Pattern pattern = Pattern.compile("^[a-z]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    public static boolean validatePhone (String phone) {
        Pattern pattern = Pattern.compile("^[0-9\\(\\)\\+]{7,15}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);

        return matcher.find();
    }

    public static boolean validateSalary (float salary) {
        return salary > 0;
    }
}

