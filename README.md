# MamaCare Backend

**A Maternal Healthcare Management System**

MamaCare is a backend application designed to support maternal healthcare management by providing a structured platform for managing mothers, pregnancies, antenatal care (ANC), healthcare workers, appointments, medications, vaccinations, and pregnancy danger signs.

The backend is developed using **Java 21, Spring Boot, PostgreSQL, Maven, Domain-Driven Design (DDD), and Hexagonal Architecture**.

## Project Status

**Phase 1: Project Setup and Configuration — Build Verified**

The foundational development environment has been configured successfully. Maven compilation, automated context testing, database connectivity, and application packaging have been verified.

Application startup and browser verification are the remaining checks before Phase 1 can be considered fully verified.

---

## 1. Technology Stack

| Technology | Version / Purpose |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.1 |
| Maven | Dependency management and build automation |
| PostgreSQL | 17.11 |
| Spring Web | REST API development |
| Spring Data JPA | Database persistence |
| Hibernate ORM | Object-relational mapping |
| Jakarta Validation | Input validation |
| Lombok | Reduction of repetitive Java code |
| JUnit | Automated testing |
| Git | Version control |
| IntelliJ IDEA | Development environment |

## 2. Architecture

MamaCare follows a **Feature-Oriented Domain-Driven Design (DDD) and Hexagonal Architecture** approach.

The architecture separates business rules, application use cases, incoming interfaces, and external infrastructure.

### Architectural Principles

- **Domain Layer:** Contains business entities, value objects, domain rules, and repository contracts.
- **Application Layer:** Coordinates business use cases and application workflows.
- **Infrastructure Layer:** Implements persistence and integrations with external systems.
- **Inbound Adapters:** Expose application functionality through REST APIs.
- **Outbound Adapters:** Connect application logic to databases and external services.

The architecture encourages separation of concerns, maintainability, testability, and independence of business logic from infrastructure frameworks.

### Planned Main Features

1. Mother Management
2. Pregnancy Management
3. Healthcare Worker Management
4. Appointment Management
5. Antenatal Care (ANC)
6. Medication Management
7. Vaccination Management
8. Danger Sign Management
9. Shared Components

The initial implementation will remain a simple, modular Spring Boot application. Additional infrastructure technologies will only be introduced when justified by actual requirements.

## 3. Project Structure

The backend uses the base package:

`com.mamacare`

The planned feature-oriented structure is illustrated below. Most feature-specific packages will be implemented in subsequent phases.

```text
mamacare/
├── .mvn/
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mamacare/
│   │   │           ├── MamacareApplication.java
│   │   │           │
│   │   │           ├── mother/
│   │   │           ├── pregnancy/
│   │   │           ├── healthcareworker/
│   │   │           ├── appointment/
│   │   │           ├── anc/
│   │   │           ├── medication/
│   │   │           ├── vaccination/
│   │   │           ├── dangersign/
│   │   │           └── shared/
│   │   │
│   │   └── resources/
│   │       └── application.yml
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── mamacare/
│                   └── MamacareApplicationTests.java
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

Each business feature will be developed using the appropriate domain, application, and infrastructure packages as the project progresses.

## 4. Phase 1 — Project Setup and Configuration

### Objective

Establish a working backend development environment before implementing the business features.

### Completed Setup Activities

- Created the MamaCare Spring Boot project.
- Configured Java 21.
- Configured Maven and the Maven Wrapper.
- Added the foundational Spring Boot dependencies.
- Established the base package `com.mamacare`.
- Installed and configured PostgreSQL.
- Created the `mamacare_db` database and `mamacare_user` database account.
- Configured database connectivity.
- Configured Hibernate and JPA.
- Configured environment-variable-based database credentials.
- Configured IntelliJ IDEA.
- Prepared `.gitignore` for version control.
- Successfully compiled, tested, and packaged the application.

## 5. Prerequisites

Before running the project, install:

- Java Development Kit (JDK) 21
- PostgreSQL
- Git
- IntelliJ IDEA or another Java-compatible IDE

Maven does not need to be installed globally because the project includes the Maven Wrapper.

### Verify Java

```powershell
java -version
```

Expected Java major version:

```text
21
```

### Verify Maven Wrapper

On Windows:

```powershell
.\mvnw.cmd -version
```

Ensure that Maven is using Java 21.

### Verify Git

```powershell
git --version
```

## 6. Database Configuration

MamaCare uses PostgreSQL as its relational database.

### Database Details

| Property | Value |
|---|---|
| Database engine | PostgreSQL |
| Database name | `mamacare_db` |
| Application user | `mamacare_user` |
| Host | `localhost` |
| Port | `5432` |

The application should use its dedicated database account rather than the PostgreSQL administrator account.

### Create the Database

Connect as a PostgreSQL administrator and run the following SQL commands if the database and user have not already been created:

```sql
CREATE USER mamacare_user
WITH PASSWORD 'replace_with_secure_password';

CREATE DATABASE mamacare_db
OWNER mamacare_user;
```

Use a strong local development password and keep it outside version control.

## 7. Spring Boot Configuration

The application configuration is located at:

`src/main/resources/application.yml`

```yaml
spring:
  application:
    name: mamacare

  datasource:
    url: jdbc:postgresql://localhost:5432/mamacare_db
    username: mamacare_user
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080
```

### Important Configuration Properties

**`spring.application.name`**

Sets the application name to `mamacare`.

**`spring.datasource.url`**

Specifies the PostgreSQL database connection URL.

**`spring.datasource.username`**

Identifies the database account used by the application.

**`spring.datasource.password`**

Reads the password from the `DB_PASSWORD` environment variable.

**`spring.jpa.hibernate.ddl-auto: validate`**

Instructs Hibernate to validate the database schema against mapped entities without automatically creating or modifying tables.

**`spring.jpa.show-sql: true`**

Displays generated SQL statements during development.

**`server.port: 8080`**

Configures the HTTP port used by the application.

## 8. Environment Variables

The database password is not stored directly in `application.yml`.

Instead, MamaCare uses:

```yaml
password: ${DB_PASSWORD}
```

### Configure in PowerShell

```powershell
$env:DB_PASSWORD = Read-Host "Enter database password"
```

This sets the password for the current PowerShell session.

### Configure in IntelliJ IDEA

1. Open **Run → Edit Configurations**.
2. Select the MamaCare application configuration.
3. Locate **Environment variables**.
4. Add `DB_PASSWORD` with the database user's password.
5. Apply and save the configuration.

**Security:** Passwords, credentials, and other secrets must not be committed to Git.

## 9. Building the Application

Open a terminal in the project root.

### Clean and Package

On Windows:

```powershell
.\mvnw.cmd clean package
```

This command:

1. Removes previous build output.
2. Compiles Java source files.
3. Compiles and executes automated tests.
4. Packages the application into an executable JAR.

### Expected Result

```text
[INFO] BUILD SUCCESS
```

The generated application artifact is located at:

```text
target/mamacare-0.0.1-SNAPSHOT.jar
```

## 10. Running MamaCare

Ensure PostgreSQL is running and the `DB_PASSWORD` environment variable has been configured.

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, use IntelliJ IDEA's Run configuration.

### Expected Startup Messages

```text
Tomcat started on port 8080
Started MamacareApplication
```

Once the application has started successfully, access:

`http://localhost:8080`

At this stage, the application has no implemented business REST controllers.

Therefore, a `404 Not Found` response at the root URL is expected.

## 11. Testing and Verification

The initial Spring Boot context test is located at:

```text
src/test/java/com/mamacare/MamacareApplicationTests.java
```

The test verifies that the Spring application context can initialise successfully.

### Run Tests

```powershell
.\mvnw.cmd test
```

### Verified Phase 1 Build Results

The successful verification build was performed on **8 October 2026**.

| Verification | Result |
|---|---|
| Java 21 compilation | Passed |
| Maven build | Passed |
| PostgreSQL JDBC connection | Passed |
| Hibernate/JPA initialisation | Passed |
| Spring Boot application context test | Passed |
| Executable JAR packaging | Passed |

### Recorded Test Results

```text
Tests run: 1
Failures: 0
Errors: 0
Skipped: 0
```

### Recorded Build Result

```text
BUILD SUCCESS
Total time: 27.332 s
```

The successful build confirms that the project can compile, establish its database connection, initialise its Spring context during testing, and produce a packaged JAR.

## 12. Version Control

Git is used to track project changes.

### Initialise Git

If the repository has not already been initialised:

```powershell
git init
```

### Check Repository Status

```powershell
git status
```

### Recommended Git Ignore Rules

The `.gitignore` file should exclude generated build files, IDE configuration, logs, local environment files, and secrets.

```gitignore
# Maven
target/

# IntelliJ IDEA
.idea/
*.iml

# Eclipse / STS
.classpath
.project
.settings/

# VS Code
.vscode/

# Environment files
.env
.env.*
!.env.example

# Local configuration
application-local.yml
application-local.yaml
application-local.properties

# Logs
*.log
logs/

# Operating system
.DS_Store
Thumbs.db
```

### Make an Initial Commit

After checking that no sensitive files are staged:

```powershell
git add .
git commit -m "chore: complete MamaCare phase 1 setup"
```

## 13. Known Warnings

During Phase 1 verification, the application produced some non-blocking warnings.

These included:

- Hibernate's default Open Session in View setting.
- Mockito dynamically attaching a Java agent during tests.
- JDK warnings associated with dynamic agent loading.

These warnings did not prevent the Maven build from completing successfully.

They can be addressed as test configuration and application development progress.

## 14. Phase 1 Verification Checklist

- [x] Initialise the Spring Boot project
- [x] Configure Java 21
- [x] Configure Maven
- [x] Set up core dependencies
- [x] Establish the base package
- [x] Configure PostgreSQL
- [x] Configure database authentication
- [x] Configure `application.yml`
- [x] Configure environment variables
- [x] Prepare Git configuration
- [x] Compile the application
- [x] Run the initial application context test
- [x] Establish PostgreSQL connectivity
- [x] Generate the application JAR
- [ ] Verify standalone application startup
- [ ] Verify localhost HTTP response

## 15. Next Phase — Domain Modelling

**Phase 2: Domain Modelling**

The next phase will introduce the core business models and begin implementing MamaCare's domain.

The development process will focus on:

- Identifying core domain entities.
- Defining value objects.
- Establishing business invariants.
- Defining repository contracts.
- Introducing domain-specific exceptions.
- Implementing the first feature incrementally.

Business logic will remain independent of REST controllers, database adapters, and other infrastructure concerns.

---

## Development Approach

MamaCare follows an incremental **Build-and-Explain** development approach.

Each new concept will be introduced individually, its purpose explained, and its implementation demonstrated before presenting complete working code.

This approach ensures that the system remains understandable, maintainable, and aligned with its business requirements.

## Licence

A project licence has not yet been selected.

---

**MamaCare Backend — Phase 1: Foundation Established**

Built with Java, Spring Boot, PostgreSQL, DDD, and Hexagonal Architecture.