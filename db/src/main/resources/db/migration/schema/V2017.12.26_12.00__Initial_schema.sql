CREATE TABLE users (
    id  uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_name text NOT NULL,
    password text NOT NULL,
    doc jsonb DEFAULT '{}' NOT NULL
);

CREATE TABLE notes (
    id  uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    title text NOT NULL,
    description text,
    doc jsonb DEFAULT '{}' NOT NULL,
    created_date timestamp DEFAULT now() NOT NULL,
    user_id uuid NOT NULL REFERENCES users(id)
);