
DROP DATABASE if exists scrumdog_oyo;
CREATE DATABASE IF NOT EXISTS Scrumdog_oyo;
SET DATABASE =Scrumdog_oyo;
SHOW DATABASE;

CREATE TABLE IF NOT EXISTS State
(
    state_id int PRIMARY KEY  DEFAULT unique_rowid(),
    state_name VARCHAR NOT NULL ,
    country VARCHAR DEFAULT 'USA'
);


CREATE TABLE IF NOT EXISTS City(
    city_id INT PRIMARY KEY DEFAULT unique_rowid(),
    city_name VARCHAR NOT NULL,
    state_id INT NOT NULL,
    CONSTRAINT fk_City FOREIGN KEY  (state_id) REFERENCES State(state_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Address(
    Address_id INT PRIMARY KEY DEFAULT unique_rowid(),
    Address1 varchar NOT NULL ,
    Address2 varchar,
    landmark varchar,
    city_id INT NOT NULL ,
    pin_code int NOT NULL ,
    CONSTRAINT  fk_Address_city FOREIGN KEY (city_id) REFERENCES City(city_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Hotel_type(
    type_id INT PRIMARY KEY DEFAULT unique_rowid(),
    type_name varchar
);

CREATE TABLE IF NOT EXISTS Hotel (
	hotel_id INT PRIMARY KEY DEFAULT unique_rowid(),
	hotelName VARCHAR NOT NULL ,
	hotelTypeId INT NOT NULL ,
	hotelWheelchairAcc BOOLEAN,
	hotelCovidCertified BOOLEAN,
	hotelPetsAllowed BOOLEAN,
	hotelPhone1 VARCHAR NOT NULL ,
	hotelPhone2 VARCHAR,
	hotelEmail VARCHAR NOT NULL ,
	hotelImagePath VARCHAR NOT NULL ,
	hotelDescription TEXT NOT NULL ,
	hotelCreatedOn timestamp,
	hotelModifiedOn timestamp,
	hotelIsActive BOOLEAN NOT NULL ,
	hotelAddressId INT NOT NULL ,
	CONSTRAINT fk_hotel_address FOREIGN KEY (hotelAddressId) REFERENCES Address(address_id),
	CONSTRAINT  fk_hotel_type FOREIGN KEY (hotelTypeId) REFERENCES  Hotel_type(type_id)
) ;

CREATE TABLE IF NOT EXISTS Food(
	foodId INT PRIMARY KEY DEFAULT unique_rowid(),
	foodName VARCHAR NOT NULL ,
	foodDesc VARCHAR
);
CREATE TABLE IF NOT EXISTS Hotel_Food(
	hotel_id INT,
	foodId INT,
	foodStatus BOOLEAN NOT NULL ,
	foodCost INT NOT NULL ,
	PRIMARY KEY(hotel_id,foodId),
	CONSTRAINT fk_Hotel_Food FOREIGN KEY(hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
	CONSTRAINT fk_Hotel_food_it FOREIGN KEY (foodId) REFERENCES  Food(foodId) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Facilities(
	facilityId INT PRIMARY KEY DEFAULT unique_rowid(),
	facilityName VARCHAR,
	facilityDesc VARCHAR
);
CREATE TABLE IF NOT EXISTS Hotel_facility(
	facilityId INT,
	hotel_id INT,
	facilityStatus BOOLEAN,
	facilityCost INT,
	facilityFree BOOLEAN,
	CONSTRAINT fk_Hotel_facility FOREIGN KEY(hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
	CONSTRAINT fk_Hotel_Facility_id FOREIGN KEY(facilityId) REFERENCES Facilities(facilityId) ON DELETE CASCADE,
	CONSTRAINT pk_Hotel_Facility PRIMARY KEY (facilityId,hotel_id)
);


CREATE TABLE IF NOT EXISTS Account(
    account_id VARCHAR PRIMARY KEY ,
    password VARCHAR NOT NULL ,
    password_salt varchar,
    password_last_update DATE,
    status BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS base_User(
user_id INT PRIMARY KEY DEFAULT unique_rowid(),
user_FirstName varchar,
user_MiddleName varchar,
user_LastName varchar,
user_Phone varchar,
user_email varchar,
user_address_id INT,
user_created_on date,
user_modified_on date,
user_end_date date,
CONSTRAINT fk_user_address FOREIGN KEY (user_address_id) REFERENCES Address(address_id),
CONSTRAINT fk_user_account FOREIGN KEY (user_email) REFERENCES Account(account_id)
);

CREATE TABLE IF NOT EXISTS Employee(
Emp_id INT PRIMARY KEY DEFAULT unique_rowid(),
user_id INT,
hotel_id INT,
Emp_is_admin boolean,
CONSTRAINT fk_Employee_user FOREIGN KEY (user_id) references base_User(user_id) on delete cascade,
CONSTRAINT fk_Employee_hotel FOREIGN KEY (hotel_id) references  Hotel(hotel_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS Customer(
    Customer_id INT PRIMARY KEY DEFAULT unique_rowid(),
    user_id INT,
    customer_points int,
    CONSTRAINT fk_Customer_user FOREIGN KEY (user_id) references base_User(user_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS Room_Type(
    Type_id INT PRIMARY KEY DEFAULT unique_rowid(),
    Type_name varchar,
    description varchar
);

CREATE TABLE IF NOT EXISTS Hotel_Room_Type(
    hotel_id INT,
    type_id INT,
    room_capacity INT DEFAULT 0,
    room_type_count INT,
    type_img_url VARCHAR,
    type_base_price FLOAT,
    type_weekend_surge FLOAT,
    type_holiday_surge FLOAT,
    CONSTRAINT fk_hotel_room_type_id FOREIGN KEY (type_id) REFERENCES Room_Type(Type_id),
    CONSTRAINT pk_hotel_room_type PRIMARY KEY (hotel_id,type_id)
);

CREATE TABLE IF NOT EXISTS Room(
    Room_id INT PRIMARY KEY DEFAULT unique_rowid(),
    Room_type_id INT,
    Room_number int,
    room_floor int,
    Room_status boolean,
    Room_created_on date,
    Room_modified_on date,
    Hotel_Id INT,
    CONSTRAINT fk_roomType FOREIGN KEY (Room_type_id) REFERENCES  Room_Type(Type_id),
    CONSTRAINT fk_room_HotelId FOREIGN KEY (Hotel_Id) REFERENCES Hotel(hotel_id)
);


CREATE TABLE IF NOT EXISTS Booking_Status(
    Booking_status_id INT PRIMARY KEY DEFAULT unique_rowid(),
    status VARCHAR,
    active BOOLEAN
);



CREATE TABLE IF NOT EXISTS Booking(
    boooking_id INT PRIMARY KEY DEFAULT unique_rowid(),
    Customer_id INT,
    Room_id INT,
    boooking_start_date DATE,
    booking_end_date DATE,
    booking_days INT,
    booking_breakfast BOOLEAN,
    booking_fitness BOOLEAN,
    booking_swimming BOOLEAN,
    booking_parking BOOLEAN,
    booking_all_meals BOOLEAN,
    booking_cost FLOAT,
    booking_status_id INT,
    CONSTRAINT fk_Booking_customer FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id),
    CONSTRAINT fk_Booking_Room FOREIGN KEY (Room_id) REFERENCES Room(Room_id),
    CONSTRAINT fk_Booking_Status_id FOREIGN KEY (booking_status_id) REFERENCES  Booking_Status(Booking_status_id)
);

show tables ;

/*

INSERT INTO State(STATE_NAME, COUNTRY) VALUES ('California','USA');
SELECT * from State;

INSERT INTO City(city_name, state_id) VALUES ('San Jose',743757765402525698);
SELECT * from City;

INSERT INTO Address(address1, landmark, city_id, pin_code)
VALUES ('101 Washington St','Beside SJSU',743757792724418562,'95110');
INSERT INTO Address(address1, landmark, city_id, pin_code)
VALUES ('33 Coleman St','Near SJC Airport',743757792724418562,'95112');
SELECT * from Address;

INSERT INTO Hotel_type(type_name) VALUES ('Suite');
INSERT INTO Hotel_type(type_name) VALUES ('Bed and Breakfast');
INSERT INTO Hotel_type(type_name) VALUES ('Hostel');
INSERT INTO Hotel_type(type_name) VALUES ('Resort');
SELECT * from Hotel_type;

select current_timestamp;
INSERT INTO Hotel(HOTELNAME, HOTELTYPEID, HOTELWHEELCHAIRACC, HOTELCOVIDCERTIFIED, HOTELPETSALLOWED, HOTELPHONE1, HOTELPHONE2,
                  HOTELEMAIL, HOTELIMAGEPATH, HOTELDESCRIPTION, HOTELCREATEDON, HOTELMODIFIEDON, HOTELISACTIVE, HOTELADDRESSID)
                VALUES ('Fountain Plaza',743757860072947714,TRUE,TRUE,FALSE,'6690991230','6690991231','contact@fp.com',
                        'https://www.liveatfountainplaza.com/wp-content/uploads/2021/08/Fountain_Plaza-210702-0013-1.jpg',
                        'Fountain Plaza is Bed and Breakfast Place in San Jose Downtown','2020-03-11 20:11:41.981031 +00:00',
                        '2022-03-11 20:11:41.981031 +00:00',TRUE,743757837243383810);

select * from  Hotel;

INSERT INTO Food(FOODNAME, FOODDESC) VALUES ('Veg Burger','Burger made with Veg patties and Bun');
SELECT * FROM Food;
INSERT INTO Hotel_Food(HOTEL_ID, FOODID, FOODSTATUS, FOODCOST) VALUES (743757971462291458,743758183969161218,TRUE,10);
SELECT * FROM  Hotel_Food;

INSERT INTO Facilities(facilityName, facilityDesc) VALUES ('Wifi Service','Wireless Internet Connection');
SELECT * FROM Facilities;

INSERT INTO Hotel_facility(FACILITYID, HOTEL_ID, FACILITYSTATUS, FACILITYCOST, FACILITYFREE) VALUES
(743767090016878594,743757971462291458,TRUE,0,TRUE);
SELECT * FROM Hotel_facility;

*/
