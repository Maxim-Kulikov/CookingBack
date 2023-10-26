DROP TABLE dishes;

CREATE TABLE IF NOT EXISTS dishes (
                                      id serial primary key,
                                      id_user int not null,
                                      id_dish_name int not null,
                                      name varchar(45) not null,
                                      cooking_time int not null,
                                      id_recipe_info varchar(45) not null,
    calories float not null,
    proteins float not null,
    fats float not null,
    carbohydrates float not null,
    foreign key (id_dish_name) REFERENCES dish_name (id),
    foreign key(id_user) REFERENCES users (id)
    );
