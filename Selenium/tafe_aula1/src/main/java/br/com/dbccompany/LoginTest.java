package br.com.dbccompany;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeTest
    public void abrirNavegador(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://automationexercise.com/login");
        driver.manage().window().maximize();
    }

    @Test
    public void deveFazerLoginComSucesso(){
        String btnLogin = "#form  div div div.col-sm-4.col-sm-offset-1 div  form > button";
        String btnLogout = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"login-email\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys("vs@gmail.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-qa=\"login-password\"]")));
        driver.findElement(By.cssSelector("[data-qa=\"login-password\"]")).sendKeys("123456");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnLogin)));
        driver.findElement(By.cssSelector(btnLogin)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnLogout)));
        String textBtnLogout = driver.findElement(By.cssSelector(btnLogout)).getText();

        Assert.assertEquals(textBtnLogout, "Logout");
    }

//    @AfterTest
//    public void fecharNavegador(){
//        driver.quit();
//    }
}