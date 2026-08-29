# Maven and Selenium setup

Maven supplies repeatable dependencies and a test lifecycle. `selenium-java` is the browser API, WebDriverManager resolves drivers, and Surefire launches TestNG. Test sources conventionally live under `src/test/java`.

Use a Java 17 POM and keep browser configuration close to test setup. In CI add `--headless=new` and `--no-sandbox` where required.
