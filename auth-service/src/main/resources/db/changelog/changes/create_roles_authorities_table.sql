CREATE TABLE role_authorities (
    role_id UUID NOT NULL,
    authority_id UUID NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (authority_id) REFERENCES authorities(id)
);