CREATE USER blogger@localhost IDENTIFIED BY 'blog1';
GRANT ALL ON spring_blog.* TO blogger@localhost@localhost;

drop database spring_blog;
show databases;
use spring_blog;
show tables;

# describe table users;
drop table  products;
describe products;
 describe post;
describe users;
describe pet_owner;
select * from pet_owner;
describe vet;


describe dogs;
drop table dogs;
select * from pet_owner;

show tables;
select * from post;

describe post;
select * from post;
truncate post;
insert into users (email, password, username)
values ('amber@mail.com', 'aj', 'amberlovescats14'),
       ('mom@mail.com', 'mm', 'momlovescats'),
       ('cami@mail.com', 'cj', 'camilovescats14');

insert into post (title, description, user_id) values
('Squeky Chair', 'Need a new one', 1),
('Good oranges', 'Thick peel', 2),
('LV Purse', 'Please buy me one', 1) ;


insert into pet_owner (name, email, phone_number)
values
       ('amber', 'amber@mail.com', '2104296474'),
       ('mom', 'mom@mail.com', '2107059770'),
       ('cami', 'cami@mail.com', '2104202094');


insert into vet (phone_number, vet_name, pet_owner_id)
values ('2105559898', 'pacheco', 2),
       ('2105559898', 'pacheco', 1);



