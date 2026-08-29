# Concept: Framework architecture

A production framework has seams: ConfigReader loads environment values, DriverFactory creates local or remote drivers, BaseTest owns lifecycle, and pages own UI behavior. Factory selects drivers, strategy selects wait policy, builder creates readable complex data, and facade exposes flows such as loginAs. Apache POI reads Excel and Jackson maps JSON to typed records; validate missing fields early. CI should run named smoke suites and upload reports. Retry only known transient failures and report retries so defects are not hidden.

## Example to write

Implement the idea in a small TestNG test against the relevant demo page. Explain what the assertion proves, what failure means, and which state must be cleaned up.
