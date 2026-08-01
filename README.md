# OrangeHRM Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange) ![Selenium](https://img.shields.io/badge/Selenium-4.x-green) ![TestNG](https://img.shields.io/badge/TestNG-7.x-red) ![Maven](https://img.shields.io/badge/Maven-3.9.x-blue) ![ExtentReports](https://img.shields.io/badge/ExtentReports-5.x-purple)

A production-grade, scalable Selenium WebDriver automation framework for [OrangeHRM](https://opensource-demo.orangehrmlive.com/) built with Java, TestNG, Maven, and Page Object Model.

---

## 📋 Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reports & Logs](#reports--logs)
- [Framework Features](#framework-features)
- [Jenkins CI/CD](#jenkins-cicd)
- [Contributing](#contributing)

---

## 🛠️ Technologies Used

| Tool              | Version   | Purpose                         |
|-------------------|-----------|---------------------------------|
| Java JDK          | 17+       | Programming Language            |
| Selenium WebDriver| 4.28.x    | Browser Automation              |
| TestNG            | 7.10.x    | Test Framework                  |
| Maven             | 3.9.x     | Build & Dependency Management   |
| WebDriverManager  | 5.9.x     | Browser Driver Management       |
| Extent Reports    | 5.1.x     | HTML Test Reporting             |
| Log4j2            | 2.23.x    | Execution Logging               |
| Apache POI        | 5.2.x     | Excel Data-Driven Testing       |
| Apache Commons IO | 2.16.x    | File Utilities                  |

---

## 📁 Project Structure

```
OrangeHRM-Automation-Framework/
├── src/
│   ├── main/
│   │   ├── java/com/orangehrm/
│   │   │   ├── base/
│   │   │   │   ├── BaseTest.java         # Test lifecycle management
│   │   │   │   └── DriverFactory.java    # ThreadLocal WebDriver factory
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── DashboardPage.java
│   │   │   │   ├── AdminPage.java
│   │   │   │   ├── PIMPage.java
│   │   │   │   ├── LeavePage.java
│   │   │   │   ├── RecruitmentPage.java
│   │   │   │   ├── BuzzPage.java
│   │   │   │   └── LogoutPage.java
│   │   │   ├── utilities/
│   │   │   │   ├── ConfigReader.java
│   │   │   │   ├── ExcelUtility.java
│   │   │   │   ├── WaitUtility.java
│   │   │   │   ├── ScreenshotUtility.java
│   │   │   │   ├── JavaUtility.java
│   │   │   │   └── ReportManager.java
│   │   │   └── listeners/
│   │   │       └── TestListener.java     # TestNG ITestListener
│   │   └── resources/
│   │       ├── config.properties         # App configuration
│   │       └── log4j2.xml                # Logging configuration
│   └── test/
│       ├── java/com/orangehrm/tests/
│       │   ├── LoginTest.java
│       │   ├── DashboardTest.java
│       │   ├── AdminTest.java
│       │   ├── PIMTest.java
│       │   ├── LeaveTest.java
│       │   ├── RecruitmentTest.java
│       │   ├── BuzzTest.java
│       │   └── LogoutTest.java
│       └── resources/data/
│           └── EmployeeData.xlsx         # Excel test data
├── Reports/                              # Extent HTML Reports
├── Screenshots/                          # Failure screenshots
├── Logs/                                 # Log4j2 rolling logs
├── pom.xml
├── testng.xml
└── README.md
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Java JDK 17 or higher
- Apache Maven 3.9+
- Google Chrome / Firefox / Edge browser
- IntelliJ IDEA or Eclipse
- Git

### Clone & Install

```bash
# Clone the repository
git clone https://github.com/kishore-9000/OrangeHRM-Automation-Framework.git
cd OrangeHRM-Automation-Framework

# Install dependencies
mvn clean install -DskipTests
```

---

## 🔧 Configuration

Edit [`src/main/resources/config.properties`](src/main/resources/config.properties):

```properties
url=https://opensource-demo.orangehrmlive.com/
browser=chrome          # chrome | firefox | edge
username=Admin
password=admin123
timeout=20
headless=false          # true for headless execution (CI/CD)
```

---

## ▶️ Running Tests

### Run All Tests (Full Regression Suite)

```bash
mvn clean test
```

### Run Smoke Tests Only

```bash
mvn clean test -Dgroups=smoke
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=LoginTest
```

### Run on Different Browser

```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run in Headless Mode

```bash
mvn clean test -Dheadless=true
```

---

## 📊 Reports & Logs

| Artifact         | Location                       | Description                        |
|-----------------|--------------------------------|------------------------------------|
| Extent Report   | `Reports/ExtentReport_*.html`  | Full HTML execution report         |
| Failure Screenshots | `Screenshots/`            | PNG screenshots of failed tests    |
| Execution Logs  | `Logs/orangehrm.log`           | Rolling Log4j2 log file            |
| TestNG Output   | `test-output/`                 | Default TestNG HTML/XML reports    |

Open the Extent HTML report in any browser for detailed pass/fail analysis with screenshots.

---

## 🌟 Framework Features

| Feature                    | Implementation                          |
|----------------------------|-----------------------------------------|
| Page Object Model (POM)    | Separate Page classes per module        |
| Thread-safe WebDriver      | `ThreadLocal<WebDriver>` in DriverFactory |
| Cross-Browser Testing      | Chrome, Firefox, Edge via WebDriverManager |
| Data-Driven Testing        | Apache POI reading from `.xlsx` files   |
| Detailed HTML Reports      | Extent Reports 5.x with Dark theme     |
| Logging                    | Log4j2 rolling file + console appender |
| Failure Screenshots        | Auto-captured via TestListener          |
| Parallel Execution Ready   | `parallel` setting in testng.xml        |
| Easy Configuration         | Centralized `config.properties`         |
| CI/CD Ready                | Maven + Jenkins integration             |

---

## 🔧 Jenkins CI/CD

### Jenkins Pipeline Setup

1. Create a **New Pipeline** job in Jenkins
2. Under **Source Code Management**, add your GitHub repository URL
3. Set up the following **Build Step**:

```bash
mvn clean test
```

4. Add **Post-Build Action** → Publish HTML reports from `Reports/`
5. Archive artifacts: `Reports/**/*.html`, `Screenshots/**/*.png`

### Jenkinsfile (Declarative Pipeline)

```groovy
pipeline {
    agent any
    tools {
        maven 'Maven 3.9'
        jdk 'JDK 17'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'Reports',
                    reportFiles: '*.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'Reports/**/*.html, Screenshots/**/*.png', allowEmptyArchive: true
        }
    }
}
```

---

## 🤝 Contributing

1. Fork this repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request

---

## 📝 License

This project is developed for educational and interview demonstration purposes.

---

## 🙋‍♂️ Interview Explanation

> "I developed an end-to-end Selenium Automation Framework for the OrangeHRM application using Java, Selenium WebDriver, TestNG, and Maven. The framework follows the Page Object Model design pattern to improve code reusability and maintainability. Configuration values are managed using a properties file, while test data is read from Excel using Apache POI. WebDriverManager handles browser drivers automatically. Extent Reports provide detailed HTML execution reports, and Log4j2 captures execution logs. Screenshots are automatically taken whenever a test fails. The framework supports Chrome, Firefox, and Edge browsers, integrates with Git for version control, and can be executed through Jenkins for Continuous Integration."
