package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    WebElement usernameTextbox;
    WebElement passwordTextBox;
    WebElement logInButton;
    WebElement usernameIsInvalidMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getUsernameTextbox() {
        return driver.findElement(By.id("username"));
    }
    public WebElement getPasswordTextBox() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLogInButton() {
        return driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
    }
    public WebElement getUsernameIsInvalidMessage() {
        return driver.findElement(By.id("flash"));
    }


    //-----------------------------------------------------
    public void typeUsername(String username){
        getUsernameTextbox().click();
        getUsernameTextbox().sendKeys(username);
    }
    public void typePassword(String password){
        getPasswordTextBox().clear();
        getPasswordTextBox().sendKeys(password);
    }
    public void clickOnLogInButton(){
        getLogInButton().click();
    }
    public String invalidUsernameMessegeText(){
        return getUsernameIsInvalidMessage().getText();
    }

}
