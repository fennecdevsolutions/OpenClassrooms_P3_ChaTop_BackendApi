# Chatôp API Backend

## Overview

This is the backend API for the Chatôp application, built on Spring Boot v3.4.2. It provides the server-side functionality and data management for the Chatôp frontend application.

## Prerequisites

- Java Development Kit (JDK) 17+
- Maven 3.6+
- MySQL Server 8.0+
- Eclipse IDE (optional) or any Java IDE

## Frontend Repository

The corresponding frontend application can be found in the following GitHub repository: [Chatôp Frontend](https://github.com/OpenClassrooms-Student-Center/Mod-lisez-et-impl-mentez-le-back-end-en-utilisant-du-code-Java-maintenable)

## Database Setup

### 1. Create the Database

Create the MySQL database using the provided SQL script:

```sql
-- Execute the following script from chatop_db.sql file
-- File location: src/main/resources/chatop_db.sql
-- Database name: chatop_db
-- Server: localhost:3306
```

### 2. Create Database User

Create a restricted database user with the following Grants:

- SELECT
- INSERT
- UPDATE

### 3. Configure Environment Variables

Set the following environment variables for database access:

- **DB_USERNAME**: Your database username (e.g., chatop_user)
- **DB_PASSWORD**: Your database password

#### Setting Environment Variables

**On Linux/macOS:**
```bash
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
```

**On Windows:**
```cmd
set DB_USERNAME=your_username
set DB_PASSWORD=your_password
```

**In Eclipse IDE:**
1. Go to Run → Run Configurations
2. Select your application
3. Go to the "Environment" tab
4. Add the variables:
   - Variable: `DB_USERNAME`, Value: `your_username`
   - Variable: `DB_PASSWORD`, Value: `your_password`

## Installation & Running

### Using Maven Commands

**Clone the repository:**
```bash
git clone <repository-url>
cd chatop-api
```

**Build the project:**
```bash
mvn clean install
```

**Run the application:**
```bash
mvn spring-boot:run
```

### Using Eclipse IDE

1. **Import the project:**
   - File → Import → Maven → Existing Maven Projects
   - Select the project directory
   - Click Finish

2. **Set environment variables in Eclipse:**
   - Right-click the project → Run As → Run Configurations
   - Select your application configuration
   - Go to the "Environment" tab
   - Add DB_USERNAME and DB_PASSWORD variables

3. **Run the application:**
   - Right-click the project → Run As → Spring Boot App

## API Documentation

The API documentation is automatically generated using OpenAPI 3.0 and can be accessed through Swagger UI using the following URL : http://localhost:3001/swagger-ui/index.html

### Features

- **Interactive API Documentation**: Browse all available endpoints
- **Try it out**: Test API endpoints directly from the browser
- **Request/Response Examples**: View sample requests and responses
- **Authentication**: Configure and test authentication

## Troubleshooting

### Common Issues

1. **Database Connection Errors**
   - Verify MySQL server is running
   - Check environment variables are set correctly
   - Ensure database name is `chatop_db`

2. **Maven Build Errors**
   - Clean the project: `mvn clean`
   - Update dependencies: `mvn dependency:resolve`
