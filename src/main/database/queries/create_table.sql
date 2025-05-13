CREATE TABLE Customer (
    customerID INTEGER PRIMARY KEY,
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
    lastUpdated DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Staff (
    staffID INTEGER PRIMARY KEY,
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(20),
    phoneNumber VARCHAR(15),
    createdDate DATE DEFAULT CURRENT_TIMESTAMP,
    lastUpdated DATE DEFAULT CURRENT_TIMESTAMP,
    roleInCompany VARCHAR(20)
);