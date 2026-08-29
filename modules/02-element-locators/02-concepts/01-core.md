# Concept: Locators

A locator is the contract between a test and the DOM. Prefer id, name, or data-testid because they represent application identity. CSS is compact for attributes and relationships; XPath is useful for text and moving through ancestors. findElement returns the first match and throws when absent. findElements returns a list and returns an empty list when absent. Relative locators describe layout, so use them only when layout is stable. Example: driver.findElements(By.cssSelector("ul.item-list > li.item")); then assert the list size. Avoid absolute XPath tied to every wrapper and never add a sleep to hide a bad locator.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
