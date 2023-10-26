DROP TABLE users;

CREATE TABLE users (
                       id serial primary key,
                       login varchar(45) unique not null,
                       password varchar(150) not null,
                       name varchar(45),
                       lastname varchar(45),
                       is_user_blocked boolean not null,
                       id_role int not null,
                       foreign key (id_role) references roles (id)

)