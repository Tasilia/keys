package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    public static Faker faker = new Faker(new Locale("ru"));
    private static String phone = "+79819500657";
    private static String code = "1111";
    private static String password = "admin";

    public static String getCorrectPhone() {
        return phone;
    }

    public static String getCorrectCode() {
        return code;
    }

    public static String getCorrectPassword() {
        return password;
    }

    public static String getWrongPhone() {
        return faker.phoneNumber().cellPhone();
    }

    public static String getWrongCode() {
        return faker.numerify("####");
    }

    public static String getWrongPassword() {
        return faker.internet().password();
    }

}
