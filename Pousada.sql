{\rtf1\ansi\ansicpg1252\deff0\nouicompat{\fonttbl{\f0\fnil\fcharset0 Courier New;}}
{\*\generator Riched20 10.0.17134}\viewkind4\uc1 
\pard\f0\fs22\lang1046 create database if not exists Pousada;\par
\par
use Pousada;\par
\par
create table if not exists Cliente(\par
    Rg int (20) primary key not null,\par
    Nome varchar (100) not null,\par
    Email varchar (60)not null,\par
    Telefone varchar (10),\par
    Celular varchar (11)\par
);\par
\par
create table if not exists Quarto(\par
    Id int (11) primary key not null auto_increment,\par
    Categoria int (3) not null,\par
    Valor decimal (5,2) not null,\par
    Ramal int (4) not null,\par
    Ocupado boolean not null\par
);\par
\par
create table if not exists Colaborador(\par
    CNPJ varchar (18) primary key not null,\par
    NomeAgencia varchar (30) not null,\par
    NomeContato varchar (30) not null,\par
    Desconto int (5)\par
);\par
\par
create table if not exists Produto(\par
    Id int (11) primary key not null auto_increment,\par
    Nome varchar (30) not null,\par
    Valor decimal (3,2),\par
    EstoQtd int (5) not null\par
);\par
\par
create table if not exists Financeiro(\par
    Id int (11) primary key not null auto_increment,\par
    Id_Cliente int (11) not null,\par
    CNPJ_Colaborador varchar (18),\par
    Id_Reserva int (11) not null\par
);\par
\par
//currentdate\par
\par
create table if not exists Reserva(\par
    Id int (11) primary key not null auto_increment,\par
    Entrada datetime,\par
    Saida datetime,\par
    Id_Quarto int (11),\par
    Rg_Cliente int (20),\par
    foreign key (Id_Quarto) references Quarto (Id),\par
    foreign key (Rg_Cliente) references Cliente (Rg)\par
);\par
\par
create table if not exists AdcionaisReservados(\par
    Id int (11) primary key not null auto_increment,\par
    Id_Reserva int (11),\par
    Id_Adcionais int (11),\par
    foreign key (Id_Reserva) references Reserva (Id),\par
    foreign key (Id_Adcionais) references Adcionais (Id)\par
);\par
\par
create table if not exists Adcionais(\par
    Id int (11) primary key not null auto_increment,\par
    Nome varchar (50) not null,\par
    Valor int (11) not null,\par
    QtdMax int (11) not null\par
);\par
\par
create table if not exists Compra(\par
    Id_Financeiro (11),\par
    ConsuQtd (5),\par
    Id_Produto int (11),\par
    foreign key (Id_Financeiro) references Financeiro (Id),\par
    foreign key (Id_Produto) references Produto (Id)\par
);\par
\par
create table if not exists Endereco(\par
    CEP varchar (80) primary key not null,\par
    Rg_Cliente int (11) not null,\par
    CNPJ_Colaborador varchar (18) not null,\par
    Cidade varchar (50) not null,\par
    Rua varchar (30) not null,\par
    Bairro varchar (30) not null,\par
    numero int (5) not null\par
);\par
\par
create table if not exists Usuario(\par
    Codigo int (5) primary key not null auto_increment,\par
    Nome varchar(100) not null,\par
    Senha varchar(255) not null\par
);\par
\par
#Criando os users\par
create user "pousada"@"localhost" identified by "rr";\par
create user "pousada"@"%" identified by "rr";\par
grant all privileges on Pousada . * to "pousada"@"localhost";\par
grant all privileges on Pousada . * to "pousada"@"%";\par
flush privileges;\par
\par
}
 