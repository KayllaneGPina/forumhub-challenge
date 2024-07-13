create TABLE cursos (
    id bigint not null auto_increment,
    nome varchar (120) not null unique ,
    categoria varchar(100) not null,

    primary key(id)
);