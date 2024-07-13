create TABLE topicos(
    id bigint not null auto_increment,
    titulo varchar(120) not null unique,
    mensagem varchar(255) not null unique,
    data datetime not null,
    status varchar (100) not null,
    curso_id bigint not null,
    usuario_id bigint not null,

    primary key(id),

    constraint fk_topicos_cursos_id foreign key(curso_id) references cursos(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id)
);