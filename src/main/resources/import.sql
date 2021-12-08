insert into tab_cozinha (id, nom_cozinha) values (1,'Tailandesa')
insert into tab_cozinha (id, nom_cozinha) values (2, 'Indiana')

insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id) values ('Do Joao', 2.00, 1)
insert into tab_restaurante (nom_restaurante, taxa_frete, cozinha_id) values ('Da maria', 3.00, 2)

insert into tab_forma_pagamento (id, pag_descricao) values (1, "Cartão de crédito"), (2, "Cartão de débito"), (3, "Dinheiro")

insert into tab_cidade (id, cid_nome) values (1,'Maravilha')
insert into tab_cidade (id, cid_nome) values (2, 'Pinhalzinho')

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1), (2, 2)