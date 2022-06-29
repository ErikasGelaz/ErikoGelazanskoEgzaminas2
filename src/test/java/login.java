import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class login {
    static ChromeDriver chrome;
    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", Paths.get("src/test/resources/drivers/chromedriver-2").toString());
        chrome = new ChromeDriver();
    }
    @AfterEach
    void tearDown() {
        chrome.quit();
    }

    @Test
    void loginSuccessful() {
        chrome.get("http://localhost:8080/skaiciuotuvas");
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement username = chrome.findElement(By.xpath("/html/body/div/form/div/input[1]"));
        WebElement password = chrome.findElement(By.xpath("/html/body/div/form/div/input[2]"));
        WebElement loginButton = chrome.findElement(By.xpath("/html/body/div/form/div/button"));

        username.sendKeys("test1");
        password.sendKeys("test1");
        loginButton.click();

        WebElement loginResult = new WebDriverWait(chrome, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/nav/div/ul[2]/a")));
        String expected = "Logout, test1";
        String actual = loginResult.getText();
        Assertions.assertEquals(expected, actual);
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    void loginFail() {
        chrome.get("http://localhost:8080/skaiciuotuvas");
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement username = chrome.findElement(By.xpath("/html/body/div/form/div/input[1]"));
        WebElement password = chrome.findElement(By.xpath("/html/body/div/form/div/input[2]"));
        WebElement loginButton = chrome.findElement(By.xpath("/html/body/div/form/div/button"));

        username.sendKeys("test");
        password.sendKeys("test");
        loginButton.click();

        WebElement loginFailedResult = new WebDriverWait(chrome, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/div/span[2]")));
        String expected = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actual = loginFailedResult.getText();
        Assertions.assertEquals(expected, actual);
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    void registrationSuccessful() throws InterruptedException {
        chrome.get("http://localhost:8080/skaiciuotuvas");
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement getToRegistration = chrome.findElement(By.xpath("/html/body/div/form/div/h4/a"));
        getToRegistration.click();
        Thread.sleep(1000);
        WebElement username = chrome.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password = chrome.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement passwordConfirm = chrome.findElement(By.xpath("//*[@id=\"passwordConfirm\"]"));
        WebElement registrationButton = chrome.findElement(By.xpath("//*[@id=\"userForm\"]/button"));

        username.sendKeys("test14");
        password.sendKeys("test14");
        passwordConfirm.sendKeys("test14");
        registrationButton.click();

        WebElement loginResult = new WebDriverWait(chrome, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/nav/div/ul[2]/a")));
        String expected = "Logout, test9";
        String actual = loginResult.getText();
        Assertions.assertEquals(expected, actual);
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    void registrationFail() throws InterruptedException {
        chrome.get("http://localhost:8080/skaiciuotuvas");
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement getToRegistration = chrome.findElement(By.xpath("/html/body/div/form/div/h4/a"));
        getToRegistration.click();
        Thread.sleep(1000);
        WebElement username = chrome.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password = chrome.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement passwordConfirm = chrome.findElement(By.xpath("//*[@id=\"passwordConfirm\"]"));
        WebElement registrationButton = chrome.findElement(By.xpath("//*[@id=\"userForm\"]/button"));

        username.sendKeys("test3");
        password.sendKeys("test3");
        passwordConfirm.sendKeys("test3");
        registrationButton.click();

        WebElement loginResult = new WebDriverWait(chrome, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"username.errors\"]")));
        String expected = "Toks vartotojo vardas jau egzistuoja";
        String actual = loginResult.getText();
        Assertions.assertEquals(expected, actual);
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    void logoutSuccessful() throws InterruptedException {
        chrome.get("http://localhost:8080/skaiciuotuvas");
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement username = chrome.findElement(By.xpath("/html/body/div/form/div/input[1]"));
        WebElement password = chrome.findElement(By.xpath("/html/body/div/form/div/input[2]"));
        WebElement loginButton = chrome.findElement(By.xpath("/html/body/div/form/div/button"));

        username.sendKeys("test1");
        password.sendKeys("test1");
        loginButton.click();
        Thread.sleep(1000);
        Assertions.assertEquals("Skaičiuotuvas", chrome.getTitle( ));

        WebElement logoutButton = chrome.findElement(By.xpath("/html/body/nav/div/ul[2]/a"));
        logoutButton.click();
        Thread.sleep(1000);

        Assertions.assertEquals("Prisijungimas", chrome.getTitle( ));
    }
}

