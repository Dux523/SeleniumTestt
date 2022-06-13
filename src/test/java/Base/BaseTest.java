package Base;

import Pages.LoginPage;
import Pages.SecureAreaPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    public WebDriver driver;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public SecureAreaPage secureAreaPage;
//pravimo atribute za svaku klasu
    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        excelReader = new ExcelReader("C:\\Users\\Ryzen\\Desktop\\SeleniumTest.xlsx");
        loginPage = new LoginPage(driver);
        secureAreaPage = new SecureAreaPage(driver);
    }
    @AfterClass
    public void tesrDown(){
        driver.close();
        driver.quit();
    }
    //ovom metodom zatvaramo i gasimo browser
}
