DROP TABLE account IF EXISTS;
CREATE TABLE account (id SERIAL PRIMARY KEY, owner VARCHAR(80), amount NUMERIC(1000,2));

insert into account (owner,amount) values ('DUPONT', 1000);
insert into account (owner,amount) values ('DURAND', 2000);


DROP TABLE operation IF EXISTS;
CREATE TABLE operation (id SERIAL PRIMARY KEY, date_operation TIMESTAMP, description VARCHAR(255), amount NUMERIC(1000,2), account_id NUMERIC);

insert into operation (date_operation,description,amount,account_id) values ('2024-05-19', 'Salaire', 1000,1);
insert into operation (date_operation,description,amount,account_id) values ('2024-05-19', 'Resto', 100,1);

