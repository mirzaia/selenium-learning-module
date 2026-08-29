# Concept: Reporting and debugging

A useful report identifies the test, browser, step, URL, exception, and screenshot. Extent Reports creates a test node, logs pass/fail events, and flushes HTML. ITestListener centralizes this behavior. Use Log4j2: INFO for business steps, DEBUG for selectors, WARN for recoverable conditions, and ERROR for failures. NoSuchElementException suggests a locator or timing issue; stale elements require re-location after DOM refresh; timeout means a condition never became true. Capture screenshots before teardown.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
