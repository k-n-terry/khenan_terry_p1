-- database
CREATE database khenan_terry_p1;

-- T01: employee table
create table employee(
	empId varchar(32) unique primary key,
	firstname varchar(255) not null,
	lastname varchar(255) not null,
	registry varchar(8) not null default('Listed')
);

-- T02: expense table
create table expense(
	expenseId serial primary key,
	empId varchar(32) not null,
	expenseLabel varchar(255) not null,
	expenseAmount numeric(255,2) not null,
	status varchar(8) default('Pending'),
	foreign key(empId) references employee(empId) 
);

-- employee insertion (Listed Employees)
insert into employee values(
	'928F3A6C9A6E5BABDF5B5CFFEA476868',
	'D_01',
	'Beaver_01',
	'Listed'	
);

insert into employee values(
	'04B89AEACEB4A43EE5DAB3ACA2B15DD2',
	'D_02',
	'Beaver_02',
	'Listed'	
);

-- insertion (Unlisted Employees)
insert into employee values(
	'CDC996828540D64AD3E008E081ABF988',
	'D_03',
	'Beaver_03',
	'Listed'	
);

-- expense insertion
insert into expense values(
	1,
	'928F3A6C9A6E5BABDF5B5CFFEA476868',
	'Expense01 from DBeaver',
	99.99,
	'Pending'	
);

insert into expense values(
	2,
	'04B89AEACEB4A43EE5DAB3ACA2B15DD2',
	'Expense02 from DBeaver',
	149.99,
	'Pending'	
);

insert into expense values(
	3,
	'CDC996828540D64AD3E008E081ABF988',
	'Expense03 from DBeaver',
	50.99,
	'Approved'	
);


-- selections
select * from employee;
select * from employee where empId= 'CDC996828540D64AD3E008E081ABF988';
select * from employee where empId = '928F3A6C';

select * from expense;
select * from expense where expenseId = 1;

-- drop table
drop table employee;
drop table expense;
-- update

-- delete
delete from expense *;
delete from expense where empId='928F3A6C9A6E5BABDF5B5CFFEA476868';
-- testing NUMERIC's precision:
select cast(4.456 as numeric(255,2));

-- MD5 test
select upper(MD5('KhenanTerryListed'));			--(BD816121754F851BCC59CA8EE7D7A2CE)
select upper(MD5('BrandonTerryListed'));		--(898A2D0EC470101707C121D762EC0C23)

select upper(MD5('D_01Beaver_01Listed')); 		--(928F3A6C9A6E5BABDF5B5CFFEA476868)
select upper(MD5('D_02Beaver_02Listed'));		--(04B89AEACEB4A43EE5DAB3ACA2B15DD2)
select upper(MD5('D_03Beaver_03Listed'));		--(CDC996828540D64AD3E008E081ABF988)

select upper(MD5('Intelli_01J_01Listed'));		--(F4388D30B20EA0F2CEC8A934A0BB44FB)

select upper(MD5('PostMan'));					--(B8E3278568FC8BE24D7095D33152732A)
select upper(MD5('DeleteriusMaximus'));			--(439762E804FA118D75C3CA100A41F8F8)

update expense set status='Pending' where expenseId=3;
