CREATE TABLE User (
    userID INTEGER PRIMARY KEY,
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(20),
    phoneNumber VARCHAR(15),
    streetAddress VARCHAR(20),
    country VARCHAR(20),
    state VARCHAR(10),
    postcode VARCHAR(4),
    suburb VARCHAR(10),
    createdDate DATE DEFAULT CURRENT_TIMESTAMP,
    lastUpdated DATE DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS AccessLog (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    loginDateTime TEXT NOT NULL,
    logoutDateTime TEXT,
    FOREIGN KEY (userId) REFERENCES User(id)
);

CREATE TABLE Payment (
    paymentID INTEGER PRIMARY KEY AUTOINCREMENT,
    orderID INTEGER,
    customerID INTEGER,
    paymentDate DATE,
    paymentMethod VARCHAR(50),
    paymentAmount VARCHAR(20),
    billingStreetAddress VARCHAR(100),
    billingPostcode VARCHAR(10),
    billingCity VARCHAR(50),
    billingState VARCHAR(50),
    billingPhoneNumber VARCHAR(20),
    createdDate DATE DEFAULT CURRENT_TIMESTAMP,
    updatedDate DATE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customerID) REFERENCES User(userID)
);