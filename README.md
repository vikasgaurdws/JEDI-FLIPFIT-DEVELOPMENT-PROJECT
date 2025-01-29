# JEDI-FLIPFIT Gym Management System

## Contents
1. [Project Overview](#project-overview)
2. [Technical Stack](#technical-stack)
3. [System Architecture](#system-architecture)
4. [Database Design](#database-design)
5. [Features](#features)
6. [Setup Instructions](#setup-instructions)
7. [API Documentation](#api-documentation)
8. [Usage Guide](#usage-guide)

## Project Overview
FlipFit is a comprehensive gym management system that connects gym owners with customers, facilitating efficient gym slot booking and management. The system streamlines the process of gym registration, slot management, and booking operations.

### Key Stakeholders
- **Gym Owners**: Register and manage their gym facilities
- **Customers**: Book and manage gym sessions
- **Administrators**: Oversee system operations and approvals

## Technical Stack
- **Backend**: Java
- **Database**: MySQL
- **Architecture**: Layered Architecture (DAO, Business, Client layers)
- **Build Tool**: Maven
- **Version Control**: Git

## System Architecture

### Package Structure
```
com.flipkart
├── bean/
│   ├── FlipFitCustomer.java
│   ├── FlipFitGymOwner.java
│   ├── FlipFitGym.java
│   └── Slot.java
├── business/
│   ├── CustomerService.java
│   ├── GymOwnerService.java
│   └── AdminService.java
├── DAO/
│   ├── FlipFitCustomerDAO.java
│   ├── FlipFitGymOwnerDAO.java
│   └── FlipFitUserDAO.java
├── client/
│   └── FlipfitMenu.java
├── exception/
│   └── InvalidInputException.java
└── utils/
    └── DBconnection.java
```

### Layers
1. **Bean Layer**
   - Data models and entity classes
   - Implements proper encapsulation
   - Used for database mapping

2. **DAO Layer**
   - Database interaction logic
   - CRUD operations
   - Query execution

3. **Business Layer**
   - Business logic implementation
   - Data validation
   - Transaction management

4. **Client Layer**
   - User interface
   - Input handling
   - Response formatting

## Database Design

### Tables
1. **Customer**
   ```sql
   CREATE TABLE Customer (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(50),
       email VARCHAR(50) UNIQUE,
       password VARCHAR(100)
   );
   ```

2. **GymOwner**
   ```sql
   CREATE TABLE GymOwner (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(50),
       email VARCHAR(50) UNIQUE,
       password VARCHAR(100),
       approved BOOLEAN DEFAULT FALSE
   );
   ```

3. **Gym**
   ```sql
   CREATE TABLE Gym (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100),
       location VARCHAR(100),
       owner_id INT,
       approved BOOLEAN DEFAULT FALSE,
       FOREIGN KEY (owner_id) REFERENCES GymOwner(id)
   );
   ```

4. **Slot**
   ```sql
   CREATE TABLE Slot (
       id INT PRIMARY KEY AUTO_INCREMENT,
       gym_id INT,
       start_time TIME,
       end_time TIME,
       capacity INT,
       FOREIGN KEY (gym_id) REFERENCES Gym(id)
   );
   ```

5. **Booking**
   ```sql
   CREATE TABLE Booking (
       id INT PRIMARY KEY AUTO_INCREMENT,
       customer_id INT,
       slot_id INT,
       booking_date DATE,
       status VARCHAR(20),
       FOREIGN KEY (customer_id) REFERENCES Customer(id),
       FOREIGN KEY (slot_id) REFERENCES Slot(id)
   );
   ```

## Features

### Customer Features
- Registration and authentication
- Gym search by location
- Slot booking and cancellation
- Booking history view
- Waitlist management
- Payment processing

### Gym Owner Features
- Gym registration
- Slot management
- Booking overview
- Profile management
- Revenue tracking

### Admin Features
- Gym owner approval
- Gym approval
- System monitoring
- User management

## Setup Instructions

### Prerequisites
- JDK 8 or higher
- MySQL 5.7 or higher
- Maven

### Installation Steps
1. Clone the repository
   ```bash
   git clone https://github.com/your-repo/flipfit.git
   ```

2. Configure database
   ```java
   // Update in DBconnection.java
   private static String url = "jdbc:mysql://localhost:3306/GroupD_Schema";
   private static String username = "your_username";
   private static String password = "your_password";
   ```

3. Build the project
   ```bash
   mvn clean install
   ```

4. Run the application
   ```bash
   java -jar target/flipfit.jar
   ```

## API Documentation

### Customer API
```java
public interface FlipFitCustomerDAO {
    int registerCustomer(FlipFitCustomer customer);
    List<FlipFitGym> getAvailableGyms();
    List<Slot> getSlots(int gymId);
    int bookSlots(int userId, int slotId, int gymId, Date date);
    boolean cancelBooking(int userId, int bookingId);
}
```

### Gym Owner API
```java
public interface FlipFitGymOwnerDAO {
    FlipFitGymOwner getFlipFitGymOwnerDetails(FlipFitGymOwner gymOwner);
    boolean addGym(FlipFitGymOwner gymOwner, FlipFitGym gym);
    List<FlipFitGym> getGymsOfFlipFitGymOwner(FlipFitGymOwner gymOwner);
    boolean addSlot(Slot slot);
}
```

## Usage Guide

### Customer Flow
1. Register/Login
2. Search for gyms
3. View available slots
4. Book preferred slot
5. Make payment
6. View booking confirmation

### Gym Owner Flow
1. Register/Login
2. Add gym details
3. Wait for admin approval
4. Add slots
5. Monitor bookings

### Admin Flow
1. Login
2. Review gym owner applications
3. Approve/reject gyms
4. Monitor system metrics

## Error Handling
- Custom exceptions for validation
- Proper error messages
- Transaction management
- Input validation

## Security Measures
- Password encryption
- Input sanitization
- Role-based access control
- Session management

## Contributing Guidelines
1. Fork the repository
2. Create feature branch
3. Commit changes
4. Create pull request

## Contact
Vikas Gaur
(Whatsappp: 9340544813)
