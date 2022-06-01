insert into role(id, name)
values (1, 'ADMIN'),
       (2, 'AGENT'),
       (3, 'COURIER'),
       (4, 'CLIENT');

insert into office(address, city, office_name)
values ('Sofia, Mladost 1', 'Sofia', 'Mladost 1 NBU Logistic'),
       ('Sofia, Mladost 1a', 'Sofia', 'Mladost 1a NBU Logistic'),
       ('Sofia, Mladost 2', 'Sofia', 'Mladost 2 NBU Logistic'),
       ('Sofia, Mladost 3', 'Sofia', 'Mladost 3 NBU Logistic'),
       ('Sofia, Mladost 4', 'Sofia', 'Mladost 4 NBU Logistic'),
       ('Sofia, Lulin 1', 'Sofia', 'Lulin 1 NBU Logistic'),
       ('Sofia, Lulin 2', 'Sofia', 'Lulin 2 NBU Logistic'),
       ('Sofia, Lulin 3', 'Sofia', 'Lulin 3 NBU Logistic'),
       ('Sofia, Lulin 4', 'Sofia', 'Lulin 4 NBU Logistic'),
       ('Sofia, Lulin 5', 'Sofia', 'Lulin 5 NBU Logistic'),
       ('Sofia, Lulin 6', 'Sofia', 'Lulin 6 NBU Logistic'),
       ('Sofia, Lulin 7', 'Sofia', 'Lulin 7 NBU Logistic'),
       ('Sofia, Lulin 8', 'Sofia', 'Lulin 8 NBU Logistic'),
       ('Sofia, Lulin 9', 'Sofia', 'Lulin 9 NBU Logistic'),
       ('Sofia, Lulin 10', 'Sofia', 'Lulin 10 NBU Logistic');


INSERT INTO api_user ("name", "password", username)
VALUES ('Ivan Ivanov', '$2a$10$31sCb28VeTaqu/odel2txusm.p1vEzZmiv09Wwrka6DJnbLjn9ehG', 'vankata1'),
       ('Petar Petrov', '$2a$10$q4Pm38HMIG7YRs5Lcj4hy.d2VPyjR10iJfMj9wQj.6TwgV8ZtY/Iu', 'pesho1'),
       ('Alexander Stoyanov', '$2a$10$qpCpuHMihOpd6VMk4rgnXeYfvReRmqrnn0B0AaOckUPscTu45VknC', 'sashenkata1'),
       ('Petar Tudzharov', '$2a$10$oVhjsGlf9W0ZIWdtl28wI.FFxOhQLhaXw1BJ1.9B7nH4wc2n57w6q', 'djaro1'),
       ('Vladimir Popov', '$2a$10$J8Hg8/.MBkH.bS6UpKthq.kOddpipVV47SumwFBYYqpIXDcPjzziu', 'popov1'),
       ('Martin Hristov', '$2a$10$cc1IDkpUEnT39n6o5goIDOw0saBJXKAEHMSscxQcJ0mR7TMgk99aG', 'hristov1'),
       ('Ivaylo Zahariev', '$2a$10$mv2Md3tL1pBl6UimCjC1M.nJVNAuHETU88dKNnAJdmH0hHJoY5lI6', 'ivak1'),
       ( 'Vladimir Chervenski', '$2a$10$JS3T20hlshjckCFcwIW7SOQIIUW5/VLLI0TCiCi246YhZp0U0CFnS', 'vlado1');

INSERT INTO api_user_roles (api_user_id, roles_id)
VALUES (1, 4),
       (2, 4),
       (8, 4),
       (3, 3),
       (7, 3),
       (5, 2),
       (6, 2),
       (4, 1);