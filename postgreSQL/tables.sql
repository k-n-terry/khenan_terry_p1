-- database
CREATE database khenan_terry_p1;

-- T01: employee table
create table employee(
	emp_id varchar(32) unique primary key,
	firstname varchar(255) not null,
	lastname varchar(255) not null,
	keyword varchar(255) not null
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

-- selections
select * from employee;
select * from employee where emp_id= 'C577E4C72920997EDBB6653E23D382B7';

-- drop table
drop table employee;
drop table expense;

-- testing NUMERIC's precision:
select cast(4.456 as numeric(255,2));

-- MD5 test
select MD5('KhenanTerryabc123');		--(0aa3105b5d68cddc6c1062755b85999f)
select MD5('BrandonTerryabc123');		--(1332c0c759cd30f95c1ee92d1dfd5c7e)
select MD5('JohnSmithHiFromIntelliJ');	--(c577e4c72920997edbb6653e23d382b7)
