CREATE TABLE authorities (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             name VARCHAR(50) NOT NULL UNIQUE
);