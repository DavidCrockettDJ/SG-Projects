DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE IF NOT EXISTS HotelReservation;

USE HotelReservation;

CREATE TABLE Customer (
CustomerID INT NOT NULL AUTO_INCREMENT,
FirstName VARCHAR(30) NOT NULL,
LastName VARCHAR(30) NOT NULL,
CellPhone VARCHAR(15) NOT NULL,
Email VARCHAR(45) NOT NULL,
Age TINYINT NOT NULL,
PRIMARY KEY (CustomerID));

CREATE TABLE Room (
RoomID INT NOT NULL AUTO_INCREMENT,
RoomNumber SMALLINT UNSIGNED NOT NULL,
RoomType VARCHAR(10) NOT NULL,
OccupancyLimit TINYINT NOT NULL,
PRIMARY KEY (RoomID));

CREATE TABLE Rate (
RateID INT NOT NULL AUTO_INCREMENT,
RateDate DATE NOT NULL,
Price DECIMAL(9, 4) NOT NULL,
PRIMARY KEY (RateID));

CREATE TABLE Amenity (
AmenityID INT NOT NULL AUTO_INCREMENT,
Bed VARCHAR(10) NOT NULL,
TV VARCHAR(10) NOT NULL,
NumberOfBeds TINYINT NOT NULL,
NumberOfTVs TINYINT NOT NULL,
Fridge BOOLEAN NOT NULL,
Jacuzzi BOOLEAN NOT NULL,
MiniBar BOOLEAN NOT NULL,
PRIMARY KEY (AmenityID));

CREATE TABLE RoomRate (
RateID INT NOT NULL,
RoomID INT NOT NULL,
CONSTRAINT fk_RoomRate_RateID
FOREIGN KEY (RateID)
REFERENCES Rate(RateID),
CONSTRAINT fk_RoomRate_RoomID
FOREIGN KEY (RoomID)
REFERENCES Room(RoomID));

CREATE TABLE RoomAmmenities (
RoomID INT NOT NULL ,
AmenityID INT NOT NULL,
CONSTRAINT fk_RoomAmmenities_RoomID
FOREIGN KEY (RoomID)
REFERENCES Room(RoomID),
CONSTRAINT fk_RoomAmmenities_AmenityID
FOREIGN KEY (AmenityID) 
REFERENCES Amenity(AmenityID));

CREATE TABLE AddOn (
AddOnID INT NOT NULL AUTO_INCREMENT,
Description VARCHAR(30) NOT NULL,
Price DECIMAL(9, 4) NOT NULL,
AddOnDate DATE NOT NULL,
PRIMARY KEY (AddOnID));

CREATE TABLE Reservation (
ReservationID INT NOT NULL AUTO_INCREMENT,
StartDate DATE NOT NULL,
EndDate DATE NOT NULL,
CustomerID INT NOT NULL,
PRIMARY KEY (ReservationID),
CONSTRAINT fk_Reservation_CustomerID
FOREIGN KEY (CustomerID)
REFERENCES Customer(CustomerID));

CREATE TABLE Guest (
GuestID INT NOT NULL AUTO_INCREMENT,
FirstName VARCHAR(30) NOT NULL,
LastName VARCHAR(30) NOT NULL,
Age TINYINT NOT NULL,
ReservationID INT NOT NULL,
PRIMARY KEY (GuestID),
CONSTRAINT fk_Guest_ReservationID
FOREIGN KEY (ReservationID)
REFERENCES Reservation(ReservationID));

CREATE TABLE RoomReservation (
RoomID INT NOT NULL,
ReservationID INT NOT NULL,
CONSTRAINT fk_RoomReservation_RoomID
FOREIGN KEY (RoomID)
REFERENCES Room(RoomID),
CONSTRAINT fk_RoomReservation_ReservationID
FOREIGN KEY (ReservationID)
REFERENCES Reservation(ReservationID));

CREATE TABLE ReservationAddOns (
AddOnID INT NOT NULL,
ReservationID INT NOT NULL,
CONSTRAINT fk_ReservationAddOns_AddOnID
FOREIGN KEY (AddOnID)
REFERENCES AddOn(AddOnID),
CONSTRAINT fk_ReservationAddOns_ReservationID
FOREIGN KEY (ReservationID)
REFERENCES Reservation(ReservationID));

CREATE TABLE Promotion (
PromotionID INT NOT NULL AUTO_INCREMENT,
StartDate DATE NOT NULL,
EndDate DATE NOT NULL,
Description VARCHAR(30) NOT NULL,
PRIMARY KEY (PromotionID));

CREATE TABLE Receipt (
ReceiptID INT NOT NULL AUTO_INCREMENT,
PromotionID INT NULL,
Tax DECIMAL(9, 4) NOT NULL,
Total DECIMAL(15, 4) NOT NULL,
AddOnTotals DECIMAL(12, 4) NULL,
PRIMARY KEY (ReceiptID),
CONSTRAINT fk_Receipt_PromotionID
FOREIGN KEY (PromotionID)
REFERENCES Promotion(PromotionID));

CREATE TABLE ReservationReceipt (
ReservationID INT NOT NULL,
ReceiptID INT NOT NULL,
CONSTRAINT fk_ReservationReceipt_ReservationID
FOREIGN KEY (ReservationID)
REFERENCES Reservation(ReservationID),
CONSTRAINT fk_ReservationReceipt_ReceiptID
FOREIGN KEY (ReceiptID)
REFERENCES Receipt(ReceiptID));
