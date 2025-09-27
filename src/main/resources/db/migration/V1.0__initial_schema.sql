create sequence sq_tb_endereco start with 1 increment by 1;
create sequence sq_tb_localizacao start with 1 increment by 1;
create sequence sq_tb_moto start with 1 increment by 1;
create sequence sq_tb_patio start with 1 increment by 1;

create table tb_endereco (estado varchar2(2 char) not null, numero number(10,0) not null, id_endereco number(19,0) not null, cep varchar2(9 char) not null, bairro varchar2(50 char) not null, cidade varchar2(50 char) not null, logradouro varchar2(100 char) not null, primary key (id_endereco));
create table tb_localizacao (posicao_x float(20), posicao_y float(20), id_loc number(19,0) not null, ultima_modificacao timestamp(6), primary key (id_loc));
create table tb_moto (id_loc number(19,0) unique, id_moto number(19,0) not null, id_patio number(19,0), placa varchar2(8 char) not null unique, cod_tag varchar2(10 char) not null unique, modelo varchar2(255 char) not null check (modelo in ('MOTO_POP','MOTO_SPORT','MOTO_E')), status varchar2(255 char) not null check (status in ('PENDENCIA','REPARO_SIMPLES','DANOS_GRAVES','MOTOR_DEFEITUOSO','SEM_PLACA','AGENDADA_MANUTENCAO','PRONTA_PARA_USO','OPERANDO')), primary key (id_moto));
create table tb_patio (id_endereco number(19,0) unique,id_patio number(19,0) not null,imagem_planta_url varchar2(255 char),primary key (id_patio));

alter table tb_moto add constraint FKoi4jxkog0fcj951jmewufn7m2 foreign key (id_loc) references tb_localizacao;
alter table tb_moto add constraint FKpf7c91427jeaikhbkv7xi3dij foreign key (id_patio) references tb_patio;
alter table tb_patio add constraint FKe7rhfsf9yqa8u1hhyqwia2y6c foreign key (id_endereco) references tb_endereco;
