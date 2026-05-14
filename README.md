# 🧪 SeleniumCucumberMBDD2

> A production-ready BDD test automation framework built with **Selenium 4**, **Cucumber 7**, **JUnit**, and dual reporting via **Allure** and **ExtentReports** — designed for cross-browser UI testing with failed test re-run support.

---

## 📋 Table of Contents

- [About the Framework](#about-the-framework)
- [Tech Stack](#tech-stack)
- [Framework Architecture](#framework-architecture)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
  - [Run the Full Suite](#run-the-full-suite)
  - [Run by Browser](#run-by-browser)
  - [Run by Tag](#run-by-tag)
  - [Run a Specific Feature](#run-a-specific-feature)
  - [Run Failed Tests Only](#run-failed-tests-only)
  - [Run on All Browsers in Parallel](#run-on-all-browsers-in-parallel)
  - [Run via BAT Script](#run-via-bat-script)
- [Test Reports](#test-reports)
- [Key Features](#key-features)
- [Project Structure](#project-structure)
- [Tags Reference](#tags-reference)
- [Contributing](#contributing)

---

## About the Framework

This framework automates UI testing of web applications using the **Behaviour-Driven Development (BDD)** approach. Test scenarios are written in plain English using **Gherkin syntax**, making them readable by both technical and non-technical stakeholders.

It targets the [DemoQA](https://demoqa.com) application and covers multiple modules including Elements, Widgets, Forms, Interactions, and the BookStore — demonstrating real-world test coverage across a feature-rich web application.

---

## Tech Stack

| Layer | Technology | Version |
|---|---|---|
| Language | Java | 19 |
| Browser Automation | Selenium WebDriver | 4.43.0 |
| BDD Framework | Cucumber | 7.14.0 |
| Test Runner | JUnit 4 (via Vintage Engine) | 5.10.0 |
| Build Tool | Apache Maven | 3.x+ |
| Dependency Injection | PicoContainer | via cucumber-picocontainer |
| Primary Reporting | Allure Reports | 2.29.1 |
| Secondary Reporting | ExtentReports | 5.1.1 |
| Code Utility | Lombok | 1.18.38 |
| Logging | SLF4J Simple | 2.0.9 |

---

## Framework Architecture

```
┌─────────────────────────────────────────────────┐
│               Feature Files (.feature)          │  ← Gherkin scenarios (BDD layer)
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│              Step Definitions (Java)            │  ← Glue code binding Gherkin to actions
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│               Page Object Model                 │  ← Page classes with element locators
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│         Selenium WebDriver (Browser Layer)      │  ← Chrome / Firefox / Edge
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│         Allure Reports + ExtentReports          │  ← Rich HTML test reports
└─────────────────────────────────────────────────┘
```

PicoContainer handles **dependency injection** between step definition classes, keeping shared state (e.g., WebDriver instance) clean and thread-safe across steps.

---

## Prerequisites

Ensure the following are installed before running the framework:

- **Java JDK 19+** — [Download](https://jdk.java.net/19/)
- **Apache Maven 3.6+** — [Download](https://maven.apache.org/download.cgi)
- **Google Chrome**, **Mozilla Firefox**, and/or **Microsoft Edge** (latest versions)
- **Allure CLI** (optional, for serving reports locally) — [Install Guide](https://allurereport.org/docs/install/)

Verify your setup:

```bash
java -version
mvn -version
allure --version   # optional
```

> **Note:** Selenium 4 uses the built-in **Selenium Manager** to automatically download the correct browser driver — no manual WebDriver setup required.

---

## Getting Started

```bash
# 1. Clone the repository
git clone https://github.com/BobbyBrahmam/SeleniumCucumberMBDD2.git

# 2. Navigate into the project
cd SeleniumCucumberMBDD2

# 3. Install dependencies
mvn clean install -DskipTests
```

---

## Running Tests

### Run the Full Suite

Runs all tests on the default browser (Chrome) and generates Allure + Extent reports:

```bash
mvn clean verify -Dtest=myrunner.SuiteRunnerTest
```

---

### Run by Browser

Pass the `-Dbrowser` flag to target a specific browser:

```bash
# Chrome (default)
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=chrome

# Firefox
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=firefox

# Microsoft Edge
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=edge
```

---

### Run by Tag

Filter scenarios using Cucumber tags:

```bash
# Run Regression suite
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dcucumber.filter.tags="@Regression"

# Run smoke tests on Firefox
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=firefox -Dcucumber.filter.tags="@Smoke"

# Combine tags (AND logic)
mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dcucumber.filter.tags="@Regression and @Draft"
```

---

### Run a Specific Feature

```bash
mvn clean verify -Dcucumber.features="src/test/java/com/qa/features/Elements.feature"

# With a tag filter on a specific feature
mvn clean verify \
  -Dbrowser=edge \
  -Dcucumber.features="src/test/java/com/qa/features/Widgets.feature" \
  -Dcucumber.filter.tags="@TestOne"
```

---

### Run Failed Tests Only

After a failed run, a `failedrerun.txt` file is automatically generated listing the failed scenarios. Re-run them without re-running the whole suite:

```bash
mvn clean verify -Dtest=FailedRun

# On a specific browser
mvn clean verify -Dtest=FailedRun -Dbrowser=edge
```

The `failedrerun.txt` file format looks like:
```
file:src/test/java/com/qa/features/Widgets.feature:44
```

---

### Run on All Browsers in Parallel

Launch Chrome, Firefox, and Edge simultaneously in separate terminal windows:

```bash
runAllBrowsers.bat
```

This runs the full suite concurrently across all three browsers — useful for cross-browser regression runs.

---

### Run via BAT Script

The `runTest.bat` script accepts arguments for targeted test execution:

```bash
# Usage
runTest.bat <FeatureFile> <Tag> <Browser> <RunnerClass>

# Examples
runTest.bat Elements.feature @Regression edge SuiteRunnerTest
runTest.bat BookStore.feature @Draft chrome SuiteRunnerTest
```

---

## Test Reports

Reports are automatically generated after every `mvn clean verify` run.

### Allure Report

Allure results are saved to `target/allure-results/`. To view the report:

```bash
# Generate a static report
mvn allure:report
# → Opens at: test-reports/AllureReports/index.html

# Serve the report in a live browser
mvn allure:serve
```

Allure provides rich visuals including test timelines, step-by-step breakdowns, environment info, and failure screenshots.

### ExtentReports

ExtentReports are auto-generated via the grasshopper adapter and saved under `test-reports/`. Open the HTML file directly in any browser — no CLI required.

---

## Key Features

- ✅ **BDD with Gherkin** — human-readable scenarios for all stakeholders
- ✅ **Page Object Model** — clean separation of test logic from UI selectors
- ✅ **PicoContainer DI** — shared driver state without static variables
- ✅ **Cross-browser** — Chrome, Firefox, Edge via runtime `-Dbrowser` flag
- ✅ **Parallel browser execution** — `runAllBrowsers.bat` for simultaneous runs
- ✅ **Failed test rerun** — auto-generated `failedrerun.txt` for targeted reruns
- ✅ **Dual reporting** — Allure (interactive) + ExtentReports (portable HTML)
- ✅ **Tag-based filtering** — run suites by tag (`@Smoke`, `@Regression`, `@Draft`, etc.)
- ✅ **Selenium Manager** — zero manual WebDriver configuration
- ✅ **Maven lifecycle integration** — reports generated on `verify` phase automatically

---

## Project Structure

```
SeleniumCucumberMBDD2/
│
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/qa/
│   │   │   │   ├── features/          ← Gherkin .feature files
│   │   │   │   ├── pages/             ← Page Object classes
│   │   │   │   └── steps/             ← Step Definition classes
│   │   │   ├── myrunner/
│   │   │   │   └── SuiteRunnerTest.java  ← Main Cucumber runner
│   │   │   └── runners/
│   │   │       └── FailedRun.java     ← Failed scenario re-runner
│   │   └── resources/
│   │       ├── failedrerun.txt        ← Auto-generated on test failure
│   │       └── extent.properties      ← ExtentReports config
│
├── test-reports/
│   └── AllureReports/                 ← Generated Allure HTML report
│
├── runTest.bat                        ← Parameterized single-feature runner
├── runAllBrowsers.bat                 ← Parallel cross-browser runner
├── pom.xml                            ← Maven dependencies & plugins
└── README.md
```

---

## Tags Reference

| Tag | Purpose |
|---|---|
| `@Regression` | Full regression suite |
| `@Smoke` | Quick smoke/sanity checks |
| `@Draft` | Work-in-progress scenarios |
| `@TestOne` | Individual scenario targeting |

To add a tag to a scenario:

```gherkin
@Regression @Smoke
Scenario: Verify user can login successfully
  Given the user is on the login page
  When they enter valid credentials
  Then they should be redirected to the dashboard
```

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-new-test`)
3. Write your `.feature` file and step definitions
4. Ensure tests pass locally (`mvn clean verify`)
5. Submit a pull request with a clear description

---

*Built with ❤️ for clean, maintainable test automation.*
