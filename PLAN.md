# Selenium Java Learning Module — Implementation Plan v2

> **This is the active plan.** It supersedes the earlier CLI-based approach (v1) archived at `.hermes/plans/2026-08-02_000000-selenium-cli-tutorial.md`.
> Exported from `.hermes/plans/2026-08-02_121500-selenium-java-maven-modules.md` on 2026-08-02.
> For Hermes: Use subagent-driven-development skill to implement this plan task-by-task.

**Goal:** Build a markdown-based, self-paced learning module (10 progressive modules) teaching Selenium WebDriver in Java — from basic setup to enterprise framework architecture — targeting medium-experience backend engineers.

**Architecture:** Each module is a self-contained Maven project with its own `pom.xml`. Concepts and exercises are delivered as markdown files following a 4-layer pattern: objectives → concepts → exercises → verification. A shared `demo-app/` directory of static HTML pages (served via Python's built-in HTTP server) provides a stable, offline target for all exercises. No CLI app, no YAML engine, no embedded server — plain files that a learner opens in their IDE and follows.

**Tech Stack:** Java 17+, Maven, Selenium WebDriver 4.x, WebDriverManager (driver auto-management), TestNG (testing framework), Extent Reports (reporting), Log4j2 (logging), Apache POI (data-driven), Python http.server (demo app serving, built into macOS/Linux)

---

## Comparison: Existing Plan vs v2 Requirements

| Dimension | Existing Plan | v2 Plan (this document) |
|---|---|---|
| Delivery | CLI application (JLine, YAML engine, embedded Jetty) | Markdown files + self-contained Maven projects |
| Complexity | High — builds a custom app to deliver content | Low — learner reads markdown, writes code in their IDE |
| Module structure | Monolithic Gradle project | 10 independent Maven modules + 1 shared demo-app |
| Content style | YAML lesson definitions with snippets | 4-layer: objectives → concepts → exercises → verification (matches reference project) |
| Audience | General (explains "what is Selenium") | Backend engineers (assumes prior knowledge, focuses on HOW) |
| Depth | 5 basic lessons + 5 stubbed advanced | 10 detailed modules: 4 basic, 4 intermediate, 2 advanced |
| Demo app | Embedded Jetty (Java) | Static HTML served by Python http.server |
| Exercise validation | Auto-validation engine (deferred) | Self-verification checklists + TestNG assertions |
| Progress tracking | JSON file via custom ProgressTracker | Learner self-tracks via verification checklists |

**Key design decisions driven by user requirements:**
- "Not complex and over-engineered" → stripped CLI, YAML engine, embedded Jetty. Plain files.
- Reference project structure adopted exactly: objectives/concepts/exercises/verification
- Maven over Gradle: enterprise standard, familiar to backend engineers
- 10 modules vs 5: fulfills "basic to advanced" requirement

---

## Project Structure

```
selenium-learning-module/
├── README.md                          # Entry point — how to use this module
├── demo-app/                          # Shared HTML pages all modules target
│   ├── index.html                     # Navigation hub
│   ├── basic/
│   │   ├── hello.html                 # Module 1: basic page
│   │   ├── locators.html              # Module 2: locator practice
│   │   ├── form.html                  # Module 3: form interactions
│   │   └── slow-page.html             # Module 4: wait practice
│   ├── intermediate/
│   │   ├── drag-drop.html             # Module 5: Actions class
│   │   ├── alerts.html                # Module 5: alerts
│   │   ├── iframe-container.html      # Module 5: iframes
│   │   ├── iframe-content.html         
│   │   ├── login.html                 # Module 6: POM target
│   │   └── dashboard.html             # Module 6: POM target
│   └── advanced/
│       ├── data-table.html            # Module 10: data-driven
│       └── ecommerce/                 # Module 10: complex multi-page
│           ├── products.html
│           ├── cart.html
│           └── checkout.html
├── modules/
│   ├── 01-webdriver-setup/
│   │   ├── README.md
│   │   ├── 01-objectives/README.md
│   │   ├── 02-concepts/
│   │   │   ├── 01-maven-selenium-setup.md
│   │   │   └── 02-webdriver-lifecycle.md
│   │   ├── 03-exercises/
│   │   │   ├── 01-first-test.md
│   │   │   └── 02-driver-management.md
│   │   ├── 04-verification/checklist.md
│   │   └── pom.xml
│   ├── 02-element-locators/
│   │   ├── ... (same 4-layer pattern)
│   │   └── pom.xml
│   ├── 03-webelement-interactions/
│   ├── 04-synchronization-waits/
│   ├── 05-advanced-interactions/
│   ├── 06-page-object-model/
│   ├── 07-testng-framework/
│   ├── 08-reporting-debugging/
│   ├── 09-cross-browser-grid/
│   └── 10-framework-architecture/
│       └── pom.xml
└── .hermes/
    └── plans/
        └── 2026-08-02_000000-selenium-cli-tutorial.md  # (existing plan — reference only)
```

### Module 4-Layer Pattern

Every module follows this exact structure (matching the reference project):

```
modules/XX-module-name/
├── README.md                    # Module overview, prerequisites, navigation table
├── 01-objectives/README.md      # What you'll learn, success criteria, framework connection
├── 02-concepts/
│   ├── 01-topic-a.md            # Concept explanation with code snippets
│   ├── 02-topic-b.md            # Second concept
│   └── 03-topic-c.md            # Third concept (some modules have 2, some have 3)
├── 03-exercises/
│   ├── 01-exercise-a.md         # Hands-on exercise with instructions
│   ├── 02-exercise-b.md         # Second exercise
│   └── 03-exercise-c.md         # Third exercise (optional)
├── 04-verification/checklist.md  # PASS/FAIL checklist, troubleshooting, score
└── pom.xml                       # Maven config with module-specific dependencies
```

---

## Shared Demo App

The demo app is a directory of static HTML pages that all 10 modules exercise against. Served with one command:

```bash
cd demo-app
python3 -m http.server 8080
```

This gives a stable, offline target at `http://localhost:8080/`. No Java server, no Docker — built-in Python on macOS/Linux.

Each module's exercises reference specific pages (e.g., `http://localhost:8080/basic/hello.html`).

---

## Module 01: WebDriver Setup & Lifecycle

**Goal:** Set up a working Selenium + Maven project, understand WebDriver lifecycle, run first test.

**Maven Dependencies:** selenium-java 4.27.0, webdrivermanager 5.9.2, testng 7.10.2, assertj-core 3.27.3

### Objectives

```
01-objectives/README.md
```

By the end of this module, you will be able to:
1. Create a Maven project with Selenium and WebDriverManager dependencies
2. Initialize ChromeDriver using WebDriverManager (no manual driver downloads)
3. Write and run a TestNG test that opens a browser, loads a page, and closes
4. Understand the WebDriver lifecycle: create → navigate → interact → quit

**Framework Connection:** Every Selenium-based test framework starts with reliable driver management. WebDriverManager eliminates the brittle manual driver download step — it auto-detects your Chrome version and downloads the matching driver.

**Success Criteria:** You can run `mvn test` and see a TestNG test pass that opens Chrome, loads a page, reads the title, and quits cleanly.

### Concepts

```
02-concepts/01-maven-selenium-setup.md
```
- What Maven brings to test automation (dependency management, build lifecycle, test execution)
- The `pom.xml` structure for a Selenium project
- Key dependencies: selenium-java, webdrivermanager, testng
- The `maven-surefire-plugin` for running TestNG tests via `mvn test`
- Directory conventions: `src/test/java/` for test code

```
02-concepts/02-webdriver-lifecycle.md
```
- What WebDriver is (W3C protocol, browser-native communication)
- The lifecycle pattern: create driver → navigate → interact → assert → quit
- Why `driver.quit()` matters (orphaned browser processes, port exhaustion)
- WebDriverManager: auto-detection, caching, version matching
- Headless vs headed mode, when to use each
- Common setup code: ChromeOptions configuration

### Exercises

```
03-exercises/01-first-test.md
```
**Objective:** Write and run your first Selenium test.

Instructions:
1. Navigate to `modules/01-webdriver-setup/`
2. Create `src/test/java/com/seleniumlearn/module01/FirstTest.java`
3. Write a test that:
   - Creates a ChromeDriver via WebDriverManager
   - Navigates to `http://localhost:8080/basic/hello.html`
   - Asserts the page title equals "Hello Page"
   - Asserts the greeting text is present
   - Quits the driver
4. Run `mvn test`
5. Verify the test passes

Expected code:
```java
package com.seleniumlearn.module01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Remove to see the browser
        driver = new ChromeDriver(options);
    }

    @Test
    public void shouldLoadHelloPage() {
        driver.get("http://localhost:8080/basic/hello.html");
        assertThat(driver.getTitle()).isEqualTo("Hello Page");
        assertThat(driver.getPageSource()).contains("Hello, Selenium Learner!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

```
03-exercises/02-driver-management.md
```
**Objective:** Practice ChromeOptions and understand driver configuration.

Instructions:
1. Create `src/test/java/com/seleniumlearn/module01/DriverConfigTest.java`
2. Write three tests:
   - Test with `--headless` mode (should run without opening a window)
   - Test with `--window-size=800,600` and verify window size
   - Test that `driver.quit()` actually closes the browser (verify by checking a second `driver.get()` throws)
3. Run `mvn test`

Key ChromeOptions to explore:
- `--headless` — no visible browser window
- `--window-size=WIDTH,HEIGHT` — set viewport
- `--incognito` — private browsing
- `--disable-gpu` — needed on some Windows setups

### Verification

```
04-verification/checklist.md
```
Standard checklist format (matching reference project):
| Step | Command / Check | Expected |
|------|----------------|----------|
| 1 | `cd modules/01-webdriver-setup` | Correct directory |
| 2 | `mvn clean compile` | BUILD SUCCESS |
| 3 | `mvn test` | All tests pass |
| 4 | Check `target/surefire-reports/` | TestNG reports generated |
| 5 | Explain WebDriver lifecycle | Can describe create→navigate→interact→quit |

### Maven POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.seleniumlearn</groupId>
    <artifactId>module-01-webdriver-setup</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.27.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.9.2</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.27.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Module 02: Element Locators

**Goal:** Master all 8 Selenium locator strategies, understand CSS vs XPath trade-offs, and learn Selenium 4 relative locators.

**Prerequisites:** Module 01 complete, demo-app running at `localhost:8080`

**Additional Maven Dependencies:** None (same as Module 01 base)

### Concepts

```
02-concepts/01-locator-strategies.md
```
- The 8 locator strategies: id, name, className, tagName, linkText, partialLinkText, cssSelector, xpath
- When to use each: id > name > cssSelector > xpath > others
- `findElement()` vs `findElements()` — single vs list, NoSuchElementException vs empty list
- The `By` class API

```
02-concepts/02-css-vs-xpath.md
```
- CSS selector cheat sheet: `#id`, `.class`, `[attr=val]`, `parent > child`, `:nth-child()`, `[attr^=prefix]`, `[attr$=suffix]`, `[attr*=substring]`
- XPath cheat sheet: absolute vs relative, axes (parent, ancestor, following-sibling), text(), contains(), starts-with()
- Performance: CSS is faster in most browsers
- When XPath is necessary: finding by text content, navigating up the DOM

```
02-concepts/03-relative-locators.md
```
- Selenium 4 relative locators: `above()`, `below()`, `toLeftOf()`, `toRightOf()`, `near()`
- Practical use cases: finding a label relative to an input, finding a button next to specific text

### Exercises

```
03-exercises/01-basic-locators.md
```
Target page: `http://localhost:8080/basic/locators.html`

Locators page has: button with id, inputs with name, div with data-testid, links, list items with class.

Exercises:
1. Find the button by ID and assert its text
2. Find the input by name and assert its placeholder
3. Find all list items with class "item" and assert count is 3
4. Find the div by data-testid attribute and assert its text
5. Find the link by link text and assert its href
6. Find the button again using partial link text "Cli" and assert it's the same element

```
03-exercises/02-css-xpath-challenge.md
```
Target page: `http://localhost:8080/basic/locators.html`

Exercises:
1. Using CSS: find all `<li>` that are direct children of `<ul class="item-list">`
2. Using CSS: find the element with attribute `data-testid="result-area"`
3. Using XPath: find the link that contains "Go to Form" in its text
4. Using XPath: find the third `<li>` item using position
5. Write the SAME locator in both CSS and XPath for the text input — compare readability

```
03-exercises/03-relative-locators.md
```
Target page: `http://localhost:8080/basic/form.html`

Exercises:
1. Find the email input using `toRightOf()` the "Email:" label
2. Find the submit button using `below()` the country dropdown
3. Find the "I agree to terms" label using `near()` the checkbox

### Verification Checklist

Standard PASS/FAIL format with 8 checks. Scoring: 8/8 = complete.

### Maven POM

Same as Module 01 POM (no new dependencies yet).

---

## Module 03: WebElement Interactions

**Goal:** Master form interactions: text inputs, checkboxes, radio buttons, Select dropdowns, and reading element state.

**Prerequisites:** Module 02, demo-app running

### Concepts

```
02-concepts/01-form-interactions.md
```
- `sendKeys()` — typing text, special keys (Keys.ENTER, Keys.TAB)
- `clear()` — clearing before typing
- `click()` — buttons, links, checkboxes, radios
- `submit()` — form submission (less common, `.click()` on submit button is preferred)
- Reading: `getText()`, `getAttribute()`, `getCssValue()`, `getTagName()`
- Element state: `isDisplayed()`, `isEnabled()`, `isSelected()`
- Form validation patterns: fill → submit → verify success message

```
02-concepts/02-select-dropdowns.md
```
- The `Select` helper class for `<select>` elements
- Selection methods: `selectByVisibleText()`, `selectByValue()`, `selectByIndex()`
- Reading: `getFirstSelectedOption()`, `getOptions()`, `getAllSelectedOptions()`
- Multi-select dropdowns: `isMultiple()`, `deselectAll()`

```
02-concepts/03-file-upload-and-javascript.md
```
- File upload with `sendKeys()` (using absolute path)
- When to use `JavascriptExecutor` — scrolling elements into view, clicking hidden elements
- `executeScript()` basics: `arguments[0].scrollIntoView(true)`, `arguments[0].click()`

### Exercises

All exercises target `http://localhost:8080/basic/form.html` (the registration form).

```
03-exercises/01-form-basics.md
```
1. Fill all text fields and assert values persist
2. Select radio button, assert it's selected
3. Check checkbox, assert it's selected
4. Submit form, assert success message appears

```
03-exercises/02-dropdown-challenge.md
```
1. Select "Indonesia" by visible text, assert it's the selected option
2. Select "Japan" by value ("jp"), assert it's selected
3. Count total options in the dropdown (should be 4 including "-- Select --")
4. Programmatically iterate through all options and print their values

```
03-exercises/03-edge-cases.md
```
1. Attempt to click a disabled element — what exception does it throw?
2. Use `JavascriptExecutor` to scroll to and click an element that's off-screen
3. Test what `getAttribute("value")` returns before and after `sendKeys()`
4. Test `isEnabled()` vs `isDisplayed()` on visible vs hidden elements

### Verification

Standard checklist.

### Maven POM

Same base POM (no new deps).

---

## Module 04: Synchronization & Waits

**Goal:** Eliminate flaky tests with proper synchronization — implicit, explicit, and fluent waits.

**Prerequisites:** Module 03, demo-app running

### Concepts

```
02-concepts/01-implicit-waits.md
```
- What is an implicit wait: global timeout for `findElement()`
- How to set: `driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))`
- Pros: one line, works everywhere
- Cons: blunt instrument, slows down all element lookups, mixes poorly with explicit waits
- When to use: rarely (most teams avoid it in production)

```
02-concepts/02-explicit-waits.md
```
- `WebDriverWait` + `ExpectedConditions` — the recommended approach
- Key ExpectedConditions: `visibilityOfElementLocated()`, `elementToBeClickable()`, `presenceOfElementLocated()`, `textToBePresentInElement()`, `invisibilityOfElement()`, `alertIsPresent()`, `titleContains()`, `urlContains()`
- Pattern: `new WebDriverWait(driver, timeout).until(condition)`
- Why explicit waits > implicit waits: targeted, composable, cleaner failures

```
02-concepts/03-fluent-waits.md
```
- FluentWait vs WebDriverWait: polling interval, exception ignoring, custom conditions
- Building a reusable wait utility (WaitUtils helper class)
- Common timing pitfalls: AJAX spinners, animation delays, race conditions
- Anti-patterns: `Thread.sleep()` — why it's wrong

### Exercises

Target page: `http://localhost:8080/basic/slow-page.html`

The slow-page has:
- An element that appears after 3 seconds via setTimeout
- A button that reveals hidden text after 1.5 seconds
- An AJAX-simulated element (appears after a random 2-5 second delay)

```
03-exercises/01-implicit-vs-explicit.md
```
1. Without any wait: try to find `#delayed` immediately — expect NoSuchElementException
2. With implicit wait (5s): find `#delayed` and assert its text
3. With explicit wait (WebDriverWait): wait for `#delayed` visibility and assert
4. Compare: which gives a better error message on failure?

```
03-exercises/02-advanced-waiting.md
```
1. Click the "Reveal Hidden Text" button, wait for `#hidden-text` to become visible
2. Wait for `#ajax-content` element to be present AND have non-empty text
3. Build a reusable `waitForElementAndGet(WebDriver driver, By locator, int timeoutSeconds)` method
4. Write a test that demonstrates the anti-pattern: `Thread.sleep(5000)` vs `WebDriverWait`

```
03-exercises/03-custom-conditions.md
```
1. Create a custom ExpectedCondition that waits for an element's text to change from X to Y
2. Use FluentWait with polling every 500ms, ignoring NoSuchElementException
3. Write a test that proves Thread.sleep(1000) is slower than a 10ms WebDriverWait

### Verification

Standard checklist + explicit wait quiz.

### Maven POM

Same base POM.

---

## Module 05: Advanced Interactions

**Goal:** Master the Actions API for complex user interactions, plus alerts, iframes, and window management.

**Prerequisites:** Module 04

### Concepts

```
02-concepts/01-actions-class.md
```
- What the Actions class is: low-level keyboard + mouse simulation
- `moveToElement()` — hover
- `dragAndDrop()` / `dragAndDropBy()` — drag operations
- `contextClick()` — right-click
- `doubleClick()` — double-click
- `keyDown()` + `keyUp()` — modifier keys (Ctrl, Shift, Alt)
- `sendKeys()` via Actions — typing into specific elements
- Building composite actions: `actions.moveToElement(el).click().keyDown(Keys.SHIFT).sendKeys("text").keyUp(Keys.SHIFT).perform()`
- `build()` vs `perform()` — immutable chain, execute only on perform()

```
02-concepts/02-alerts-iframes-windows.md
```
- JavaScript alerts: `driver.switchTo().alert()`, `accept()`, `dismiss()`, `getText()`, `sendKeys()`
- iFrames: `driver.switchTo().frame(indexOrNameOrElement)`, `switchTo().defaultContent()`, nested frames
- Windows/Tabs: `getWindowHandle()`, `getWindowHandles()`, `switchTo().window(handle)`, `close()` vs `quit()`
- Tab-specific state: each tab has independent navigation history

```
02-concepts/03-cookies-and-storage.md`
```
- Reading/writing cookies: `driver.manage().getCookies()`, `addCookie()`, `deleteCookieNamed()`
- LocalStorage and SessionStorage via JavascriptExecutor
- Practical use: sharing auth state between tests

### Exercises

```
03-exercises/01-actions-practice.md
```
Target pages: `http://localhost:8080/intermediate/drag-drop.html` (a simple drag-and-drop puzzle page)

1. Right-click an element and verify context menu
2. Hover over a dropdown menu trigger and assert submenu appears
3. Drag an element from source to target and verify position change
4. Double-click an element and verify it toggles state
5. Hold Shift and click multiple elements (multi-select simulation)

```
03-exercises/02-alerts-and-iframes.md
```
Target pages: `http://localhost:8080/intermediate/alerts.html`, `http://localhost:8080/intermediate/iframe-container.html`

1. Trigger a simple alert, read its text, accept it
2. Trigger a confirm dialog, dismiss it, verify the page state reflects dismissal
3. Trigger a prompt dialog, type into it, accept, verify the page shows the typed text
4. Switch into an iframe, interact with elements inside, switch back to main content
5. Handle nested iframes (iframe within iframe)

```
03-exercises/03-window-management.md
```
1. Open a link in a new tab (target="_blank"), switch to it, verify content, close tab, switch back
2. Open three tabs, iterate through all window handles, print each page title
3. Switch between tabs by title (build a utility method)
4. Test `driver.close()` vs `driver.quit()` — close one tab vs close entire browser

### Verification

Standard checklist.

### Maven POM

Same base POM (Actions, alerts, iframes are all in selenium-java).

---

## Module 06: Page Object Model

**Goal:** Learn the POM design pattern — the industry standard for maintainable test automation.

**Prerequisites:** Module 05

### Concepts

```
02-concepts/01-pom-basics.md
```
- What POM is: one class per page, encapsulating locators and actions
- Benefits: single source of truth, DRY locators, readable tests, easy maintenance
- Structure: page class with private WebElements (or By locators) + public action methods
- Constructor pattern: `public LoginPage(WebDriver driver) { this.driver = driver; }`
- Method chaining: `loginPage.enterUsername("user").enterPassword("pass").clickLogin()` returning the next page object
- The test vs page separation: tests contain assertions and flow, pages contain element interactions

```
02-concepts/02-page-factory.md
```
- `PageFactory.initElements(driver, this)` — auto-initializes @FindBy elements
- `@FindBy` annotations: `@FindBy(id = "username")`, `@FindBy(css = ".login-btn")`, `@FindBy(xpath = "//button[text()='Submit']")`
- `@FindAll` and `@FindBys` for compound locators
- Lazy initialization: elements are located on first use, not at page construction
- `AjaxElementLocatorFactory` for dynamic pages — waits before locating
- Page Factory vs manual `driver.findElement()`: when to use each

```
02-concepts/03-pom-best-practices.md
```
- Keep assertions out of page objects (move to test layer)
- Return page objects from navigation methods (fluent API)
- Use meaningful method names: `clickSubmit()` not `clickButton1()`
- Handle waits in page objects, not tests
- The BasePage pattern: shared header, footer, navigation methods
- Page components: extracting reusable widgets (search bar, pagination, modal)

### Exercises

Target pages: `http://localhost:8080/intermediate/login.html` and `http://localhost:8080/intermediate/dashboard.html`

The login page has: username input, password input, login button, error message area.
The dashboard page has: welcome message, user menu, logout button.

```
03-exercises/01-build-pom.md
```
1. Create `LoginPage` class with locators and methods: `enterUsername()`, `enterPassword()`, `clickLogin()`, `getErrorMessage()`
2. Create `DashboardPage` class with: `getWelcomeMessage()`, `clickLogout()`
3. Method chaining: `loginPage.clickLogin()` returns `DashboardPage` on success
4. Write a test: successful login → assert welcome message → logout
5. Write a test: failed login → assert error message

```
03-exercises/02-page-factory.md
```
1. Refactor LoginPage to use `@FindBy` and `PageFactory.initElements()`
2. Compare locator management: `@FindBy` vs manual `driver.findElement()`
3. Use `AjaxElementLocatorFactory` with a 10-second timeout
4. Write a test that uses the Page Factory version — identical test logic, different page implementation

```
03-exercises/03-base-page-and-components.md
```
1. Create a `BasePage` with shared navigation methods
2. Extract a reusable component: `NavigationBar` (header menu present on all pages)
3. Write a test that uses the navigation bar component from both LoginPage and DashboardPage
4. Demonstrate DRY: change a locator in one place, all tests still pass

### Verification

Standard checklist.

### Maven POM

Same base POM (no new deps — POM is a pattern, not a library, and PageFactory is in selenium-java).

---

## Module 07: TestNG Framework

**Goal:** Master TestNG for test organization, data-driven testing, parallel execution, and test lifecycle.

**Prerequisites:** Module 06

**New Dependencies:** testng 7.10.2 (already in POM from Module 01)

### Concepts

```
02-concepts/01-testng-annotations.md
```
- TestNG vs JUnit: why TestNG dominates Selenium testing (parameterization, grouping, parallel)
- Annotation lifecycle: `@BeforeSuite` → `@BeforeTest` → `@BeforeClass` → `@BeforeMethod` → `@Test` → `@AfterMethod` → `@AfterClass` → `@AfterTest` → `@AfterSuite`
- `@Test` attributes: `priority`, `dependsOnMethods`, `groups`, `enabled`, `timeOut`, `invocationCount`, `threadPoolSize`, `dataProvider`, `expectedExceptions`
- `@BeforeMethod` vs `@BeforeClass`: per-test setup (fresh driver) vs per-class setup (shared driver)
- Priority and dependency ordering

```
02-concepts/02-data-providers.md
```
- `@DataProvider`: the cleanest way to run the same test with different data
- Data provider syntax: returning `Object[][]` or `Iterator<Object[]>`
- Named data providers: `@Test(dataProvider = "loginData")`
- External data sources: Excel (Apache POI), CSV, JSON
- Data provider with custom objects (not just primitives)

```
02-concepts/03-parallel-execution.md
```
- testng.xml configuration: `<suite parallel="tests" thread-count="3">`
- Parallel modes: `methods`, `tests`, `classes`, `instances`
- Thread safety with WebDriver: use ThreadLocal or per-method driver
- Pitfalls: shared state, file system conflicts, port conflicts
- Running with Maven: `mvn test -DsuiteXmlFile=testng.xml`

```
02-concepts/04-groups-and-listeners.md
```
- Test groups: smoke, regression, integration — tag tests and run subsets
- `@Test(groups = {"smoke", "login"})`
- Group dependencies and group-of-groups
- ITestListener interface: `onTestSuccess`, `onTestFailure`, `onTestSkipped`, `onStart`, `onFinish`
- ISuiteListener, IInvokedMethodListener
- Practical: custom listener for logging test execution time

### Exercises

```
03-exercises/01-annotation-lifecycle.md
```
1. Create a test class with all annotations printing to System.out — observe execution order
2. Test `priority`: run methods in specified order regardless of method name
3. Test `dependsOnMethods`: assert a dependent test is skipped when prerequisite fails
4. Test `enabled = false`: verify the test is skipped
5. Test `timeOut = 1000`: verify test fails after 1 second
6. Test `expectedExceptions`: verify test passes when expected exception is thrown

```
03-exercises/02-data-driven-testing.md
```
1. Create a `@DataProvider` for login credentials: 5 username/password/expectedResult tuples
2. Run the same login test with all 5 data sets
3. Create a data provider that reads from CSV
4. Create a data provider that returns custom LoginCredential objects
5. Use `@Factory` to create test instances dynamically from a data provider

```
03-exercises/03-parallel-execution.md
```
1. Create testng.xml with `parallel="methods" thread-count="3"`
2. Run 6 test methods in parallel — verify execution time is roughly 1/3 of sequential
3. Implement ThreadLocal WebDriver to make tests thread-safe
4. Run cross-browser tests in parallel (Chrome + Firefox if available)

```
03-exercises/04-groups-and-listeners.md
```
1. Tag tests with `groups = {"smoke"}` and `groups = {"regression"}`
2. Create testng.xml that runs only smoke tests
3. Implement a custom ITestListener that logs test duration
4. Implement a listener that takes a screenshot on failure and saves to `target/screenshots/`
5. Create a custom TestNG reporter that generates a simple HTML summary

### Verification

Standard checklist + testng.xml exercise.

### Maven POM

```xml
<!-- Add to dependencies (most already present from Module 01) -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.2</version>
    <scope>test</scope>
</dependency>

<!-- For CSV data providers -->
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.9</version>
    <scope>test</scope>
</dependency>
```

---

## Module 08: Reporting & Debugging

**Goal:** Build professional test reports and master debugging techniques for Selenium tests.

**Prerequisites:** Module 07

**New Dependencies:** extentreports 5.1.2, log4j-core 2.24.3, log4j-slf4j2-impl 2.24.3, commons-io 2.18.0

### Concepts

```
02-concepts/01-extent-reports.md
```
- Extent Reports: the most popular HTML reporting library for Selenium
- Setup: ExtentSparkReporter + ExtentReports + ExtentTest
- Test lifecycle: `createTest()` → `log(Status.PASS/FAIL/SKIP)` → `flush()`
- Screenshot embedding: `test.addScreenCaptureFromPath(screenshotPath)`
- Categorizing: `assignCategory()`, `assignAuthor()`, `assignDevice()`
- Customizing the report look with ExtentSparkReporter config
- Integration with TestNG ITestListener: auto-log results

```
02-concepts/02-logging.md
```
- Why logging matters in test automation: debugging CI failures at 3am
- Log4j2 setup: `log4j2.xml` configuration
- Log levels: TRACE, DEBUG, INFO, WARN, ERROR, FATAL
- Best practices: log test steps (INFO), log element interactions (DEBUG), log errors (ERROR)
- Structured logging: `logger.info("Filling username field with: {}", username)`
- SLF4J facade: use the interface, not the implementation

```
02-concepts/03-debugging-strategies.md
```
- Screenshot on failure: the single most useful debugging tool — capture page state when test fails
- `TakesScreenshot` interface: `((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)`
- `FileUtils.copyFile()` to save
- Browser DevTools: `driver.getDevTools()` in Selenium 4 — console logs, network requests
- Headless debugging: screenshot the invisible browser
- Breakpoints in IDE: step through Selenium tests
- Common failure patterns and their causes:
  - NoSuchElementException → timing or wrong locator
  - StaleElementReferenceException → DOM refreshed, re-find element
  - ElementNotInteractableException → element hidden/disabled/covered
  - TimeoutException → wait too short or element never appears
  - WebDriverException → browser crashed, driver version mismatch

### Exercises

```
03-exercises/01-extent-reports-setup.md
```
1. Set up ExtentReports with ExtentSparkReporter
2. Create a TestNG ITestListener that auto-logs test results to ExtentReports
3. Run a suite of 5 tests (mix of pass and fail) — verify the HTML report is generated
4. Add screenshots to failed test logs in the report
5. Customize the report: add system info (OS, Java version, browser), add categories

```
03-exercises/02-logging-practice.md
```
1. Set up log4j2 with console + file appender
2. Add INFO logs for every test step
3. Add DEBUG logs for every element interaction (findElement, click, sendKeys)
4. Verify the log file contains the expected entries after test run
5. Set up separate log files: `tests.log` and `errors.log` (threshold-based routing)

```
03-exercises/03-debugging-scenarios.md
```
1. Scenario: NoSuchElementException — add screenshot on failure + wait, then fix
2. Scenario: StaleElementReferenceException — capture, diagnose, refactor with re-find
3. Scenario: ElementNotInteractableException — scroll element into view, fix
4. Scenario: TimeoutException — increase wait, verify element actually exists
5. Use Chrome DevTools to capture console errors during a failing test

### Verification

Standard checklist: verify HTML report generated, log file exists, screenshots captured on failure.

### Maven POM additions

```xml
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>5.1.2</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.24.3</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.24.3</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.18.0</version>
</dependency>
```

---

## Module 09: Cross-Browser Testing & Selenium Grid

**Goal:** Run tests across multiple browsers, set up Selenium Grid for parallel remote execution, and understand Docker Selenium.

**Prerequisites:** Module 07 (TestNG parallel execution)

**New Dependencies:** None (WebDriverManager handles multi-browser driver downloads)

### Concepts

```
02-concepts/01-cross-browser-testing.md
```
- The "why": rendering differences, JS engine differences, user base diversity
- Browser-specific drivers: ChromeDriver, GeckoDriver (Firefox), EdgeDriver, SafariDriver
- WebDriverManager multi-browser: `WebDriverManager.firefoxdriver().setup()`
- Browser-specific options: `ChromeOptions`, `FirefoxOptions`, `EdgeOptions`
- Test parameterization by browser: `@Parameters({"browser"})` in TestNG
- Running the same test against Chrome, Firefox, Edge
- Browser-specific quirks and workarounds
- Cloud providers: Sauce Labs, BrowserStack, LambdaTest (conceptual — no actual account needed)

```
02-concepts/02-selenium-grid.md
```
- What Selenium Grid is: hub + nodes architecture
- Hub receives test commands, distributes to nodes based on capabilities
- Setting up Grid standalone: `java -jar selenium-server-4.x.x.jar standalone`
- RemoteWebDriver: `new RemoteWebDriver(new URL("http://localhost:4444"), capabilities)`
- Node registration and capabilities matching
- Observing test execution on the Grid console (`http://localhost:4444`)
- Parallel execution on Grid: TestNG parallel + Grid distributes to multiple nodes

```
02-concepts/03-docker-selenium.md
```
- Docker Selenium images: `selenium/standalone-chrome`, `selenium/hub`, `selenium/node-chrome`
- docker-compose for Selenium Grid: hub + chrome node + firefox node + edge node
- Advantages: isolated, reproducible, no manual browser installs, CI-friendly
- Practical: `docker-compose.yml` with hub and 3 browser nodes

### Exercises

```
03-exercises/01-cross-browser-basics.md
```
1. Create a BaseTest with `@Parameters({"browser"})` that initializes the correct driver
2. Run the same 3 tests (login, form fill, navigation) on Chrome and Firefox
3. Handle browser-specific options: Chrome headless, Firefox headless
4. Build a `DriverFactory` utility that accepts browser name and returns the right driver type

```
03-exercises/02-selenium-grid-local.md
```
1. Download Selenium Server jar (or use WebDriverManager to get it)
2. Start Grid standalone: `java -jar selenium-server-4.27.0.jar standalone`
3. Convert tests from local WebDriver to RemoteWebDriver
4. Run tests against the Grid and observe execution on the Grid console
5. Run 3 tests in parallel against the Grid — verify they execute concurrently

```
03-exercises/03-docker-grid.md
```
1. Write a `docker-compose.yml` with selenium-hub, chrome-node, firefox-node
2. Start the grid: `docker-compose up -d`
3. Run tests against the Docker grid
4. Scale nodes: `docker-compose up -d --scale chrome-node=3` — run 3 Chrome tests in parallel
5. Observe the Grid console showing all nodes and their current sessions

### Verification

Standard checklist + docker-compose verification.

---

## Module 10: Framework Architecture

**Goal:** Design a production-ready test automation framework using design patterns, data-driven testing with external files, and CI/CD integration.

**Prerequisites:** All previous modules

**New Dependencies:** apache-poi 5.3.0 (Excel), jackson-databind 2.18.2 (JSON)

### Concepts

```
02-concepts/01-design-patterns-for-test-frameworks.md
```
- **Singleton Pattern:** Single WebDriver instance across tests (risky with parallel — use ThreadLocal)
- **Factory Pattern:** DriverFactory — create the right driver based on config (browser, headless, remote)
- **Strategy Pattern:** Different wait strategies based on application characteristics
- **Builder Pattern:** Test data builders for complex objects
- **Facade Pattern:** High-level API over complex Selenium interactions — a `TestActions` class with methods like `loginAs(user)`, `searchFor(term)`
- Favor composition over inheritance: behavior classes (LoginBehavior, SearchBehavior) over deep page hierarchies
- Config-driven framework: `config.properties` for browser, base URL, timeouts, grid URL

```
02-concepts/02-data-driven-testing-with-external-files.md
```
- Excel data sources: Apache POI — reading .xlsx files
- JSON data sources: Jackson — mapping JSON to Java objects
- Data provider that reads from Excel and feeds TestNG tests
- Test data management patterns: test data per test class, shared test data, data versioning
- Keeping test data separate from test logic

```
02-concepts/03-ci-cd-integration.md
```
- GitHub Actions workflow for Selenium tests:
  - Trigger: push, PR, schedule (nightly regression)
  - Setup: Java 17, Chrome, Firefox
  - Run: `mvn test -DsuiteXmlFile=testng-smoke.xml`
  - Report: upload Extent Reports as artifacts
  - Notify: Slack/Teams on failure
- Jenkins pipeline (conceptual — Jenkinsfile example)
- Headless mode for CI, headed for local debugging
- Test environment management: config per environment (dev, staging, prod)
- Retry mechanism: flaky test handling with IRetryAnalyzer

```
02-concepts/04-test-suite-organization.md
```
- Organizing tests: by feature, by risk, by execution frequency
- Smoke tests: run on every push, < 5 minutes, cover critical paths
- Regression tests: run nightly, full coverage
- Integration tests: cross-service validation
- TestNG suite XML structure: master suite → sub-suites → test classes
- Maven profiles: different profiles for different test suites
- Naming conventions: class names, method names, package structure

### Exercises

```
03-exercises/01-build-framework-foundation.md
```
1. Create `config.properties` with: `browser=chrome`, `base.url=http://localhost:8080`, `headless=true`, `timeout=10`
2. Build `ConfigReader` utility using java.util.Properties
3. Build `DriverFactory` using Factory pattern: reads config, creates appropriate driver
4. Build `BaseTest` using Singleton (ThreadLocal) for WebDriver
5. Write a test that uses this framework foundation — config-driven, factory-created driver

```
03-exercises/02-excel-data-driven.md
```
1. Create an Excel file `test-data/login-credentials.xlsx` with 10 user/password/expected result rows
2. Build `ExcelDataReader` with Apache POI
3. Create a TestNG data provider that feeds from the Excel file
4. Run the login test with all 10 data sets
5. Create a JSON-based data reader as alternative and compare the approaches

```
03-exercises/03-github-actions-pipeline.md
```
1. Create `.github/workflows/selenium-tests.yml`:
```yaml
name: Selenium Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with: { java-version: '17', distribution: 'temurin' }
      - uses: browser-actions/setup-chrome@v1
      - name: Start demo app
        run: cd demo-app && python3 -m http.server 8080 &
      - name: Run tests
        run: cd modules/10-framework-architecture && mvn test
      - name: Upload report
        uses: actions/upload-artifact@v4
        with: { name: extent-report, path: modules/10-framework-architecture/target/extent-reports/ }
```
2. Push the workflow and verify it runs (or explain the expected CI behavior)
3. Add a Slack notification step using a webhook action

```
03-exercises/04-suite-organization.md
```
1. Organize tests into packages: `smoke/`, `regression/`, `integration/`
2. Create `testng-smoke.xml`, `testng-regression.xml`, `testng-master.xml`
3. Create Maven profiles for each suite
4. Write a shell script `run-tests.sh` that accepts `smoke|regression|all` and runs the right profile
5. Implement `IRetryAnalyzer` for flaky tests — retry up to 2 times

### Verification

Standard checklist + verify `config.properties`, `testng-smoke.xml`, GitHub Actions YAML.

### Maven POM additions

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.3.0</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.18.2</version>
</dependency>
```

---

## Demo App HTML Pages

The demo app at `demo-app/` needs the following pages. Each should be complete, self-contained HTML with inline CSS.

### Pages needed:

| Page | Path | Used By | Features |
|---|---|---|---|
| Hello | `basic/hello.html` | Module 1 | Title, greeting text |
| Locators | `basic/locators.html` | Module 2 | Button(id), input(name), div(data-testid), links, ul>li with class |
| Form | `basic/form.html` | Module 3, 6 | Text inputs, select dropdown, radios, checkbox, submit, success message |
| Slow Page | `basic/slow-page.html` | Module 4 | setTimeout element, button with delayed reveal, simulated AJAX |
| Drag-Drop | `intermediate/drag-drop.html` | Module 5 | Two draggable divs, drop zone, visual feedback |
| Alerts | `intermediate/alerts.html` | Module 5 | Buttons triggering alert(), confirm(), prompt() |
| iFrame Container| `intermediate/iframe-container.html` | Module 5 | iframe with embedded content page |
| iFrame Content | `intermediate/iframe-content.html` | Module 5 | Elements inside the iframe |
| Login | `intermediate/login.html` | Module 6, 7, 10 | Username/password fields, login button, error area, redirect to dashboard |
| Dashboard | `intermediate/dashboard.html` | Module 6 | Welcome message, user menu, logout |
| Data Table | `advanced/data-table.html` | Module 10 | Sortable table with pagination (HTML+CSS), 20+ rows |
| E-commerce Suite | `advanced/ecommerce/products.html`, `cart.html`, `checkout.html` | Module 10 | Multi-page flow: browse → add to cart → checkout |

### Design constraints for demo pages:
- No external CSS/JS dependencies — all inline
- Consistent styling across pages (clean, modern, dark header + light content)
- Each interactive element must have a unique, stable `id` attribute
- Pages that involve timing (slow-page.html) must use deterministic delays (not random)
- All pages work without internet connection
- Mobile-responsive not required (desktop-only target)

---

## Master README.md

Create at project root with:
1. What this is and who it's for (backend engineers learning Selenium Java)
2. Prerequisites: Java 17+, Maven, Chrome, Python 3 (for demo app)
3. How to get started: clone, start demo app, navigate to Module 1
4. Module progression table
5. How to use: start demo app → cd into module → read concepts → do exercises → verify
6. Structure explanation (4-layer pattern per module)
7. Estimated time per module

---

## Task Breakdown for Implementation

### Task 1: Create project structure and master README
- Create `README.md` with full documentation
- Create directory scaffolding for all 10 modules + demo-app
- Each module gets: `01-objectives/`, `02-concepts/`, `03-exercises/`, `04-verification/` directories

### Task 2: Create shared demo app (all HTML pages)
- Create all 14+ HTML pages with complete interactive elements
- Create `index.html` navigation hub
- Test that all pages load and function correctly

### Task 3: Module 01 — WebDriver Setup & Lifecycle
- All markdown files (objectives, 2 concepts, 2 exercises, verification)
- `pom.xml` with base dependencies
- Exercise solution code (`src/test/java/`)

### Task 4: Module 02 — Element Locators
- All markdown files (objectives, 3 concepts, 3 exercises, verification)
- `pom.xml` (same as Module 01)
- Exercise solution code

### Task 5: Module 03 — WebElement Interactions
- All markdown files
- `pom.xml`
- Exercise solution code

### Task 6: Module 04 — Synchronization & Waits
- All markdown files
- `pom.xml`
- Exercise solution code

### Task 7: Module 05 — Advanced Interactions
- All markdown files
- `pom.xml`
- Exercise solution code
- New demo pages: drag-drop.html, alerts.html, iframe-container.html, iframe-content.html

### Task 8: Module 06 — Page Object Model
- All markdown files
- `pom.xml`
- Exercise solution code (LoginPage, DashboardPage, BasePage, NavigationBar)
- New demo pages: login.html, dashboard.html

### Task 9: Module 07 — TestNG Framework
- All markdown files
- `pom.xml` (add opencsv)
- Exercise solution code + testng.xml examples

### Task 10: Module 08 — Reporting & Debugging
- All markdown files
- `pom.xml` (add extentreports, log4j, commons-io)
- Exercise solution code + log4j2.xml config
- Extent Reports listener

### Task 11: Module 09 — Cross-Browser & Grid
- All markdown files
- `pom.xml`
- Exercise solution code + docker-compose.yml

### Task 12: Module 10 — Framework Architecture
- All markdown files
- `pom.xml` (add apache-poi, jackson)
- Exercise solution code + config.properties, testng suite XMLs, GitHub Actions YAML
- New demo pages: data-table.html, ecommerce pages

---

## Risks & Tradeoffs

| Risk | Mitigation |
|---|---|
| Learner doesn't have Chrome installed | Document prerequisite in README; WebDriverManager auto-downloads ChromeDriver |
| Python not in PATH (demo-app serving) | Document `python3` command; alternative: Java HTTP server class in Appendix |
| Maven not installed | Document prerequisite; provide `mvnw` (Maven Wrapper) in each module |
| Some advanced modules assume Docker | Module 9 (Grid) has Docker as optional — concepts explain without it, exercises use it |
| Too many modules → overwhelming | README recommends Module 1-4 for basics, pick-and-choose for the rest |
| Demo app pages need maintenance across modules | Centralized in `demo-app/` — one source of truth, all modules reference same pages |

## Open Questions

1. Should exercise solution code be included in the module directory (reference-able) or in a separate `solutions/` directory?
   → Recommendation: include in module `src/test/java/` so learners can run `mvn test` to see working code, but concepts/exercises/verification markdown doesn't include full solutions.

2. Should each module have its own Maven Wrapper (`mvnw`)?
   → Recommendation: yes, include `mvnw` + `.mvn/` in each module so no global Maven install required.

3. Should `.gitignore` be global or per-module?
   → Recommendation: root `.gitignore` covering `target/`, `.idea/`, `*.iml` across all modules.

---

## Summary

This plan replaces the CLI-based approach with a simpler, modular, markdown-driven learning module following the reference project's proven 4-layer pattern. 

Key changes from the existing plan:
- **Removed:** CLI app, YAML engine, embedded Jetty, auto-validation engine, ProgressTracker
- **Added:** 5 additional advanced modules (Modules 6-10), self-contained Maven projects, proper content following reference project style
- **Kept:** WebDriverManager, Chrome as primary browser, TestNG, the demo-app concept (simplified to static HTML)

Total deliverables: 10 modules × 4 layers each + 14 demo HTML pages + master README + .gitignore + mvnw wrappers.