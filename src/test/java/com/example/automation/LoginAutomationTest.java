package com.example.automation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Use WebDriverManager to handle driver setup
        WebDriverManager.chromedriver().setup();

        // Set up ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Necessary for some ChromeDriver versions
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://www.saucedemo.com/");

            // Use WebDriverWait for element stability
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

            // Perform login
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Wait for the page to load after login and validate title
            wait.until(ExpectedConditions.titleIs("Swag Labs")); // Update with the actual title of the logged-in page
            String actualTitle = driver.getTitle();
            String expectedTitle = "Swag Labs"; // Replace with the actual title
            assertEquals(expectedTitle, actualTitle, "Login failed or page title does not match.");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
