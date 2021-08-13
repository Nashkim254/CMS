Use CourierManagementSystemDatabase;

CREATE TABLE CUSTOMER
(
	CustomerNID varchar(16) PRIMARY KEY,
	Name varchar(20) not null,
	Address varchar(40) not null,
	Phone varchar(15) not null
);


CREATE TABLE RECEIVER
(
	ReceiverNID varchar(16) PRIMARY KEY,
	Name varchar(20) not null,
	Address varchar(40) not null,
	Phone varchar(15) not null
);

CREATE TABLE DELIVERYMAN
(
	DeliveryManID int PRIMARY KEY,
	Name varchar(20) not null,
	Address varchar(40) null,
	Phone varchar(15) unique not null,
    DeliverymanPassWord varchar(10),
    CONSTRAINT UC_DELIVERYMAN UNIQUE (Email),
	Email varchar(20) unique not null
);


/*
select * from DELIVERYMAN
*/

CREATE TABLE PAYMENT
(
	PaymentID int  PRIMARY KEY,
	CustomerNID varchar(16), foreign key references CUSTOMER(CustomerNID),
	Type varchar(10) not null,
	Amount decimal(8,2) not null
);


CREATE TABLE PACKAGE
(
	PackageID int PRIMARY KEY,
	Type varchar(20) null,
	Weight decimal(6,2) check(Weight>0)
);


CREATE TABLE VEHICLE
(
	VehicleID int PRIMARY KEY,
	Type varchar(10) DEFAULT 'Pick-up',
	LicenseNo varchar(15) not null unique
);

/*DROP TABLE VEHICLE
SELECT * FROM VEHICLE
SELECT * FROM DELIVERYMAN*/


CREATE TABLE ORDERS
(
	OrderID int PRIMARY KEY,
	CustomerNID varchar(16), foreign key references CUSTOMER(CustomerNID),
	ReceiverNID varchar(16), foreign key references RECEIVER(ReceiverNID),
	DeliveryManID int, foreign key references DELIVERYMAN(DeliveryManID),
	PackageID int, foreign key references PACKAGE(PackageID),
	PaymentID int, foreign key references PAYMENT(PaymentID),
	VehicleID int, foreign key references VEHICLE(VehicleID),
	Date DATE not null,
	Status varchar(12) DEFAULT 'PENDING'
);



/*DROP TABLE ORDERS
SELECT * FROM ORDERS*/

/*Abir*/

CREATE TABLE ADMIN
(
	AdminUserName varchar(10) PRIMARY KEY,
    CONSTRAINT UC_ADMIN UNIQUE (AdminUserName),
	AdminPassWord varchar(10) not null
);



/*
SELECT * FROM ADMIN WHERE AdminUserName = 'xyz' AND AdminPassWord = '1234567'
*/

/*
DELETE FROM ADMIN WHERE AdminUserName = ''
*/

/*
ALTER TABLE DELIVERYMAN
ADD DeliverymanUserName varchar(10)

ALTER TABLE DELIVERYMAN
DROP COLUMN DeliverymanUserName
*/

/*
SELECT * FROM ADMIN
SELECT * FROM DELIVERYMAN
*/


/*
SELECT * FROM ADMIN
SELECT * FROM DELIVERYMAN
SELECT * FROM VEHICLE
SELECT * FROM PAYMENT
SELECT * FROM ORDERS
SELECT * FROM PACKAGE
SELECT * FROM CUSTOMER
SELECT * FROM RECEIVER

SELECT CUSTOMER.CustomerNID, CUSTOMER.Name, CUSTOMER.Phone, CUSTOMER.Address, PACKAGE.Type, PACKAGE.Weight, ORDERS.Status, ORDERS.ReceiverNID FROM PACKAGE INNER JOIN ORDERS ON PACKAGE.PackageID = ORDERS.PackageID INNER JOIN CUSTOMER ON ORDERS.CustomerNID = CUSTOMER.CustomerNID/* WHERE ORDERS.ReceiverNID = '9999'*/

/*SELECT DELIVERYMAN.Name, DELIVERYMAN.Address, DELIVERYMAN.Phone, A.PendingOrders FROM DELIVERYMAN INNER JOIN (SELECT DeliveryManID, COUNT(OrderID) AS PendingOrders FROM ORDERS WHERE ORDERS.Status LIKE 'PENDING' GROUP BY DeliveryManID) AS A ON DELIVERYMAN.DeliveryManID = A.DeliveryManID

SELECT * FROM DELIVERYMAN WHERE DeliveryManID = 12

UPDATE DELIVERYMAN SET Name = 'Mohammad', Address = 'Lalmonirhat', Phone = '01111111111', Email = 'abc@outlook.edu' WHERE DeliveryManID = 15
*/