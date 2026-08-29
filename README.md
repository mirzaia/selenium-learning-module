# Selenium Java Learning Module

A practical, self-paced Selenium WebDriver course for backend engineers. Each module is an independent Maven project and follows **objectives → concepts → exercises → verification**.

## Prerequisites

Java 17+, Maven 3.9+, Chrome, and Python 3. Start the offline target app with:

```bash
cd demo-app && python3 -m http.server 8080
```

Then open `modules/01-webdriver-setup`, read its layers, and run `mvn test`. WebDriverManager downloads a matching browser driver automatically. Use headless mode in CI.

## Progression

| Module | Focus | Typical time |
|---|---|---:|
| 01 | WebDriver setup and lifecycle | 45 min |
| 02 | Locators, CSS, XPath, relative locators | 60 min |
| 03 | Forms and WebElement interactions | 60 min |
| 04 | Synchronization and waits | 60 min |
| 05 | Actions, alerts, frames, windows | 75 min |
| 06 | Page Object Model | 90 min |
| 07 | TestNG organization and data | 90 min |
| 08 | Reporting, logging, debugging | 75 min |
| 09 | Browsers and Selenium Grid | 75 min |
| 10 | Production framework architecture and CI | 120 min |

Modules 1–4 form the essentials; later modules can be selected by interest. Every module is runnable in isolation and points at `http://localhost:8080`.

## Repository conventions

`02-concepts` explains the technique, `03-exercises` gives deliberately incomplete challenges, and `04-verification/checklist.md` is the learner’s completion record. `src/test/java` contains small reference tests that can be used as a safety net; learners should first attempt the exercises themselves.

## Troubleshooting

If tests cannot connect, confirm the Python server is running. If Chrome is unavailable, install Chrome or adapt the driver factory to Firefox. Delete a module’s `target/` directory only when you need a clean build.
