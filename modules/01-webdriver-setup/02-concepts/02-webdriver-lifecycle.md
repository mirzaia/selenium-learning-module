# WebDriver lifecycle

The stable sequence is: construct a driver, navigate, locate/interact, assert, then quit. `close()` closes one window; `quit()` ends the whole session and releases the driver service. Prefer `@BeforeMethod`/`@AfterMethod` for isolation.
