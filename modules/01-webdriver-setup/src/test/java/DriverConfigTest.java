import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/** Examples of configuration choices learners can experiment with safely. */
public class DriverConfigTest {
    @Test
    public void headlessDriverCanReadThePage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=800,600");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("http://localhost:8080/basic/hello.html");
            assertThat(driver.getTitle()).isEqualTo("Hello Page");
            assertThat(driver.manage().window().getSize().getWidth()).isEqualTo(800);
        } finally {
            driver.quit();
        }
    }
}
