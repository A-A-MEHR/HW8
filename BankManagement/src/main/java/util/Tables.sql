create table Users
(
    id        INTEGER PRIMARY KEY generated always as identity,
    user_name varchar(255) not null,
    password  varchar(255) not null

);
create table Accounts
(
    id             INTEGER PRIMARY KEY generated always as identity,
    account_number INTEGER not null,
    credit         INTEGER not null,
    owner_id       INTEGER not null,
    foreign key (owner_id) REFERENCES Users (id)
);
create table Credit_cards
(
    id          INTEGER primary key generated always as identity,
    card_number INTEGER not null,
    account_id  INTEGER not null,
    foreign key (account_id) references Accounts (id),
    is_active   bool    not null
);
create table Banks
(
    id            INTEGER primary key generated always as identity,
    city          varchar(255) not null,
    branch_number INTEGER      not null,
    name          varchar(255) not null
);
CREATE TABLE Transactions
(
    id             INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    status         transaction_status NOT NULL,
    date           DATE,
    time           TIME,
    amount         INTEGER            NOT NULL,
    credit_card_id INTEGER            NOT NULL,
    FOREIGN KEY (credit_card_id) REFERENCES Credit_cards (id)
);

CREATE TYPE transaction_status AS ENUM ('NORMAL', 'PAYA_SINGLE', 'PAYA_SECTIOAL', 'SATNA');
ALTER TABLE transactions
    ADD status transaction_status NOT NULL;
ALTER TABLE Credit_cards
    ADD bank_name VARCHAR(255) NOT NULL;
CREATE TYPE success AS ENUM ('SUCCESSFUL', 'UN_SUCCESSFUL');
CREATE TYPE status AS ENUM ('NORMAL', 'PAYA_SINGLE', 'PAYA_SECTIOAL', 'SATNA');
ALTER TABLE transactions
    ADD status status;
ALTER TABLE transactions
    ADD success success NOT NULL;
ALTER TABLE accounts
    ADD bank_name varchar(255) not null;
ALTER TABLE credit_cards
    DROP bank_name;
ALTER TABLE accounts
    ADD shaba_number INTEGER not null;
ALTER TABLE credit_cards
    ADD shaba_number INTEGER not null;
ALTER table transactions
    DROP status;

INSERT INTO transactions (status, date, time, amount, credit_card_id, success)
VALUES ('NORMAL', '2024-06-04', '14:30:00', 100, 3, 'SUCCESSFUL');
INSERT INTO transactions (status, date, time, amount, credit_card_id, success)
VALUES (('SATNA')::status, ('2024-06-06 +03:30'), ('17:09:43+03:30'), ('60000000'::int4), ('5'::int4),
        ('SUCCESSFUL')::success)
