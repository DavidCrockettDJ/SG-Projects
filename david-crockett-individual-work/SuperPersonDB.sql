DROP DATABASE IF EXISTS SuperPersonDB;

CREATE DATABASE IF NOT EXISTS SuperPersonDB;

USE SuperPersonDB;

CREATE TABLE IF NOT EXISTS SuperPerson (
SuperPersonID INT NOT NULL AUTO_INCREMENT,
SuperName VARCHAR(45) NOT NULL,
Description VARCHAR(45) NOT NULL,
Hero BOOLEAN NOT NULL,
PRIMARY KEY (SuperPersonID));

CREATE TABLE IF NOT EXISTS Power (
PowerID INT NOT NULL AUTO_INCREMENT,
Description VARCHAR(45) NOT NULL,
PRIMARY KEY (PowerID));

CREATE TABLE IF NOT EXISTS SuperPowers (
SuperPersonID INT NOT NULL,
PowerID INT NOT NULL,
CONSTRAINT fk_SuperPowers_SuperPersonID
FOREIGN KEY (SuperPersonID)
REFERENCES SuperPerson(SuperPersonID),
CONSTRAINT fk_SuperPowers_PowerID
FOREIGN KEY (PowerID)
REFERENCES `Power`(PowerID));

CREATE TABLE IF NOT EXISTS Organization (
OrganizationID INT NOT NULL AUTO_INCREMENT,
OrganizationName VARCHAR(45) NOT NULL,
Email VARCHAR(45) NULL,
Description VARCHAR(75) NULL,
PRIMARY KEY (OrganizationID));

CREATE TABLE IF NOT EXISTS SuperOrganization (
SuperPersonID INT NOT NULL,
OrganizationID INT NOT NULL,
CONSTRAINT fk_SuperOrganization_SuperPersonID
FOREIGN KEY (SuperPersonID)
REFERENCES SuperPerson(SuperPersonID),
CONSTRAINT fk_SuperOrganization_OrganizationID
FOREIGN KEY (OrganizationID)
REFERENCES Organization(OrganizationID));

CREATE TABLE IF NOT EXISTS Address (
AddressID INT NOT NULL AUTO_INCREMENT,
OrganizationID INT NULL,
PRIMARY KEY (AddressID),
CONSTRAINT fk_Address_OrganizationID
FOREIGN KEY (OrganizationID)
REFERENCES Organization(OrganizationID));

CREATE TABLE IF NOT EXISTS Location (
LocationID INT NOT NULL AUTO_INCREMENT,
LocationName VARCHAR(45) NOT NULL,
Description VARCHAR(75) NOT NULL,
Date DATE NOT NULL,
Latitude DECIMAL(8, 6) NOT NULL,
Longitude DECIMAL(9, 6) NOT NULL,
AddressID INT NOT NULL,
PRIMARY KEY (LocationID),
CONSTRAINT fk_Location_AddressID
FOREIGN KEY (AddressID)
REFERENCES Address(AddressID));

CREATE TABLE IF NOT EXISTS Sighting (
SightingID INT NOT NULL AUTO_INCREMENT,
Date DATE NOT NULL,
LocationID INT NOT NULL,
PRIMARY KEY (SightingID),
CONSTRAINT fk_Sighting_LocationID
FOREIGN KEY (LocationID)
REFERENCES Location(LocationID));

CREATE TABLE IF NOT EXISTS SuperSighting (
SuperPersonID INT NOT NULL,
SightingID INT NOT NULL,
CONSTRAINT fk_SuperSighting_SuperPersonID
FOREIGN KEY (SuperPersonID)
REFERENCES SuperPerson(SuperPersonID),
CONSTRAINT fk_SuperSighting_SightingID
FOREIGN KEY (SightingID)
REFERENCES Sighting(SightingID));