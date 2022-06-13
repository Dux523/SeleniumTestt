package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPage extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();// maximizujemo windows na browseru
        driver.navigate().to(excelReader.getStringData("URLs",1,0));// uzimamo iz excel tabele URL i uz pomoc nje odlazimo na zeljenu stranicu
    }
    @Test
    public void logInTestWithValidCreditencials() throws InterruptedException {
        loginPage.typeUsername(excelReader.getStringData("Login",1,0));
        loginPage.typePassword(excelReader.getStringData("Login",1,1));
        //Iz excel tabele uzimamo username i password i smestamo ih u prethodno napravljenu metodu typeUsername i typePassword.
        loginPage.clickOnLogInButton();
        Assert.assertEquals(secureAreaPage.LoggedInText(), "You logged into a secure area!\n" + "×");
        // Ovde asertujemo poruku "You logged into a secure area", tj proveravamo da li se poklapa sa onim sto zapravo pise na stranici. Test je prosao ako se pojavljuje.
        Assert.assertTrue(secureAreaPage.getLogoutButton().isDisplayed());
        //Ovde asertujemo da li se pojavljuje logout dugme, ako se pojavljuje, test je prosao
        secureAreaPage.clickOnLogoutButton();// ovde se odjavljujemo sa stranice i vracamo na pocetnu
        //Thread.sleep(2000);
        boolean check = false;
        try {
            check = secureAreaPage.getLogoutButton().isDisplayed();
        }catch (Exception e){}
        // try-catch metodom pokusavamo da "uhvatimo" logout dugme iz prethodne stranice, ne bi trebali da ga uhvatimo zato sto to dugme ne postoji
        //na novoj stranici
        Assert.assertFalse(check);
        //proveravamo da zapravo dugme ne postoji i zato asertujemo false jer ne bi trebalo da ga nadje. kada bi ga naslo prijavio bi gresku prilikom testiranja
    }
    @Test
    public void logInTestWithInvalidUsernameAndPassword() throws InterruptedException{
        for(int i = 1; i < excelReader.getLastRow("Login");i++){
        Thread.sleep(1000);
            loginPage.typeUsername(excelReader.getStringData("Login",i,2));
            loginPage.typePassword(excelReader.getStringData("Login",i,3));
            loginPage.clickOnLogInButton();
            //Ovde tadimo test sa neispravnim podacima, tako sto iz excel tabele uzmemo onu kolonu koja ima neispravne podatke, a da bi menjali red
            //napravili smo pretlju koja ce proci kroz svaki red i ubaciti u text polje.

            Assert.assertEquals(loginPage.invalidUsernameMessegeText(),"Your username is invalid!\n" + "×");
            //Ovde proveravamo da nam stize poruka da smo ukucali pogresan username
        }
    }
    @Test
    public void logInTestWithInvalidUsernameAndValidPassword() throws InterruptedException {
        for(int i = 1; i < excelReader.getLastRow("Login");i++){
            Thread.sleep(1000);
            loginPage.typeUsername(excelReader.getStringData("Login",i,2));
            loginPage.typePassword(excelReader.getStringData("Login",1,1));
            loginPage.clickOnLogInButton();
            Assert.assertEquals(loginPage.invalidUsernameMessegeText(),"Your username is invalid!\n" + "×");

        }}
    //Tako radimo i kada bi pojedinacno proveravali sa pogresnim passwordom samo bi assertovali drugi element na kom bi pisalo da je password invalid

    @AfterMethod
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

}