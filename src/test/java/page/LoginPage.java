package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement title = $x("//h4");
    private SelenideElement title2 = $x("//p[contains(@class, \"body2\")]");
    private SelenideElement ruFlag = $x("//span[@data-testid=\"RU\"]");
    private SelenideElement ruPhoneIndex = $x("//div[contains(text(), \"+7\")]");
    private SelenideElement phoneField = $x("//input[@name=\"phone\"]");
    private SelenideElement validationCodeField = $x("//input[@name=\"validationCode\"]");
    private SelenideElement notificationMessage = $x("//div[contains(@class,\"MuiAlert-message\")]");
    private SelenideElement errorMessage = $x("//p[contains(@class,\"error\")]");
    private SelenideElement passwordField = $x("//input[@name=\"password\"]");
    private SelenideElement enterButton = $x("//button[text()=\"Войти\"]");
    private SelenideElement selectCountryButton = $x("//button[@aria-label]");
    private SelenideElement labelPhone = $x("//label[@id=\":r0:-label\"]");
    private SelenideElement labelCode = $x("//label[@id=\":r4:-label\"]");
    private SelenideElement labelPassword = $x("//label[@id=\":r5:-label\"]");
    private SelenideElement australiaButton = $x("//span[contains(text(), \"Австралия\")]");
    private SelenideElement auPhoneIndex = $x("//div[contains(text(), \"+61\")]");
    private SelenideElement auFlag = $x("//span[@data-testid=\"AU\"]");
    private SelenideElement searchField = $x("//input[@id=\":r4:\"]");

    public LoginPage() {
        title.shouldBe(visible);
        title.shouldHave(text("Вход"));
        title2.shouldBe(visible);
        ruFlag.shouldBe(visible);
        ruPhoneIndex.shouldBe(visible);
        title2.shouldHave(text("Авторизация в CRM"));
        labelPhone.shouldHave(text("Телефон"));
    }

    public SelenideElement getValidationCodeField() {
        return validationCodeField;
    }

    public SelenideElement getAustraliaButton() {
        return australiaButton;
    }

    public SelenideElement getAUPhoneIndex() {
        return auPhoneIndex;
    }

    public SelenideElement getAuFlag() {
        return auFlag;
    }

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    public SelenideElement getLabelCode() {
        return labelCode;
    }

    public SelenideElement getLabelPassword() {
        return labelPassword;
    }

    public SelenideElement getNotificationMessage() {
        return notificationMessage;
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    public void enterPhone(String phone) {
        phoneField.setValue(phone);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void enterCode(String code) {
        validationCodeField.setValue(code);
    }

    public void enter() {
        enterButton.click();
    }

    public void selectCountryClick() {
        selectCountryButton.click();
        searchField.shouldBe(visible);
    }

    public void selectAustralia() {
        selectCountryClick();
        australiaButton.click();
    }

    public void searchCountry(String country) {
        searchField.setValue(country);
    }

    public TasksPage login() {
        enterPhone(DataHelper.getCorrectPhone());
        enter();
        enterCode(DataHelper.getCorrectCode());
        enter();
        enterPassword(DataHelper.getCorrectPassword());
        enter();
        return new TasksPage();
    }
}
