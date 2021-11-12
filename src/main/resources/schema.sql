SET SCHEMA PUBLIC;

DROP TABLE if EXISTS lords CASCADE;
DROP TABLE if EXISTS planets;

CREATE TABLE lords (

    id integer primary key,
    name varchar(45) not null,
    age integer not null

);
CREATE TABLE planets (

    id integer primary key,
    name varchar(45) not null,
    lord_id integer,

    foreign key (lord_id) references lords (id)

);
INSERT INTO lords (id,name,age) VALUES

        (1,'Мавродий',66),
        (2,'Григорий',58),
        (3,'Пан',32),
        (4,'Лангуст',51),
        (5,'Кирилл',77),
        (6,'Адамс',36),
        (7,'Ирис',98),
        (8,'Лавер',78),
        (9,'Горох',87),
        (10,'Баклажан',85),
        (11,'Вильгельм',59),
        (12,'Вильгельм',90);

INSERT INTO planets(id,name) VALUES

        (1,'Роленс'),
        (2,'Калебс'),
        (3,'Маджи'),
        (4,'Ниагро'),
        (5,'Калисса'),
        (6,'Манго-Трес'),
        (7,'Абракамбра'),
        (8,'Далавер'),
        (9,'Калиф'),
        (10,'НьюМанки'),
        (11,'Йоркфур'),
        (12,'Мальва'),
        (13,'Гангра'),
        (14,'Данемо');

INSERT INTO planets(id,name,lord_id) VALUES

        (15,'Ливербун',1),
        (16,'Ферно',2);

