create table new_application.school
(
    id   bigserial primary key,
    name varchar(255)
);

insert into new_application.school(name)
values ('Australia'),
       ('Vietnam'),
       ('Singapore'),
       ('USA'),
       ('India'),
       ('Germany');

create table new_application.city
(
    id   bigserial primary key,
    name varchar(255)
);

insert into new_application.city(name)
values ('Sydney'),
       ('Hanoi'),
       ('New York'),
       ('Paris');

create table new_application.user
(
    id   bigserial primary key,
    name varchar(255),
    gender varchar(1),
    school_id bigint,
    city_id bigint
);

insert into new_application.user(name,gender,school_id, city_id)
values ('Giang','h', '1', '1'),
       ('Khoa','h', '2', '2'),
       ('Vu','h', '3', '3'),
       ('Nguyen','h', '4', '1'),
       ('Trung','h', '5', '4'),
       ('Tu','h', '6', '3'),
       ('Thinh','h', '1', '1'),
       ('Duc','h', '1', '2'),
       ('Dat','h', '3', '4');