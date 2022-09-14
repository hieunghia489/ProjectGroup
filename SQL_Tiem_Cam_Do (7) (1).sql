
create database Q_ANT;
go
Use [Q_ANT];
go
create table tblAdmin(
adminID nvarchar(12) primary key,
fullName nvarchar(50) not null,
password nvarchar(20) not null
);
create table tblKey(
confirmKey nvarchar(8) primary key,
statusKey bit not null,
adminID nvarchar(12) foreign key references tblAdmin(adminID)
);
go
create table tblStaff(
staffID nvarchar(12) check (staffID not like '%[^0-9]%') primary key,
fullName nvarchar(50) not null,
password nvarchar(20) not null,
storeID INT
);
go
create table tblPawnShop(
storeID INT IDENTITY(1,1) primary key,
storeName nvarchar(20) not null,
storeAddress nvarchar(100) not null,
phoneNumber int not null,
managerID nvarchar(12) check (managerID not like '%[^0-9]%') foreign key references tblStaff(staffID),
confirmKey nvarchar(8) foreign key references tblKey(confirmKey),
statusID bit default 1
);
go 

CREATE TABLE tblCustomer 
(
customerID nvarchar(12) check (customerID not like '%[^0-9]%') PRIMARY KEY,
fullname nvarchar(50) not null,
phoneNumber int not null,
address nvarchar(100) not null,
);

go

create table tblItem( 
        itemID           INT IDENTITY(1,1) primary key, 
        itemName         nvarchar(80) not null,
        itemPic          nvarchar(400) not null,
        customerID     nvarchar(12) foreign key references tblCustomer(customerID),
        storeID        int foreign key references tblPawnshop(storeID),
        itemSendingDate  Date not null,
        itemGettingDate  Date not null,
		statusID bit default 1,
		isKeep bit default 1
);
go

create table tblBill (
	billID INT IDENTITY(1,1) primary key,
	itemID INT foreign key references tblItem(itemID),
	pawnMoney money not null,
	numberDays int not null,
	interestRate float not null,
	billBeginDate DATE not null,
	staffID nvarchar(12) foreign key references tblStaff(staffID),
	returnMoney money default 0
);

create table tblCallHistory(
id INT IDENTITY(1,1) primary key,
itemID  INT foreign key references tblItem(itemID),
date1 date,
date2 date
);


insert [tblAdmin] ([adminID], [fullName],[password]) values ('admin1','Admin','123456');
insert [tblAdmin] ([adminID], [fullName],[password]) values ('admin2',N'Giám đốc ','123456');
go
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('nAtEnAtE','true','admin1');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('hihihihi','true','admin1');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('idonknow','true','admin1');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('iloveyou','true','admin2');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('ihateyou','true','admin2');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('hello123','false','admin1');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('goodbyee','false','admin1');
Insert [tblKey] ([confirmKey],statusKey,adminID) values ('noyesno1','false','admin2');
go
Insert [tblStaff] ([staffID],[password],[fullName],storeID) values ('111111111111','123',N'Trịnh Hữu Trường','');
Insert [tblStaff] ([staffID],[password],[fullName],storeID) values ('666666666666','123',N'Trần Tú Anh','');
Insert [tblStaff] ([staffID],[password],[fullName],storeID) values ('222222222222','123',N'Nguyễn Khánh Toàn','');
Insert [tblStaff] ([staffID],[password],[fullName],storeID) values ('333333333333','123',N'Trương Huy Hoàng','');
Insert [tblStaff] ([staffID],[password],[fullName],storeID) values ('444444444444','123',N'Huỳnh Thị Ân','');
Insert [tblStaff] ([staffID],[password],[fullName],storeID)  values ('555555555555','123',N'Lê Văn Tiến','');
go
Insert [tblPawnShop] (storeName,storeAddress,phoneNumber,managerID,[confirmKey]) values (N'Cầm đồ VIP',N'100 Lê Lợi, Quận 1, Thành Phố Hồ Chí Minh','0912312312','111111111111','nAtEnAtE');
Insert [tblPawnShop] (storeName,storeAddress,phoneNumber,managerID,[confirmKey]) values (N'Cầm đồ Tiến Hải',N'1 Đỗ Xuân Hợp, Quận 9, Thành Phố Thủ Đức','0913213213','222222222222','hihihihi');
Insert [tblPawnShop] (storeName,storeAddress,phoneNumber,managerID,[confirmKey]) values (N'Cầm đồ Miền Nam',N'53 Hoàng Xuân Nhị, Quận Tân Phú, Thành Phố Hồ Chí Minh','0909090909','333333333333','idonknow');
Insert [tblPawnShop] (storeName,storeAddress,phoneNumber,managerID,[confirmKey]) values (N'Lấy Tiền - Đưa đồ',N'69 Bùi Viện, Quận 1, Thành Phố Hồ Chí Minh','0987654321','444444444444','iloveyou');
Insert [tblPawnShop] (storeName,storeAddress,phoneNumber,managerID,[confirmKey]) values (N'Cửa hàng cầm đồ',N'25 Nguyễn Gia Trí, Quận Bình Thạnh, Thành Phố Hồ Chí Minh','0900101010','555555555555','ihateyou');

go
update tblStaff set storeID='1' where staffID='111111111111';
update tblStaff set storeID='2' where staffID='222222222222';
update tblStaff set storeID='3' where staffID='333333333333';
update tblStaff set storeID='4' where staffID='444444444444';
update tblStaff set storeID='5' where staffID='555555555555';
update tblStaff set storeID='1' where staffID='666666666666';
GO
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('010101010101',N'Phan Huyền Trâm',N'123 Lê Văn Việt, Quận 9, Thành Phố Hồ Chí Minh', '0123456789');

insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('020202020202',N'Mai Duy Nam',N'10 Phạm Ngũ Lão, Quận 5, Thành Phố Hồ Chí Minh', '0135792468');
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('030303030303',N'Hàng Sở Nghiêu',N'50 Nguyễn Huệ, Quận 1, Thành Phố Hồ Chí Minh', '0120123012');
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('040404040404',N'Nguyễn Vũ Thành Phúc',N'90 Nguyễn Trãi, Quận 4, Thành Phố Hồ Chí Minh', '0165432178');
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('050505050505',N'Nguyễn Thanh Tân',N'100 Hồ Xuân Hương, Quận 7, Thành Phố Hồ Chí Minh', '0161231239');
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('060606060606',N'Nguyễn Đình Quân',N'12 Lũy Bán Bích, Quận 10, Thành Phố Hồ Chí Minh', '0123456543');
insert [tblCustomer] (customerID,fullname,address,phoneNumber) values ('070707070707',N'Lê Hiếu Trí',N'23 Cao Thắng, Quận 4, Thành Phố Hồ Chí Minh', '0123456666');
go

Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Xe Máy SH','xe-may-SH.jpg','2022-6-22','2022-8-17', 'true','010101010101','1');
Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Quạt máy','quat-may.jpg','2022-7-19','2022-8-2', 'true','010101010101','1');
Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Điện Thoại','dien-thoai.jpg','2022-6-25','2022-7-23', 'true','020202020202','1');
Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Ipad mini','ipad-mini.jpg','2022-7-20','2022-8-3', 'false','030303030303','1');
Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Laptop','laptop.jpg','2022-6-22','2022-8-17', 'true','040404040404','1');
Insert tblItem (itemName,itemPic,itemSendingDate,itemGettingDate,statusID,customerID,storeID) values (N'Đồng hồ','dong-ho.jpg','2022-7-25','2022-8-8', 'true','050505050505','1');

go

Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID,returnMoney) values ('2022-6-22','2.5','50000000','28','1','111111111111','1250000');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID) values ('2022-7-20','2.5','50000000','28','1','111111111111');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID) values ('2022-7-19','2.5','50000','14','2','111111111111');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID,returnMoney) values ('2022-6-25','2.5','10000000','28','3','111111111111','10250000');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID,returnMoney) values ('2022-7-25','2.2','12000000','14','4','111111111111','12264000');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID) values ('2022-6-22','2.4','20000000','28','5','111111111111');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID,returnMoney) values ('2022-7-20','2.4','20480000','28','5','111111111111','20000000');
Insert tblBill (billBeginDate,interestRate,pawnMoney,numberDays,itemID,staffID) values ('2022-7-25','2','2000000','14','6','111111111111');

go

SELECT * FROM tblAdmin;
SELECT * FROM tblKey;

SELECT * FROM tblCustomer;
SELECT * FROM tblItem;
SELECT * FROM tblBill;

SELECT * FROM tblStaff;
SELECT * FROM tblPawnShop;
