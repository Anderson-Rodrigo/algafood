create table tab_estado (
    id bigint not null auto_increment,
    est_nome varchar(80) not null,

    primary key(id)
);

update tab_cidade set tab_cidade.estado_id = (
    select tab_estado.id from tab_estado where tab_estado.est_nome = tab_cidade.cid_nome
);

alter table tab_cidade add constraint fk_cid_est foreign key (estado_id) references tab_estado(id);