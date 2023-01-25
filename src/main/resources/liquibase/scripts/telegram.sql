-- liquibase formatted sql

-- changeset albert_tt:1
CREATE TABLE notification_task
(
    id                   SERIAL PRIMARY KEY,
    chatId               SERIAL,
    notificationDateTime TIMESTAMP,
    notificationText     TEXT
);




