package com.vvs.web.server;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

    @LocalServerPort
    private int localPort;

    private String serverUrl;
    private WebDriver driver;

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initServerUrl() {
        this.serverUrl = "http://localhost:" + localPort;
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void whenInsertText_thenTextIsDisplayed() {
        String message = "Hello";
        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);
        By searchInput = By.id("input-id");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(message);
        By searchButton = By.id("greeting-button");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        assertEquals(message, driver.findElement(By.id("button-pressed"))
                .getText());

    }

    @Test
    public void whenTextIsNotInserted_thenTextIsNotDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);

        By searchButton = By.id("greeting-button");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        assertEquals("Error! Please insert a value", driver.findElement(By.id("button-pressed"))
                .getText());

    }
}
