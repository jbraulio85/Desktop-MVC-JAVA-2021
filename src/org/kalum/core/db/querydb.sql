-- Create base de datos
create database kalum2021_dev;
-- seleccionar la base de datos
use kalum2021_dev;
-- creación de tablas
create table usuario_app
(
    id        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username  VARCHAR(64)     NOT NULL,
    password  VARCHAR(128)    NOT NULL,
    enabled   BIT DEFAULT 1,
    nombres   VARCHAR(128)    NOT NULL,
    apellidos VARCHAR(128)    NOT NULL,
    email     VARCHAR(64)
) ENGINE = innodb;

create table role_app
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(64) NOT NULL
) ENGINE = innodb;

create table usuario_rol
(
    usuario_id int not null,
    role_id int not null,
    primary key (usuario_id,role_id),
    CONSTRAINT FK_ROLE_USUARIO_APP foreign key (usuario_id) references usuario_app(id),
    constraint FK_ROLE_ROLE_APP foreign key (role_id) references role_app(id)
) ENGINE = innodb;

insert into usuario_app (username, password, nombres, apellidos, email)
VALUE ('bmontufar',md5('guatemala'),'jose','montufar','becheverria@gmail.com');

select * from usuario_app;
select * from role_app;
select * from usuario_rol;

insert into role_app (nombre) value ('ROLE_ADMIN');
insert into role_app (nombre) value ('ROLE_USER');
insert into role_app (nombre) value ('ROLE_SUPERVISOR');

insert into usuario_rol (usuario_id, role_id) values (1,1);
insert into usuario_rol (usuario_id, role_id) values (1,2);
insert into usuario_rol (usuario_id, role_id) values (2,2);
insert into usuario_rol (usuario_id, role_id) values (3,3);

create user 'test'@'%' identified by 'Inicio.2021';

grant all privileges on *.* to 'test'@'%';

use mysql;

select * from user;

delimiter $$
create procedure sp_Autenticar(in _username varchar(64), in _password varchar(128))
begin
select
	u.id,
    u.username,
    u.password,
    u.nombres,
    u.apellidos,
    u.email
from usuario_app u where u.username = _username and u.password = md5 (_password);
end $$

call sp_Autenticar('bmontufar','guatemala');