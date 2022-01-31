create table tab_cidade (
    id bigint not null auto_increment,
    cid_nome varchar(80) not null,
    estado_id bigint not null,

    primary key(id)
);

