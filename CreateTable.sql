create table building
(
id int identity (1001,1) constraint building_id_PK primary key,
region char(6) not null,
dealdate char(7) not null,
buildtype char(8) not null,
loc char(25) not null,
area numeric(7,2) not null,
price int not null,
parking char(2) not null,
remark varchar(50)
);





