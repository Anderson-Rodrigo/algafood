create table tab_pedido (
	id bigint not null auto_increment,
	ped_sub_total decimal(10,2) not null,
	ped_taxa_frete decimal(10,2) not null,
	ped_valor_total decimal(10,2) not null,
	data_criacao datetime not null,
	data_confirmacao datetime,
	data_cancelamento datetime,
	data_entrega datetime,
	ped_status_pedido varchar(10) not null,

	ped_forma_pagamento_id bigint not null,
	ped_restaurante_id bigint not null,
	ped_usuario_id bigint not null,

	endereco_cidade_id bigint,
	endereco_cep varchar(9) not null,
	endereco_logradouro varchar(100) not null,
	endereco_numero varchar(20) not null,
	endereco_complemento varchar(60),
	endereco_bairro varchar(60) not null,

	primary key(id),

	constraint fk_pedido_endereco_cidade foreign key(endereco_cidade_id) references tab_cidade (id),
	constraint fk_pedido_forma_pagamento foreign key(ped_forma_pagamento_id) references tab_forma_pagamento(id),
	constraint fk_pedido_restaurante foreign key(ped_restaurante_id) references tab_restaurante(id),
	constraint fk_pedido_usuario foreign key(ped_usuario_id) references tab_usuario(id)
);

create table tab_item_pedido (
	id bigint not null auto_increment,
	item_quantidade integer(10),
	item_preco_unitario decimal(10,2) not null,
	item_preco_total decimal(10,2) not null,
	item_observacao varchar(255),

	tab_produto_id bigint not null,
	tab_pedido_id bigint not null,

	primary key(id),

	constraint fk_item_produto foreign key(tab_produto_id) references tab_produto(id),
	constraint fk_item_pedido_pedido foreign key(tab_pedido_id) references tab_pedido(id)
);

