DROP SCHEMA if exists hb_01_one_to_one_uni;
CREATE SCHEMA hb_01_one_to_one_uni;
set schema hb_01_one_to_one_uni;
set referential_integrity false;

drop table if exists instructor_detail;

create table instructor_detail
(
    id              int primary key auto_increment not null,
    youtube_channel varchar(128) default null,
    hobby           varchar(45)  default null
);

drop table if exists instructor;

create table instructor
(
    id                   int primary key auto_increment not null,
    first_name           varchar(45) default null,
    last_name            varchar(45) default null,
    email                varchar(45) default null,
    instructor_detail_id int         default null,
    foreign key (instructor_detail_id) references instructor_detail (id)
        on delete no action on update no action
);

set referential_integrity true;