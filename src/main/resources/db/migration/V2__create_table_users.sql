CREATE TABLE users(
    id TEXT PRIMARY KEY NOT NULL,
    login TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);