# Concept: Page Object Model

A page object owns locators, waits, and user actions; the test owns scenario flow and assertions. This keeps locator changes in one place. Navigation methods should return the next page object, making transitions explicit. Keep assertions out of pages so one page supports multiple scenarios. PageFactory and FindBy reduce boilerplate but manual By fields make waits and re-location clearer. Use BasePage for shared behavior and components for reusable widgets; avoid deep inheritance.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
