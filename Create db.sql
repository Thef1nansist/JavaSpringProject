create table role
(
role varchar(10) primary key
);

create table User
(
id int primary key auto_increment,
login varchar(50) not null,
password varchar(255) not null,
email varchar(50) not null,
role_fk varchar(10) not null unique references role(role) 
);

create table course_info
(
id int primary key auto_increment,
name varchar(50) not null,
theme varchar(50) not null,
technologies varchar(255) not null,
teacher varchar(50) not null,
group_num int not null,
subject varchar(50) not null,
year int not null,
user_id int ,
foreign key(user_id) references User(id)
);
select * from user;

insert into course_info(name,theme,technologies,teacher,group_num,subject,year,user_id)
values('Database','Internet Store','MYsql,NodeJs,Angular','Blinova A.A',5,'Database',2021,1);
insert into course_info(name,theme,technologies,teacher,group_num,subject,year,user_id)
values('PL','Translator','C++','Narkevich A.S',5,'PL',2019,1);


update course_info set name='aaa',theme='ffff' where id=5;

SET SQL_SAFE_UPDATES = 0;
update course_info set name='Programming Languages' where year=2019;



SET SQL_SAFE_UPDATES = 0;



insert into role (role) values('ADMIN');
insert into role (role) values('USER');

insert into user(login,password,email,role_fk)values('Ne_kit',1234,'nikzakervashevich@mail.ru','ADMIN');
insert into user(login,password,email,role_fk)values('Vasya',5678,'pupkin@mail.ru','USER');



