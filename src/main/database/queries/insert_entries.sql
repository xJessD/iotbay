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

-- Sample Product data
INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Home Hub', 'images/products/smart-hub.jpg', 'Central control unit for all your IoT devices. Supports Wi-Fi, Bluetooth, Zigbee, and Z-Wave protocols.', 199.99, 25, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Wi-Fi Smart Bulb (4-Pack)', 'images/products/smart-bulb.jpg', 'Color-changing LED smart bulbs with app control, voice assistant compatibility, and scheduling features.', 49.99, 50, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Thermostat', 'images/products/smart-thermostat.jpg', 'Energy-saving thermostat with learning capabilities, remote control, and energy usage reports.', 129.99, 15, FALSE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Security Camera', 'images/products/security-camera.jpg', 'HD security camera with night vision, motion detection, and two-way audio communication.', 89.95, 30, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Door Lock', 'images/products/door-lock.jpg', 'Keyless entry with fingerprint, PIN code, and smartphone app access. Includes tamper alerts and access logs.', 199.95, 12, FALSE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Motion Sensor', 'images/products/motion-sensor.jpg', 'Battery-powered wireless motion detector for security and automation. Integrates with smart home systems.', 29.99, 40, FALSE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Power Strip', 'images/products/power-strip.jpg', 'Wi-Fi enabled power strip with 4 individually controllable outlets and energy monitoring.', 59.99, 35, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Water Leak Detector', 'images/products/leak-detector.jpg', 'Early warning system for water leaks with audible alarm and smartphone notifications.', 34.95, 25, FALSE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Speaker', 'images/products/smart-speaker.jpg', 'Voice-controlled speaker with premium sound quality and virtual assistant support.', 149.95, 20, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Robot Vacuum', 'images/products/robot-vacuum.jpg', 'Automated cleaning robot with mapping technology, app control, and scheduled cleaning.', 299.99, 10, TRUE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Smart Garage Door Controller', 'images/products/garage-controller.jpg', 'Retrofit device to make existing garage doors smart. Control and monitor from anywhere.', 79.95, 18, FALSE);

INSERT INTO Product (name, imageUrl, description, price, quantity, favourited)
VALUES ('Environmental Sensor', 'images/products/env-sensor.jpg', 'Monitors air quality, temperature, humidity, and VOCs. Provides alerts when conditions are unhealthy.', 69.99, 22, FALSE);

-- Sample Order data
INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1001, 1, 'Completed', '2025-04-15 10:30:00', '2025-04-15 10:30:00', '2025-04-15 15:45:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1002, 2, 'Completed', '2025-04-18 14:20:00', '2025-04-18 14:20:00', '2025-04-18 18:10:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1003, 3, 'Completed', '2025-04-20 09:15:00', '2025-04-20 09:15:00', '2025-04-20 12:30:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1004, 4, 'Completed', '2025-04-22 16:45:00', '2025-04-22 16:45:00', '2025-04-22 19:20:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1005, 5, 'Completed', '2025-04-25 11:30:00', '2025-04-25 11:30:00', '2025-04-25 14:15:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1006, 1, 'Completed', '2025-05-01 13:20:00', '2025-05-01 13:20:00', '2025-05-01 16:40:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1007, 2, 'Processing', '2025-05-05 10:10:00', '2025-05-05 10:10:00', '2025-05-05 10:10:00');

INSERT INTO `Order` (orderID, customerID, orderStatus, orderDate, createdDate, updatedDate)
VALUES (1008, 3, 'Processing', '2025-05-10 15:30:00', '2025-05-10 15:30:00', '2025-05-10 15:30:00');

-- Sample OrderLine data
INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1001, 1, 1, 'Please include setup instructions', '2025-04-15 10:30:00', '2025-04-15 10:30:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1002, 2, 2, 'Would like bulbs to be compatible with my existing system', '2025-04-18 14:20:00', '2025-04-18 14:20:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1002, 3, 1, NULL, '2025-04-18 14:20:00', '2025-04-18 14:20:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1003, 6, 3, 'Need these for different rooms', '2025-04-20 09:15:00', '2025-04-20 09:15:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1004, 4, 2, 'Need outdoor models if available', '2025-04-22 16:45:00', '2025-04-22 16:45:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1005, 10, 1, 'Please ensure latest firmware is installed', '2025-04-25 11:30:00', '2025-04-25 11:30:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1006, 4, 1, NULL, '2025-05-01 13:20:00', '2025-05-01 13:20:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1007, 9, 1, 'Gift wrapping if possible', '2025-05-05 10:10:00', '2025-05-05 10:10:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1007, 7, 1, NULL, '2025-05-05 10:10:00', '2025-05-05 10:10:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1008, 5, 1, 'Please include all necessary hardware', '2025-05-10 15:30:00', '2025-05-10 15:30:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1008, 8, 2, 'Need these for bathroom and kitchen', '2025-05-10 15:30:00', '2025-05-10 15:30:00');

INSERT INTO OrderLine (orderID, productID, quantity, requests, createdDate, updatedDate)
VALUES (1008, 12, 1, NULL, '2025-05-10 15:30:00', '2025-05-10 15:30:00');