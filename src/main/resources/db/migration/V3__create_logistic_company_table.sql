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

alter table office
    add column logistic_company_id int4;

alter table office
    add constraint FK413ijhlekjh6ijeyg32pijsvb
        foreign key (logistic_company_id)
            references logistic_company;

update office
set logistic_company_id = 1;