ALTER TABLE ingredients DROP COLUMN calories;
ALTER TABLE ingredients DROP COLUMN proteins;
ALTER TABLE ingredients DROP COLUMN fats;
ALTER TABLE ingredients DROP COLUMN carbohydrates;

ALTER TABLE ingredients ADD COLUMN calories float not null;
ALTER TABLE ingredients ADD COLUMN proteins float not null;
ALTER TABLE ingredients ADD COLUMN fats float not null;
ALTER TABLE ingredients ADD COLUMN carbohydrates float not null;