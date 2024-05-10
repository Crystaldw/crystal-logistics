create schema if not exists catalogue;

create table catalogue.t_table(
    id serial primary key,
    c_title varchar(50) not null check (length(trim(c_title))>=3),
    c_details varchar(1000)
);