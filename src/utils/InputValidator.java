package utils;

public class InputValidator {

    // Validate that a string is not null or empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email != null && email.matches(emailRegex);
    }

    // Validate if a string contains only digits
    public static boolean isNumeric(String input) {
        return input != null && input.matches("\\d+");
    }

    // Validate if a string can be parsed as a double
    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate if a phone number is valid (10-15 digits)
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }
}
