package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    void setup() {
        open("https://kluchi.staging.pqlab.dev/login");
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "f.h("})
    void enterWithoutPhone(String phone){
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(phone);
        loginPage.enter();
        loginPage.getErrorMessage().shouldHave(text("Требуется значение"));
        loginPage.getErrorMessage().shouldHave(cssValue("color", "rgba(209, 67, 67, 1)"));
    }
    @Test
    void enterWithWrongPhoneFormat(){
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone("4");
        loginPage.enter();
        loginPage.getErrorMessage().shouldHave(text("Неверный формат"));
        loginPage.getErrorMessage().shouldHave(cssValue("color", "rgba(209, 67, 67, 1)"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+79819500657", "89819500657", "+7(981)9500657", "+7 981 950 06 57", "+7-981-950-06-57"})
    void enterWithCorrectPhone(String phone) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(phone);
        loginPage.enter();
        loginPage.getNotificationMessage().shouldHave(text("Проверочный код был отправлен на ваш номер телефона"));
        loginPage.getNotificationMessage().shouldHave(cssValue("color", "rgba(40, 72, 98, 1)"));
        loginPage.getValidationCodeField().shouldBe(visible);
        loginPage.getLabelCode().shouldHave(text("Проверочный код"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "f.h("})
    void enterWithoutCode(String code) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(DataHelper.getAuthInfo().getPhone());
        loginPage.enter();
        loginPage.enterCode(code);
        loginPage.enter();
        loginPage.getErrorMessage().shouldHave(text("Требуется значение"));
        loginPage.getErrorMessage().shouldHave(cssValue("color", "rgba(209, 67, 67, 1)"));
    }

    @Test
    void enterWithIncorrectCode() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(DataHelper.getAuthInfo().getPhone());
        loginPage.enter();
        loginPage.enterCode("1222");
        loginPage.enter();
        loginPage.getNotificationMessage().shouldHave(text("Неверный проверочный код"));
        loginPage.getNotificationMessage().shouldHave(cssValue("color", "rgba(87, 41, 41, 1)"));
    }
    @Test
    void enterWithCorrectCode() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(DataHelper.getAuthInfo().getPhone());
        loginPage.enter();
        loginPage.enterCode(DataHelper.getAuthInfo().getCode());
        loginPage.enter();
        loginPage.getNotificationMessage().shouldHave(text("Введите пароль"));
        loginPage.getNotificationMessage().shouldHave(cssValue("color", "rgba(40, 72, 98, 1)"));
        loginPage.getPasswordField().shouldBe(visible);
        loginPage.getLabelPassword().shouldHave(text("Пароль"));
    }
    @Test
    void enterWithoutPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(DataHelper.getAuthInfo().getPhone());
        loginPage.enter();
        loginPage.enterCode(DataHelper.getAuthInfo().getCode());
        loginPage.enter();
        loginPage.enter();
        loginPage.getErrorMessage().shouldHave(text("Требуется значение"));
        loginPage.getErrorMessage().shouldHave(cssValue("color", "rgba(209, 67, 67, 1)"));
    }
    @Test
    void enterWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPhone(DataHelper.getAuthInfo().getPhone());
        loginPage.enter();
        loginPage.enterCode(DataHelper.getAuthInfo().getCode());
        loginPage.enter();
        loginPage.enterPassword("qwerty");
        loginPage.enter();
        loginPage.getNotificationMessage().shouldHave(text("Неверный пароль"));
        loginPage.getNotificationMessage().shouldHave(cssValue("color","rgba(87, 41, 41, 1)"));
    }
    @Test
    void selectAustraliaPhoneCode() {
        LoginPage loginPage = new LoginPage();
        loginPage.selectAustralia();
        loginPage.getAUPhoneIndex().shouldBe(visible);
        loginPage.getAuFlag().shouldBe(visible);
    }
    @Test
    void searchCountry() {
        LoginPage loginPage = new LoginPage();
        loginPage.selectCountryClick();
        SelenideElement australiaButton = loginPage.getAustraliaButton();
        australiaButton.shouldBe(visible);
        loginPage.searchCountry("Бельгия");
        australiaButton.shouldNotBe(visible);
    }
    @Test
    void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }
}
