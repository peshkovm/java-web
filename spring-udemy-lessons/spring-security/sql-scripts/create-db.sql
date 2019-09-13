DROP SCHEMA if exists spring_security_demo_plaintext;
CREATE SCHEMA spring_security_demo_plaintext;
set schema spring_security_demo_plaintext;

set referential_integrity false;

drop table if exists users;

create table users
(
    username varchar(50) primary key not null,
    password varchar(50)             not null,
    enabled  tinyint(1)              not null
);

insert into users (username, password, enabled)
values ('john', '{noop}test123', 1),
       ('mary', '{noop}test123', 1),
       ('susan', '{noop}test123', 1);

drop table if exists authorities;

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) default null,
    unique (username, authority),
    foreign key (username) references users (username)
);

insert into authorities(username, authority)
values ('john', 'ROLE_EMPLOYEE'),
       ('mary', 'ROLE_EMPLOYEE'),
       ('mary', 'ROLE_MANAGER'),
       ('susan', 'ROLE_EMPLOYEE'),
       ('susan', 'ROLE_ADMIN');

set referential_integrity true;