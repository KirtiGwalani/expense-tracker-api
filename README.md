# Expense Tracker REST API

A backend REST API for managing personal expenses using Java, Spring Boot, Spring Data JPA, and MySQL.

## Features

- Add a new expense
- View all expenses
- View an expense by ID
- Update an expense
- Delete an expense
- Filter expenses by category
- View total expenses and category-wise summary
- Request validation for required fields and positive amounts
- HTTP 400 and 404 error handling

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST API
- Postman
- Git & GitHub

## Project Structure

```text
src/
└── main/
    ├── java/com/example/expense_tracker/
    │   ├── Expense.java
    │   ├── ExpenseController.java
    │   ├── ExpenseRepository.java
    │   ├── ExpenseService.java
    │   └── ExpenseTrackerApplication.java
    └── resources/
        └── application.properties
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/expenses` | Add an expense |
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get expense by ID |
| PUT | `/api/expenses/{id}` | Update an expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/expenses/category/{category}` | Get expenses by category |
| GET | `/api/expenses/summary` | Get total and category-wise summary |

## Sample Request

```json
{
  "title": "Lunch",
  "amount": 150,
  "category": "Food",
  "date": "2026-09-14",
  "description": "College lunch"
}
```

## Sample Summary Response

```json
{
  "total": 200.0,
  "byCategory": {
    "Travel": 50.0,
    "Food": 150.0
  }
}
```

## Setup

### 1. Create the MySQL database

```sql
CREATE DATABASE expense_tracker;
```

### 2. Configure the database password

The application reads the MySQL password from the `DB_PASSWORD` environment variable.

On macOS/Linux:

```bash
export DB_PASSWORD='your_mysql_password'
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API runs on:

```text
http://localhost:8080
```

## Testing

The API endpoints were tested using Postman, including:

- CRUD operations
- Category filtering
- Expense summary
- Validation errors
- Not-found (404) handling

## Author

Kirti Gwalani