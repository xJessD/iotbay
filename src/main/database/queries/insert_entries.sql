INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("James", "Qu", "test@t.com", "123456", "", "Customer");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("John", "Smith", "jsmith@gmail.com", "JOHN234352", "12345678", "Customer");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("Jess", "D", "test@gmail.com", "JxDx", "12345678", "Customer");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("Alice", "Johnson", "ajohnson@email.com", "AlicePass123", 87654321, "Customer");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("Bob", "Williams", "bwilliams@email.com", "BobSecure456", 23456789, "Customer");

INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("David", "Lee", "dlee@iotbay.com", "DavidSecure789", 98765432, "Staff");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("Jess", "D", "admin@gmail.com", "admin", 12345678, "Admin");
INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ("Emily", "Wilson", "ewilson@iotbay.com", "EmilySecret567", 65432109, "Staff");

-- Sample Payment data
INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1001, 1, '2025-04-15', 'Credit Card', '199.99', '123 Main St', '2000', 'Sydney', 'NSW', '0412345678', '2025-04-15', '2025-04-15');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1002, 2, '2025-04-18', 'PayPal', '349.50', '456 Park Ave', '3000', 'Melbourne', 'VIC', '0423456789', '2025-04-18', '2025-04-18');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1003, 3, '2025-04-20', 'Debit Card', '75.25', '789 Queen St', '4000', 'Brisbane', 'QLD', '0434567890', '2025-04-20', '2025-04-20');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1004, 4, '2025-04-22', 'Credit Card', '125.00', '101 Elizabeth St', '5000', 'Adelaide', 'SA', '0445678901', '2025-04-22', '2025-04-22');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1005, 5, '2025-04-25', 'Bank Transfer', '299.99', '202 Wellington St', '6000', 'Perth', 'WA', '0456789012', '2025-04-25', '2025-04-25');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1006, 1, '2025-05-01', 'Credit Card', '89.95', '123 Main St', '2000', 'Sydney', 'NSW', '0412345678', '2025-05-01', '2025-05-01');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1007, 2, '2025-05-05', 'PayPal', '149.50', '456 Park Ave', '3000', 'Melbourne', 'VIC', '0423456789', '2025-05-05', '2025-05-05');

INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber, createdDate, updatedDate)
VALUES (1008, 3, '2025-05-10', 'Debit Card', '199.99', '789 Queen St', '4000', 'Brisbane', 'QLD', '0434567890', '2025-05-10', '2025-05-10');