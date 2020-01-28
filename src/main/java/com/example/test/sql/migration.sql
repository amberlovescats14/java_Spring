CREATE USER blogger@localhost IDENTIFIED BY 'blog1';
GRANT ALL ON spring_blog.* TO blogger@localhost@localhost;

drop database spring_blog;
show databases;
use spring_blog;
show tables;
drop table  products;
describe products;

describe dogs;
drop table dogs;

