# Concept: WebElement interactions

WebElement combines actions and observations. Clear an input before sendKeys when it may contain existing text; read an input with getAttribute("value"), not getText(). isDisplayed, isEnabled, and isSelected answer different questions and should be asserted after an action. Select is for native select elements and supports visible text, value, and zero-based index. File inputs accept an absolute path through sendKeys. JavaScriptExecutor is an escape hatch for scrolling or storage; bypassing a normal click can hide a usability defect, so use it only after confirming the normal interaction is impossible.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
