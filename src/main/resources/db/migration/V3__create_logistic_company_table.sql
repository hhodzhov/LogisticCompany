create table "nbu-project".logistic_company
(
    id                     int4 primary key,
    name                   varchar,
    country                varchar,
    city                   varchar,
    central_office_address varchar
);

insert into "nbu-project".logistic_company
values (1, 'NBU LOGISTICS', 'Bulgaria', 'Sofia', 'zhk. Ovcha Kupel 2');
