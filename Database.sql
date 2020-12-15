---------------------------------------TABLES--------------------------------------------

CREATE table Users (
	User_Id NUMBER(10) Primary key ,
	First_name VARCHAR2(255) not null ,
	Last_name VARCHAR2(255) not null,
	Email_id VARCHAR2(255) not null,
	Phone_Number Number(10)NOT NULL,
	User_name VARCHAR2(50) unique not null, 
	Password_hash VARCHAR2(255) not null,
	User_Status VARCHAR2(255),
	Role VARCHAR2(10) NOT NULL
);

CREATE table User_Details (
	User_detail_id NUMBER(10) primary key,
	User_Id NUMBER(10), 
	constraint FK_User_Id Foreign key(User_Id) references Users(User_Id),
	Joined_Date Date NOT NULL,
	Bank_name VARCHAR2(255) not null,
	Account_No VARCHAR2(12) not null,
	Ifsc_Code VARCHAR2(20) not null,
	Address_line1 VARCHAR2(255),
	Address_line2 VARCHAR2(255),
	City VARCHAR2(30) NOT NULL,
	State VARCHAR2(30) NOT NULL,
	Zipcode VARCHAR2(6) CHECK (LENGTH(Zipcode) = 6) NOT NULL
);

CREATE table Cards (
	Card_Id NUMBER(10) PRIMARY KEY,
	User_Id NUMBER(10),
	constraint FK_User_Id2 Foreign key(User_Id) references Users(User_Id),
	Card_No NUMBER(20) not null,
	Card_type VARCHAR2(20) NOT NULL,
	Expiry_Date DATE NOT NULL,
	limit number(10) NOT NULL,
	status varchar2(50) NOT NULL,
	issue_date DATE,
	joining_fee NUMBER(10)
);


CREATE table Products (
	Product_Id NUMBER(10) PRIMARY KEY,
	Product_Name VARCHAR2(255) NOT NULL,
	Product_cost NUMBER(10) NOT NULL,
    display_size varchar2(255),
    resolution varchar2(255),
    OS varchar2(255),
    processor varchar2(255),
    internal_memmory varchar2(255),
    ram varchar2(255),
    No_of_Camera varchar2(255),
    No_of_front_camera varchar2(255),
    usb varchar2(255),
    color varchar2(255),
    image varchar2(255) NOT NULL
);


CREATE table Transactions (
	Transaction_Id NUMBER(10) PRIMARY KEY,
	User_Id NUMBER(10),
	constraint FK_User_Id3 Foreign key(User_Id) references Users(User_Id),
	Product_Id NUMBER(10),
	constraint FK_Product_Id Foreign key(Product_Id) references Products(Product_Id),
	Monthly_installment NUMBER(20) NOT NULL,
	Tenure VARCHAR2(20) NOT NULL,
	Transaction_Date DATE NOT NULL,
	Status VARCHAR2(10) NOT NULL,
	Processing_Fee NUMBER(20) NOT NULL
);


CREATE table Monthly_Transactions (
	MTxn_Id NUMBER(10) PRIMARY KEY,
	Transaction_Id NUMBER(10),
	constraint FK_Transaction_Id Foreign key(Transaction_Id) references Transactions(Transaction_Id),
	Amount NUMBER(20) NOT NULL,
	MTxn_Date DATE NOT NULL,
	M_Status VARCHAR2(50)
);


------------------------------------------SEQUENCES---------------------------------------

CREATE SEQUENCE user_sequence
    START WITH 100 INCREMENT BY 1;
    
CREATE SEQUENCE user_details_sequence
    START WITH 1 INCREMENT BY 1;
    
CREATE SEQUENCE cards_sequence
    START WITH 1 INCREMENT BY 1;
    
CREATE SEQUENCE transactions_sequence
    START WITH 1000 INCREMENT BY 1;
    
CREATE SEQUENCE monthly_transactions_sequence
    START WITH 100 INCREMENT BY 1;
    
CREATE SEQUENCE product_sequence
    START WITH 1 INCREMENT BY 1;