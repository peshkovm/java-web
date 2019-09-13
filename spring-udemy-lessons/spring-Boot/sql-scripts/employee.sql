create schema if not exists EMPLOYEE_DIRECTORY;
set schema EMPLOYEE_DIRECTORY;

drop table if exists employee;

create table employee
(
    id         int(11) not null auto_increment primary key,
    first_name varchar(45) default null,
    last_name  varchar(45) default null,
    email      varchar(45) default null,
);

insert into employee(id, first_name, last_name, email)
values (1, 'Leslie', 'Andrews', 'leslie@luv2code.com'),
       (2, 'Emma', 'Baumgarten', 'emma@luv2code.com'),
       (3, 'Avani', 'Gupta', 'avani@luv2code.com'),
       (4, 'Yuri', 'Petrov', 'yuri@luv2code.com'),
       (5, 'Juan', 'Vega', 'juan@luv2code.com');
