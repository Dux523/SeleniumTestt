package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecureAreaPage {
    WebDriver driver;
    WebElement youAreLoggedInMessage;
    WebElement logoutButton;


    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getYouAreLoggedInMessage() {
        return driver.findElement(By.id("flash"));
    }
    public WebElement getLogoutButton() {
        return driver.findElement(By.cssSelector(".icon-2x.icon-signout"));
    }

    //--------------------------------------------
    public String LoggedInText(){
       return getYouAreLoggedInMessage().getText();
    }
    public void clickOnLogoutButton(){
        getLogoutButton().click();
    }
}
