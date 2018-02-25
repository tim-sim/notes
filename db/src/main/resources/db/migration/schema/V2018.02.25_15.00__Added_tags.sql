CREATE TABLE tags (
    id  uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name text NOT NULL UNIQUE
);

ALTER TABLE notes ADD COLUMN tags uuid[] DEFAULT '{}' NOT NULL;