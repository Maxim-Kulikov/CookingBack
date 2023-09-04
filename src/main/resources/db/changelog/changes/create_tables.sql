CREATE TABLE IF NOT EXISTS product_kind (
    id serial primary key,
    name varchar(45) not null
);

CREATE TABLE IF NOT EXISTS ingredient_kind (
    id serial primary key,
    kind varchar(45) not null,
    id_product_kind integer not null,
    foreign key (id_product_kind) references product_kind (id)
);

CREATE TABLE IF NOT EXISTS ingredient_type (
    id serial primary key,
    type varchar(45) not null,
    id_ingredient_kind int not null,
    foreign key (id_ingredient_kind) references ingredient_kind (id)
);

CREATE TABLE IF NOT EXISTS ingredients (
    id serial primary key ,
    id_ingredient_type int not null,
    calories int not null,
    proteins int not null ,
    fats int not null,
    carbohydrates int not null,
    foreign key (id_ingredient_type) references ingredient_type (id)
);

CREATE TABLE IF NOT EXISTS meal_time (
    id serial primary key ,
    day_part varchar(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS dish_type (
    id serial primary key,
    type varchar(45) not null,
    id_meal_time int not null,
    foreign key (id_meal_time) references meal_time (id)
);

CREATE TABLE IF NOT EXISTS dish_name (
    id serial primary key,
    name varchar(45) not null,
    id_dish_type int not null,
    foreign key (id_dish_type) references dish_type (id)
);

CREATE TABLE IF NOT EXISTS dishes (
    id serial primary key,
    id_user int not null,
    id_dish_name int not null,
    cooking_time int not null,
    id_recipe_info varchar(45) not null,
    foreign key (id_dish_name) REFERENCES dish_name (id)
);
