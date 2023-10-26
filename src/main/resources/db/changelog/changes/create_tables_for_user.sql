CREATE TABLE roles (
    id int primary key not null,
    role varchar(45) not null
);

INSERT INTO roles VALUES (1, 'ADMIN'), (2, 'USER'), (3, 'ANONYMOUS');

CREATE TABLE users (
    id int primary key not null,
    login varchar(45) unique not null,
    password varchar(150) not null,
    name varchar(45),
    lastname varchar(45),
    is_user_blocked boolean not null,
    id_role int not null,
    foreign key (id_role) references roles (id)

)