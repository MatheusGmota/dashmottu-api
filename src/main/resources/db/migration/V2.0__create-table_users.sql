create sequence sq_tb_users start with 1 increment by 1;

CREATE TABLE TB_USERS(
    id_user NUMBER(19,0) not null,
    login VARCHAR2(30 char) NOT NULL UNIQUE,
    user_password VARCHAR2(30 char) NOT NULL,
    user_role VARCHAR2(10 char) NOT NULL,
    CONSTRAINT pk_tb_users primary key (id_user)
);