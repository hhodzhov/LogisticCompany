create table logistic_company (
                                  id  bigserial not null,
                                  central_office_address varchar(255),
                                  city varchar(255),
                                  country varchar(255),
                                  name varchar(255),
                                  primary key (id)
);

insert into logistic_company (central_office_address, city, country, name)
values ('zhk. Ovcha Kupel 2', 'Sofia', 'Bulgaria', 'NBU LOGISTICS');

alter table office
    add column logistic_company_id int8;

alter table office
    add constraint FK413ijhlekjh6ijeyg32pijsvb
        foreign key (logistic_company_id)
            references logistic_company;

update office
set logistic_company_id = 1;