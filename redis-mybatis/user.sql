use test;

drop table if exists user;
create table user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age INT NOT NULL,
  city VARCHAR(30)
);