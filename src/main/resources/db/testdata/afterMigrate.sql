set foreign_key_checks = 0;

delete from tab_cidade;
delete from tab_cozinha;
delete from tab_estado;
delete from tab_forma_pagamento;
delete from tab_grupo;
delete from grupo_permissao;
delete from tab_permissao;
delete from tab_produto;
delete from tab_restaurante;
delete from restaurante_forma_pagamento;
delete from tab_usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table tab_cidade auto_increment = 1;
alter table tab_cozinha auto_increment = 1;
alter table tab_estado auto_increment = 1;
alter table tab_forma_pagamento auto_increment = 1;
alter table tab_grupo auto_increment = 1;
alter table tab_permissao auto_increment = 1;
alter table tab_produto auto_increment = 1;
alter table tab_restaurante auto_increment = 1;
alter table tab_usuario auto_increment = 1;

insert into tab_cozinha (id, nom_cozinha) values (1,'Tailandesa');
insert into tab_cozinha (id, nom_cozinha) values (2, 'Indiana');
insert into tab_cozinha (id, nom_cozinha) values (3, 'Chinesa');
insert into tab_cozinha (id, nom_cozinha) values (4, 'Coreana');

insert into tab_estado (id, est_nome) values (1, 'Minas gerais');
insert into tab_estado (id, est_nome) values (2, 'Sao paulo');
insert into tab_estado (id, est_nome) values (3, 'Ceara');

insert into tab_cidade (id, cid_nome, estado_id) values (1,'Maravilha', 1);
insert into tab_cidade (id, cid_nome, estado_id) values (2, 'Pinhalzinho', 1);
insert into tab_cidade (id, cid_nome, estado_id) values (3, 'Jacutinga', 2);
insert into tab_cidade (id, cid_nome, estado_id) values (4, 'Chapeco', 3);

insert into tab_restaurante (nom_restaurante, taxa_frete, data_cadastro, data_atualizacao, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values ('Outro Restaurante', 2.00, utc_timestamp, utc_timestamp, 1, 1, '89874-000', 'Rua C', '123', 'Casa', 'Universitario');
insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Do Joao', 2.00, 1, utc_timestamp, utc_timestamp);
insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Da maria', 3.00, 2, utc_timestamp, utc_timestamp);

insert into tab_forma_pagamento (pag_descricao) values ('Cartão de crédito'), ('Cartão de débito'), ('Dinheiro');

insert into tab_permissao (per_nome, per_descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tab_permissao (per_nome, per_descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1), (2, 2);

insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110.90, 1, 1);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina ', 87.20, 1, 2);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21.90, 1, 3);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43.90, 1, 3);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79.90, 1, 1);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89.90, 1, 1);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19.90, 1, 1);
insert into tab_produto (prod_nome, prod_descricao, prod_preco, prod_ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8.90, 1, 2);