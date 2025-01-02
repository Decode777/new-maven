package com.example.automation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginAutomationTest {
    @Test
    public void testLogin() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://example.com/login");
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            String expectedTitle = "Dashboard";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {
            driver.quit();
        }
    }
}
