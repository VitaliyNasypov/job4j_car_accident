CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

CREATE TABLE ACCIDENT_TYPES
(
    ID   SERIAL PRIMARY KEY,
    NAME VARCHAR(2000) NOT NULL
);

CREATE TABLE RULES
(
    ID   SERIAL PRIMARY KEY,
    NAME VARCHAR(2000) NOT NULL
);

CREATE TABLE ACCIDENTS
(
    ID               SERIAL PRIMARY KEY,
    NAME             VARCHAR(2000) NOT NULL,
    TEXT             VARCHAR(2000) NOT NULL,
    ADDRESS          VARCHAR(2000) NOT NULL,
    ACCIDENT_TYPE_ID INTEGER REFERENCES ACCIDENT_TYPES (ID)
);

CREATE TABLE ACCIDENTS_RULES
(
    ACCIDENT_ID INTEGER REFERENCES ACCIDENTS (ID),
    RULE_ID     INTEGER REFERENCES RULES (ID)
);

INSERT INTO RULES (NAME)
VALUES ('Article. 1');
INSERT INTO RULES (NAME)
VALUES ('Article. 2');
INSERT INTO RULES (NAME)
VALUES ('Article. 3');

INSERT INTO ACCIDENT_TYPES (NAME)
VALUES ('Two cars');
INSERT INTO ACCIDENT_TYPES (NAME)
VALUES ('Car and man');
INSERT INTO ACCIDENT_TYPES (NAME)
VALUES ('Car and bicycle');

INSERT INTO ACCIDENTS (NAME, TEXT, ADDRESS, ACCIDENT_TYPE_ID)
VALUES ('Name_1', 'Text_1', 'Address_1', 1);
INSERT INTO ACCIDENTS (NAME, TEXT, ADDRESS, ACCIDENT_TYPE_ID)
VALUES ('Name_2', 'Text_2', 'Address_2', 2);
INSERT INTO ACCIDENTS (NAME, TEXT, ADDRESS, ACCIDENT_TYPE_ID)
VALUES ('Name_3', 'Text_3', 'Address_3', 3);

INSERT INTO ACCIDENTS_RULES (ACCIDENT_ID, RULE_ID)
VALUES (1, 1);
INSERT INTO ACCIDENTS_RULES (ACCIDENT_ID, RULE_ID)
VALUES (1, 2);
INSERT INTO ACCIDENTS_RULES (ACCIDENT_ID, RULE_ID)
VALUES (2, 2);
INSERT INTO ACCIDENTS_RULES (ACCIDENT_ID, RULE_ID)
VALUES (2, 3);
INSERT INTO ACCIDENTS_RULES (ACCIDENT_ID, RULE_ID)
VALUES (3, 3);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', '$2a$10$Fg6Nvri6NYgKrwXUHqqYje9yZDYUCnv/NaRoUK7dCv9no8lPLeR/u',
        (select id from authorities where authority = 'ROLE_ADMIN'));