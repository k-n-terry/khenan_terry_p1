-- database
CREATE database khenan_terry_p1;

-- T01: employee table
create table employee(
	emp_id varchar(32) unique primary key,
	firstname varchar(255) not null,
	lastname varchar(255) not null,
	passphrase varchar(255) not null
);

-- T02: expense table
create table expense(
	expns_id serial primary key,
	emp_id varchar(32) not null,
	expns_label varchar(255) not null,
	expns_amount numeric(2) not null,
	status varchar(255) not null,
	foreign key(emp_id) references employee(emp_id) 
);

-- insertion
insert into employee values(
	'C965C80BC13B5E2529CB057158625AF0',
	'John3',
	'Smith3',
	'HelloFromDBeaver'
);

insert into employee values(
	'76FB07B5B80A0F51B022D00DDC29ED41',
	'Some',
	'One',
	'HelloFromDBeaver'
);

-- selections
select * from employee;
select * from employee where emp_id= 'C965C80BC13B5E2529CB057158625AF0';

-- drop table
drop table employee;
drop table expense;

-- delete
delete from employee *;
delete from employee where emp_id='76FB07B5B80A0F51B022D00DDC29ED41';
delete from employee where emp_id='53D64EC479FC1C60F321F91542D26D50';
-- testing NUMERIC's precision:
select cast(4.456 as numeric(255,2));

-- MD5 test
select MD5('KhenanTerryabc123');		--(0aa3105b5d68cddc6c1062755b85999f)
select MD5('BrandonTerryabc123');		--(1332c0c759cd30f95c1ee92d1dfd5c7e)
select MD5('John3Smith3HiFromDBeaver');	--(c577e4c72920997edbb6653e23d382b7)
