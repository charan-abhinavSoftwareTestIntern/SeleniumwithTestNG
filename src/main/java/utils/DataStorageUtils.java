package utils;

public class DataStorageUtils {

        private static String fakeEmail;
        private static String fakePassword;

        // Setters and Getters for email and password
        public static void setFakeEmail(String email) {
            fakeEmail = email;
        }

        public static String getFakeEmail() {
            return fakeEmail;
        }

        public static void setFakePassword(String password) {
            fakePassword = password;
        }

        public static String getFakePassword() {
            return fakePassword;
        }
}
