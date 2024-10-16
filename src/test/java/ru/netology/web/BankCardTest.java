package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankCardTest {
    private WebDriver driver;

    @BeforeEach
    public void setupAll() {
        WebDriverManager.chromedriver().setup(); // Инициализация WebDriverManager
        ChromeOptions options = new ChromeOptions(); // Создание нового объекта ChromeOptions
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }


    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    void shouldTestV1() { // Фамилия + пробел + имя // Вводим имя
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        // Вводим телефон
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
        // Соглашаемся с условиями
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        // Отправляем форму
        driver.findElement(By.className("button")).click();
        // Проверяем сообщение об успешной отправке
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
