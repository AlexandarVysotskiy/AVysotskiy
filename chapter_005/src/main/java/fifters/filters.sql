--create table type(id serial primary key, name varchar(100));

--create table product (id serial primary key, name varchar(100), type_id int references type(id), expired_date date, price int);

--insert into type(name) values ('Cheese');
--insert into type(name) values ('Ice cream');
--insert into type(name) values ('Milk');

--insert into product(name, type_id, expired_date, price) values('Ours milk', (select id from type where name = 'Milk'), '2018-11-01', 3);
--insert into product(name, type_id, expired_date, price) values ('US milk', (select id from type where name = 'Milk'), '2018-11-29',  '3');
--insert into product(name, type_id, expired_date, price) values ('Our milk', (select id from type where name = 'Milk'), '2018-12-31',  '2');

--insert into product(name, type_id, expired_date, price) values ('Acorn', (select id from type where name = 'Cheese'), '2018-12-25', 44);
--insert into product(name, type_id, expired_date, price) values ('Abertam', (select id from type where name = 'Cheese'), '2018-12-31', 11);
--insert into product(name, type_id, expired_date, price) values ('Anari', (select id from type where name = 'Cheese'), '2018-12-29', 77);

--insert into product(name, type_id, expired_date, price) values ('Ice cream with alcohol', (select id from type where name = 'Ice cream'), '2018-12-25', 16);
--insert into product(name, type_id, expired_date, price) values ('Ice cream with nuts', (select id from type where name = 'Ice cream'), '2018-12-31', 10);
--insert into product(name, type_id, expired_date, price) values ('ice cream with smoke', (select id from type where name = 'Ice cream'), '2018-12-21', 12);

-- 1. 1. Написать запрос получение всех продуктов с типом "СЫР".
select p.name from product as p left JOIN type as t ON p.type_id  = t.id group by p.name, t.name having t.name = 'Cheese';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
select name from product where name like '%Ice cream%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select name from product where EXTRACT(MONTH FROM expired_date) = EXTRACT(MONTH FROM now())+1;

--4. Написать запрос, который выводит самый дорогой продукт.
select name, price from product where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
--5.1 Кол-во всех сыров.
select count(p.name) from product as p left JOIN type as t ON p.type_id  = t.id where t.name = 'Cheese';
--5.2 Кол-во всего молока.
select count(p.name) from product as p left JOIN type as t ON p.type_id  = t.id where t.name = 'Milk';
--5.3 Кол-во всего мороженного.
select count(p.name) from product as p left JOIN type as t ON p.type_id  = t.id where t.name = 'Ice cream';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
select p.name from product as p left JOIN type as t ON p.type_id  = t.id group by p.name, t.name having t.name = 'Cheese' or t.name ='Milk';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 4 штук.
--insert into product(name, type_id, expired_date, price) values ('ice cream with strawberry', (select id from type where name = 'Ice cream'), '2018-12-22', 12);
select t.name from type AS t left JOIN product AS p ON t.id = p.type_id GROUP BY t.name HAVING count(p.type_id = t.id) < 4;

--8. Вывести все продукты и их тип.
select p.name, t.name from product AS p left join type as t on t.id = p.type_id;