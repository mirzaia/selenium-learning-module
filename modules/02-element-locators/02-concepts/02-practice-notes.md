# Applying the concept

Read the core explanation, inspect the matching HTML page, and predict the result before running a test. Then implement the smallest example in src/test/java.

## Questions to answer

- What browser state does this API read or change?
- What is the expected success signal and what failure would indicate?
- What cleanup is required if the test fails halfway through?
- Which part should be reusable in a framework and which part belongs in the scenario?

## Engineering notes

Use stable selectors and explicit assertions. Prefer a clear failure over a hidden fallback. Keep setup and teardown deterministic, record useful evidence, and run the example repeatedly to expose timing or state leakage. After it works, add one negative case and explain the trade-off in the exercise notes.
