# Concept: Synchronization and waits

A browser loads HTML and runs JavaScript asynchronously, so immediate lookup can race the application. Prefer WebDriverWait with a meaningful condition: presence means the node exists, visibility also means it is shown, and elementToBeClickable checks visibility and enabled state. Implicit waits change every lookup globally and are difficult to reason about when mixed with explicit waits. FluentWait adds polling and ignored exceptions. Thread.sleep waits too long when the app is fast and too little when it is slow; it is not synchronization.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
