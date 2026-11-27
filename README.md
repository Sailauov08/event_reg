# Lab 7 — Event Registration REST API

Simple Spring Boot API with CRUD and case-insensitive title search. Events are stored in an ArrayList, not a database. Changes reset on restart.

## Requirements

- JDK 17 or 21
- IntelliJ IDEA or another Java IDE
- Internet connection for the initial Gradle dependency download

## Run

Open this folder (the one containing build.gradle) in IntelliJ IDEA as a Gradle project. Set Project SDK and Gradle JVM to JDK 17 or 21. Run RegistrationApplication.java or use the included Gradle wrapper:

Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

Linux/macOS:

```bash
./gradlew bootRun
```

Base URL: http://localhost:8080

## API endpoints

| Method | Endpoint | Expected status |
|---|---|---|
| GET | /api/events | 200 |
| GET | /api/events?title=quiz | 200 |
| GET | /api/events/1 | 200 or 404 |
| POST | /api/events | 201 or 400 |
| PUT | /api/events/4 | 200, 400 or 404 |
| DELETE | /api/events/4 | 204 or 404 |

POST and PUT use Content-Type: application/json and a body like:

```json
{
  "title": "Python Workshop",
  "location": "Room 105"
}
```

The server assigns IDs starting from 4. IDs are not reused after deletion during one run. Empty or missing title/location returns 400. Missing IDs return 404.

## Test

Use the included requests.http in IntelliJ HTTP Client, or import Lab7.postman_collection.json into Postman. Start from a fresh app instance and execute requests in order. Use the actual returned ID in update/delete requests if it differs from 4.

Run automated integration tests (Windows): ` .\gradlew.bat test `
Linux/macOS: `./gradlew test`

Restart the application after testing to confirm only the original three seed events remain.

## Project structure

- RegistrationApplication — starts Spring Boot.
- EventInfo — event model.
- EventService — list, ID counter, CRUD and search logic.
- RegistrationController — receives HTTP requests and returns responses.

## Submission

Upload this project to your GitHub repository, including the Gradle wrapper. Exclude .idea, .gradle and build (see .gitignore). Run the tests and capture 3–5 screenshots of actual requests/status codes for your report.

## Verification performed

The Java source was compiled and the Spring Boot application was started using Java 21. All 19 live HTTP checks passed, including a restart. The evidence folder contains the actual request/response transcripts rendered as images and a text summary. The Gradle-based test run still needs to be completed in an environment with a full JDK and Gradle dependency access.
