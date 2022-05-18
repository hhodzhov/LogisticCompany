create table api_user
(
    id       int8 not null,
    name     varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table role
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table api_user_roles
(
    api_user_id int8 not null,
    roles_id    int8 not null
);

create table office
(
    id          bigserial not null,
    address     varchar(255),
    city        varchar(255),
    office_name varchar(255),
    primary key (id)
);

create table shipment
(
    id                  int8    not null,
    delivery_address    varchar(255),
    recipient_name      varchar(255),
    sender_name         varchar(255),
    sent_date           timestamp,
    shipment_status     int4,
    to_office           boolean not null,
    updated_date        timestamp,
    weight              float8,
    sent_from_office_id int8,
    sent_to_office_id   int8,
    primary key (id)
);

--add constraints
alter table api_user_roles
    add constraint FKlaho9asox4snycrybdgcvgyuy
        foreign key (roles_id)
            references role;

alter table api_user_roles
    add constraint FKei8j58q6ht0dy1psbp8s4ciot
        foreign key (api_user_id)
            references api_user;


alter table shipment
    add constraint FKlguve12thd2okbewhg746ckat
        foreign key (sent_from_office_id)
            references office;

alter table shipment
    add constraint FK6dwp0b76p52wdqh0gyvo5m41b
        foreign key (sent_to_office_id)
            references office;