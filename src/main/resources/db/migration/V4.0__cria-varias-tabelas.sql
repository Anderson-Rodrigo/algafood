create table tab_forma_pagamento (
	id bigint not null auto_increment,
	pag_descricao varchar(60) not null,
	primary key (id)
);

create table tab_grupo (
	id bigint not null auto_increment,
	grp_nome varchar(60) not null,

	primary key (id)
);

create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null,

	primary key (grupo_id, permissao_id)
);

create table tab_permissao (
	id bigint not null auto_increment,
	per_descricao varchar(60) not null,
	per_nome varchar(100) not null,

	primary key (id)
);

create table tab_produto (
	id bigint not null auto_increment,
	restaurante_id bigint not null,
	prod_nome varchar(80) not null,
	prod_descricao text not null,
	prod_preco decimal(10,2) not null,
	prod_ativo tinyint(1) not null,

	primary key (id)
);

create table tab_restaurante (
	id bigint not null auto_increment,
	cozinha_id bigint not null,
	nom_restaurante varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	data_atualizacao datetime not null,
	data_cadastro datetime not null,

	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),

	primary key (id)
);

create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,

	primary key (restaurante_id, forma_pagamento_id)
);

create table tab_usuario (
	id bigint not null auto_increment,
	usuario_nome varchar(80) not null,
	usuario_email varchar(255) not null,
	usuario_senha varchar(255) not null,
	usuario_data_cadastro datetime not null,

	primary key (id)
);

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,

	primary key (usuario_id, grupo_id)
);

alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references tab_permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references tab_grupo (id);

alter table tab_produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references tab_restaurante (id);

alter table tab_restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references tab_cozinha (id);

alter table tab_restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references tab_cidade (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references tab_forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references tab_restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references tab_grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references tab_usuario (id);