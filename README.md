# System Admin Application for Corporate Application Management

This is a system admin application designed to help administrators view and manage devices and applications installed by employees. The admin can:

- View if the applications are up-to-date.
- View the current version of each application on each device.
- View the latest version of each application.
- Sort the list based on device name, application name, last updated, and status.

## Preview of application: 
![sytem admin 1](https://github.com/user-attachments/assets/50b1805e-5be3-43a2-83eb-b1420eeac6c1)
![system admin 2](https://github.com/user-attachments/assets/13f9244b-2f88-4438-bbda-2c6e3f73d80d)


## Additional Functionalities
 
### API to Get Latest Version of an Application in a Device:

This API is used by mobile applications to fetch the latest version of an application to compare versions and prompt for updates if necessary.

### API to Update Application:

This API updates the application to the latest version, the status to 'updated', and updates the 'current version' and the 'last updated' timestamp.

# Installation

## Prerequisites

To run this application, you will need the following:

- Java 11 or later
-  Spring Boot 2.6.x or later
- PostgreSQL 12 or later

## Clone the Repository

#### bash
```
git clone https://github.com/Benjamin-Benny/System-Admin.git
cd device-app-management
```

## Configure PostgreSQL
Ensure you have PostgreSQL installed and running. Create a database for this application.

## Database Configuration
Create the necessary tables in your PostgreSQL database using the following SQL queries:

#### sql
```
CREATE TABLE devices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE applications (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latest_version VARCHAR(50) NOT NULL
);

CREATE TABLE device_applications (
    id SERIAL PRIMARY KEY,
    device_id INTEGER NOT NULL,
    application_id INTEGER NOT NULL,
    current_version VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    last_updated TIMESTAMP,
    FOREIGN KEY (device_id) REFERENCES devices (id),
    FOREIGN KEY (application_id) REFERENCES applications (id)
);
```

## Application Properties
Configure the application properties to connect to your PostgreSQL database. Update the 'src/main/resources/application.properties' file with your database credentials:

#### application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```


##  Build and Run the Application

### Build and run the application using Maven:

#### bash
```
./mvnw spring-boot:run
```

The application will start on http://localhost:8080.

### Usage
#### Viewing Devices and Applications
Access the main page at http://localhost:8080/device-applications to view the list of devices and applications along with their status, current version, latest version, and last updated timestamp.

### Sorting
Click on the table headers to sort the list based on device name, application name, last updated, and status.


## APIs

### Get Latest Version of an Application:

- Endpoint: /api/device-applications/latest/{deviceId}/{applicationId}
- Method: GET

 Returns the latest version of the specified application for the given device.


### Update Application:

- Endpoint: /api/device-applications/update
- Method: POST
- Body: JSON object containing deviceId and applicationId

Updates the application to the latest version and sets the status to 'updated'.

. 
.
.
## Have a great day and Happy Coding!

