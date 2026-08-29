# Concept: Advanced interactions

Actions creates a low-level mouse and keyboard sequence; the chain runs only when perform is called. Verify the resulting application state because a completed input sequence does not prove the UI accepted it. Alerts are outside the DOM and require switchTo().alert(), reading text, then accept or dismiss. Frames have an independent document and require switchTo().frame followed by defaultContent. Tabs are identified by window handles; save the original handle before switching. Cookies and storage are session state and must be cleared when isolation matters.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
