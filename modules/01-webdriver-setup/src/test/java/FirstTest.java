import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.assertThat;

/** A deliberately small, fully runnable example of the WebDriver lifecycle. */
public class FirstTest {
    private static final String BASE_URL = "http://localhost:8080";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // WebDriverManager resolves and caches a compatible ChromeDriver binary.
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--window-size=1280,800");
        driver = new ChromeDriver(options);
    }

    @Test
    public void shouldLoadHelloPage() {
        // Navigation changes the current browser document.
        driver.get(BASE_URL + "/basic/hello.html");

        // Find by a stable id, then assert user-visible behavior.
        WebElement greeting = driver.findElement(By.id("greeting"));
        assertThat(driver.getTitle()).isEqualTo("Hello Page");
        assertThat(greeting.isDisplayed()).isTrue();
        assertThat(greeting.getText()).isEqualTo("Hello, Selenium Learner!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // alwaysRun protects cleanup even when the test fails.
        if (driver != null) {
            driver.quit(); // quit closes every window and ends the session.
        }
    }
}
