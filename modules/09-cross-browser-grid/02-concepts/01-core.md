# Concept: Cross-browser and Grid

A driver factory hides browser-specific construction while tests depend only on WebDriver. Browser options differ, but navigation and assertions should not. RemoteWebDriver sends the same commands to a Grid URL; capabilities describe requirements and Grid chooses a matching node. Parallel tests need separate sessions. Docker Selenium is useful for reproducible browser versions and CI isolation. Always quit remote sessions to return capacity to the Grid.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
