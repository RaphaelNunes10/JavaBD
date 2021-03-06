create database if not exists Pousada;

use Pousada;

create table if not exists Cliente(
    Rg int (20) primary key not null,
    Nome varchar (100) not null,
    Email varchar (60)not null,
    Telefone varchar (10),
    Celular varchar (11)
);

create table if not exists Quarto(
    Id int (11) primary key not null ,
    Categoria int (3) not null,
    Valor decimal (5,2) not null,
    Ramal int (4) not null,
    Ocupado boolean 
);

insert into Quarto (Id, categoria, valor, ramal) values (1, 1, 100, 01);
insert into Quarto (Id, categoria, valor, ramal) values (2, 2, 200, 02);
insert into Quarto (Id, categoria, valor, ramal) values (3, 3, 300, 03);


create table if not exists Colaborador(
    CNPJ varchar (18) primary key not null,
    NomeAgencia varchar (30) not null,
    NomeContato varchar (30) not null,
    Desconto int (5)
);



create table if not exists Produto(
    Id int (11) primary key not null auto_increment,
    Nome varchar (30) not null,
    Valor decimal (3,2),
    EstoQtd int (5) not null
);

create table if not exists Financeiro(
    Id int (11) primary key not null auto_increment,
    Id_Cliente int (11) not null,
    CNPJ_Colaborador varchar (18),
    Id_Reserva int (11) not null
);

create table if not exists Reserva(
    Id int (11) primary key not null auto_increment,
    Entrada datetime,
    Saida datetime,
    Id_Quarto int (11),
    Rg_Cliente int (20),
    foreign key (Id_Quarto) references Quarto (Id),
    foreign key (Rg_Cliente) references Cliente (Rg)
);

create table if not exists Adcionais(
    Id int (11) primary key not null auto_increment,
    Nome varchar (50) not null,
    Valor int (11) not null,
    QtdMax int (11) not null
);

create table if not exists Compra(
    Id_Financeiro int (11),
    ConsuQtd int (5),
    Id_Produto int (11),
    foreign key (Id_Financeiro) references Financeiro (Id),
    foreign key (Id_Produto) references Produto (Id)
);

create table if not exists Endereco(
    CEP varchar (80)  not null,
    Rg_Cliente int (11),
    CNPJ_Colaborador varchar (18),
    Cidade varchar (50),
    Rua varchar (30) not null,
    Bairro varchar (30) not null,
   
	numero int (5) not null,
	
	CONSTRAINT fk_EndeColab FOREIGN KEY (CNPJ_Colaborador ) REFERENCES Colaborador (CNPJ)

);

create table if not exists Usuario(
    Codigo int (5) primary key not null auto_increment,
    Nome varchar(100) not null,
    Senha varchar(255) not null
);

#Criando os users
create user "pousada"@"localhost" identified by "rr";
create user "pousada"@"%" identified by "rr";
grant all privileges on Pousada . * to "pousada"@"localhost";
grant all privileges on Pousada . * to "pousada"@"%";
flush privileges; 