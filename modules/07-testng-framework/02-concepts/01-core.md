# Concept: TestNG framework

TestNG configuration methods surround tests from suite and class setup down to method setup and teardown. A fresh driver in BeforeMethod gives isolation; BeforeClass is faster but risks state leakage. DataProvider makes input variation explicit and each row should be independent. Groups such as smoke and regression describe execution intent and suite XML selects them. Parallel methods require isolated drivers, commonly ThreadLocal. Listeners are appropriate for screenshots, timing, and report integration.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
