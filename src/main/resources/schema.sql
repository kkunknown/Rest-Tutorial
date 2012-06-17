drop database program_tutorial;
create database program_tutorial;

use program_tutorial;
create table employee (
	id  int not null AUTO_INCREMENT,
	name varchar(200),
	email varchar(200),
	
	primary key (id)
);

insert into employee values (1, "Huang Yi Ming", "huangyim@cn.ibm.com");
insert into employee values (2, "Wu Dong Fei", "wudong@cn.ibm.com");