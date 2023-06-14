create table IF NOT EXISTS student_details (
	detail_id int not null primary key,
	college varchar(50) not null,
	no_of_problems_solved int not null
);

create table IF NOT EXISTS student (
	student_id int not null primary key,
	firstname varchar(50) not null,
	lastname varchar(50) not null,
	email varchar(50) not null unique,
	detail_id int,
	CONSTRAINT fk_student_details_id
	FOREIGN KEY (detail_id)
	REFERENCES student_details(detail_id)
);

--CREATE SEQUENCE IF NOT EXISTS user_account_id MINVALUE=1000000;

--create unique index ix_auth_username on authorities (username,authority);
