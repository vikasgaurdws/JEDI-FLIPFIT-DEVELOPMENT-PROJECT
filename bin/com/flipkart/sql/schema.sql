USE flipFit_schema;

        -- Create the Role table
        CREATE TABLE Role (
            roleID INT PRIMARY KEY AUTO_INCREMENT,
            roleName VARCHAR(255)
        );

        -- Create the FlipFitUser table with userName column
        CREATE TABLE FlipFitUser (
            userID INT PRIMARY KEY AUTO_INCREMENT,
            email VARCHAR(255) NOT NULL,
            phoneNumber VARCHAR(15),
            roleID INT,
            passwordHash VARCHAR(255),
            userName VARCHAR(255),  -- New userName attribute added
            FOREIGN KEY (roleID) REFERENCES Role(roleID)
        );

        -- Create the FlipFitGymOwner table
        CREATE TABLE FlipFitGymOwner (
            gymOwnerID INT PRIMARY KEY, -- No AUTO_INCREMENT since it's a FK reference
            gymOwnerPAN VARCHAR(50),
            gymOwnerAadharNumber VARCHAR(50),
            flagVerified BOOLEAN DEFAULT FALSE,
            FOREIGN KEY (gymOwnerID) REFERENCES FlipFitUser(userID)
        );

        -- Create the FlipFitAdmin table
        CREATE TABLE FlipFitAdmin (
            adminID INT PRIMARY KEY, -- No AUTO_INCREMENT since it's a FK reference
            adminPanCard VARCHAR(50),
            adminAadharNumber VARCHAR(50),
            age INT,
            
            FOREIGN KEY (adminID) REFERENCES FlipFitUser(userID)
        );

        -- Create the FlipFitCustomer table
        CREATE TABLE FlipFitCustomer (
            customerID INT PRIMARY KEY, -- No AUTO_INCREMENT since it's a FK reference
            customerAge INT,
            FOREIGN KEY (customerID) REFERENCES FlipFitUser(userID)
        );

        -- Create the FlipFitGym table with availableSlot column
        CREATE TABLE FlipFitGym (
            gymID INT PRIMARY KEY AUTO_INCREMENT,
            gymName VARCHAR(255),
            gymLocation VARCHAR(255),
            ownerID INT,
            price INT,
            flagVerified BOOLEAN DEFAULT FALSE,
            availableSlot INT,  -- New availableSlot attribute added
            FOREIGN KEY (ownerID) REFERENCES FlipFitGymOwner(gymOwnerID)
        );

        -- Create the FlipFitGymSlot table
        CREATE TABLE FlipFitGymSlot (
            gymSlotID INT PRIMARY KEY AUTO_INCREMENT,
            gymID INT,
            startTime TIME,
            capacity INT,
            availableSeats INT,
            FOREIGN KEY (gymID) REFERENCES FlipFitGym(gymID)
        );

        -- Create the FlipFitBookSlot table
        CREATE TABLE FlipFitBookSlot (
            bookingID INT PRIMARY KEY AUTO_INCREMENT,
            userID INT,
            gymSlotID INT,
            gymID INT,
            bookingStatus VARCHAR(50),
            bookingDate DATE,
            FOREIGN KEY (userID) REFERENCES FlipFitUser(userID) ON DELETE CASCADE ON UPDATE CASCADE,
            FOREIGN KEY (gymSlotID) REFERENCES FlipFitGymSlot(gymSlotID) ON DELETE CASCADE ON UPDATE CASCADE,
            FOREIGN KEY (gymID) REFERENCES FlipFitGym(gymID) ON DELETE CASCADE ON UPDATE CASCADE,
            CHECK (bookingStatus IN ('Confirmed', 'Cancelled', 'Waitlist'))
        );

        -- Create the FlipFitNotification table
        CREATE TABLE FlipFitNotification (
            notificationID INT PRIMARY KEY AUTO_INCREMENT,
            content VARCHAR(255),
            status VARCHAR(50)
            
        );

        -- Create the FlipFitWaitlist table with ON UPDATE CASCADE
        CREATE TABLE FlipFitWaitlist (
            waitlistID INT PRIMARY KEY AUTO_INCREMENT,
            bookingID INT,
            createdAt TIMESTAMP,
            FOREIGN KEY (bookingID) REFERENCES FlipFitBookSlot(bookingID) ON UPDATE CASCADE ON DELETE CASCADE
        );

        -- Create the FlipFitPayment table
        CREATE TABLE FlipFitPayment (
            paymentID INT PRIMARY KEY AUTO_INCREMENT,
            paymentDate DATE,
            paymentTime TIME,
            paymentAmount INT,
            paymentType VARCHAR(50),
            bookingID INT,
            FOREIGN KEY (bookingID) REFERENCES FlipFitBookSlot(bookingID)  ON UPDATE CASCADE ON DELETE CASCADE
        );

