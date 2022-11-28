package data;

public class DataHelper {
    public static class AuthInfo {
        private String phone = "+79819500657";
        private String code = "1111";
        private String password = "admin";

        public String getPhone() {
            return phone;
        }

        public String getCode() {
            return code;
        }

        public String getPassword() {
            return password;
        }
    }
    public static AuthInfo getAuthInfo() {
        return new AuthInfo();
    }
}
