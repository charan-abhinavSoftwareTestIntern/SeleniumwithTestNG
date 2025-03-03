package utils;

public class DataStorageUtils {

        private static String fakeEmail;
        private static String fakePassword;
        private static String fakeFirstName;
        private static String fakeLastName;
        private static String fakeEmployeeId;
        private static String appliedFromDate;

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

        public static void setFakeFistName(String firstName){
            fakeFirstName = firstName;
        }

        public static String getFakeFirstName(){
            return fakeFirstName;
        }

        public static void setFakeLastName(String lastName){
            fakeLastName = lastName;
        }

        public static String getFakeLastName(){
            return fakeLastName;
        }

        public static void setFakeEmployeeId(String employeeId){
            fakeEmployeeId = employeeId;
        }

        public static String getFakeEmployeeId(){
            return fakeEmployeeId;
        }

}
