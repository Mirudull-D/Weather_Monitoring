# Weather Monitoring API

A lightweight, high-performance **Spring Boot REST API** designed for fetching, persisting, and analyzing weather data. This application utilizes a layered architecture to ensure separation of concerns, scalability, and maintainability.

---

##  Features

* **Live Data Retrieval:** Real-time fetching of current weather metrics (temperature, humidity, conditions).
* **Historical Tracking:** Persistent storage of weather data indexed by city for time-series analysis.
* **Daily Summaries:** Aggregated insights and statistical summaries for specific locations.
* **Data Integrity:** Validated DTO (Data Transfer Object) patterns to ensure clean API boundaries.

---

## Tech Stack

| Component | Technology | Details |
| :--- | :--- | :--- |
| **Language** | **Java 21** | Utilizes LTS features; optimized for modern JVM runtimes. |
| **Framework** | **Spring Boot 3.x** | Core framework for RESTful services and dependency injection. |
| **Database** | **PostgreSQL** | Relational storage optimized for time-series weather logs. |
| **ORM** | **Spring Data JPA** | Abstracts data access layers using Hibernate as the provider. |
| **Mapping** | **MapStruct** | Compile-time safe bean mapping (DTO ↔ Entity). |
| **Boilerplate** | **Lombok** | Reduces repetitive code (getters, setters, builders). |

---

##  API Documentation

### 1. Fetch Live Weather
Retrieves current weather and saves the record to the database.

* **Endpoint:** `POST /api/weather`
* **Headers:** `Content-Type: application/json`

**Request Body:**
```json
{
  "cityName": "Chennai"
}
```

**Response Body (201 Created):**
```json
{
  "temperature": 30,
  "humidity": 70,
  "condition": "Cloudy"
}
```

### 2. Get Weather History
Retrieves all recorded weather entries for a specific city.
* **Endpoint:** `GET /api/weather/history?cityName=Chennai`

### 3. Get Daily Summary
Provides an aggregated summary of weather conditions for the current day.
* **Endpoint:** `GET /api/weather/summary?cityName=Chennai`

---

##  Architecture & Design

The application follows a **N-Tier Layered Architecture** to decouple the business logic from the infrastructure.



* **Controller Layer:** Handles incoming HTTP requests and maps them to service calls.
* **Service Layer:** Contains business logic, calculations, and coordination between the API and Database.
* **Repository Layer (JPA):** Manages data persistence and complex SQL queries via `JpaRepository`.
* **Mapper (MapStruct):** Decouples internal `Entities` from external `DTOs`, preventing sensitive database schemas from leaking into the API response.

---

##  Installation & Setup

1.  **Clone the Repository:**
    ```bash
    git clone <your-repo-url>
    cd Weather_Monitoring
    ```

2.  **Build the Project:**
    ```bash
    mvn clean install
    ```

3.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```

### Database Configuration
Update the `src/main/resources/application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

---

## Key Dependencies

* `spring-boot-starter-web`: For building RESTful web services.
* `spring-boot-starter-data-jpa`: For interfacing with the PostgreSQL database.
* `postgresql`: JDBC driver for database connectivity.
* `mapstruct`: Generates mapping code at compile-time for high performance.
* `lombok`: Simplifies POJO classes using annotations.

---

##  Technical Notes & Context

* **Java 21 Benefits:** The use of Java 21 allows for future-proofing with **Virtual Threads (Project Loom)**, which significantly improves throughput for I/O-bound operations like external weather API calls.
* **MapStruct vs. ModelMapper:** Unlike reflection-based mappers, **MapStruct** generates plain Java code during compilation. This ensures there is no runtime overhead and errors are caught before the application even starts.
* **DTO Pattern:** By using DTOs, the API ensures that only necessary fields are exposed to the end-user, providing a layer of security and allowing the database schema to evolve without breaking the public API.
* **Scalability:** The stateless nature of the REST controllers allows this service to be horizontally scaled using containerization tools like Docker and Kubernetes.

---
