-- liquibase formatted sql

-- changeset albert_tt:1
CREATE TABLE notifications
(
    id                   SERIAL PRIMARY KEY,
    chatId               SERIAL,
    notificationDateTime TIMESTAMP,
    notificationText     TEXT
);




