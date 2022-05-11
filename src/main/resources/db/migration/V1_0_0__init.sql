CREATE TABLE system
(
    system_id SERIAL PRIMARY KEY,
    name  TEXT NOT NULL
);
ALTER SEQUENCE system_system_id_seq RESTART 1001;
INSERT INTO system (system_id, name) values (1000, 'CANONICAL');