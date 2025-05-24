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
    role VARCHAR(20)
);

CREATE TABLE Payment (
    paymentID INTEGER PRIMARY KEY AUTO_INCREMENT,
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
    paymentStatus VARCHAR(20) DEFAULT 'Pending',
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customerID) REFERENCES User(userID)
);
CREATE TABLE Product (
    productID INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    imageUrl VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    favourited BOOLEAN DEFAULT FALSE,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `Order` (
    orderID INTEGER PRIMARY KEY AUTO_INCREMENT,
    customerID INTEGER NOT NULL,
    orderStatus VARCHAR(50) NOT NULL DEFAULT 'Pending',
    orderDate TIMESTAMP NOT NULL,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customerID) REFERENCES User(userID)
);

CREATE TABLE OrderLine (
    orderID INTEGER NOT NULL,
    productID INTEGER NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1,
    requests TEXT,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (orderID, productID),
    FOREIGN KEY (orderID) REFERENCES `Order`(orderID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);