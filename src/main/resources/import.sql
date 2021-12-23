insert into tab_cozinha (id, nom_cozinha) values (1,'Tailandesa')
insert into tab_cozinha (id, nom_cozinha) values (2, 'Indiana')

insert into estado (id, nome) values (1, 'Minas gerais')
insert into estado (id, nome) values (2, 'Sao paulo')
insert into estado (id, nome) values (3, 'Ceara')

insert into tab_cidade (id, cid_nome, estado_id) values (1,'Maravilha', 1)
insert into tab_cidade (id, cid_nome, estado_id) values (2, 'Pinhalzinho', 1)
insert into tab_cidade (id, cid_nome, estado_id) values (2, 'Jacutinga', 2)
insert into tab_cidade (id, cid_nome, estado_id) values (2, 'Chapeco', 3)

insert into tab_restaurante (nom_restaurante, taxa_frete,
            utc_timestamp,
            utc_timestamp,
            cozinha_id,
            endereco_cidade_id,
            endereco_cep,
            endereco_logradouro,
            endereco_numero,
            endereco_complemento,
            endereco_bairro) values ('Outro Restaurante', 2.00, 1, 1, '89874-000', 'Rua C', '123', 'Casa', 'Universitario')
insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id, utc_timestamp, utc_timestamp) values ('Do Joao', 2.00, 1)
insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id, utc_timestamp, utc_timestamp) values ('Da maria', 3.00, 2)

insert into tab_forma_pagamento (id, pag_descricao) values (1, "Cartão de crédito"), (2, "Cartão de débito"), (3, "Dinheiro")

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1), (2, 2)