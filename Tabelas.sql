use Transportadora;

select *From produto_carga;

Drop table transporta;

create table clientes (
 id int not null AUTO_INCREMENT ,
nome VARCHAR(255) not null, 
cnpj_cpf VARCHAR(40) not null,
endereco VARCHAR(255),
telefone VARCHAR(20) ,
 PRIMARY KEY(id),
UNIQUE(cnpj_cpf));

CREATE TABLE funcionarios(
id int not null auto_increment, 
nome VARCHAR(255),
cpf CHAR(11),
rg VARCHAR(10),
salario float,
carga_horaria FLOAT,
tipo VARCHAR(255),
PRIMARY KEY(id),
UNIQUE(cpf),
UNIQUE(rg)
);
 

CREATE TABLE motoristas(
id_motorista int not null auto_increment,
cnh VARCHAR(255), 
id_funcionario int not null,
UNIQUE(cnh),
PRIMARY KEY (id_motorista),
FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
);


CREATE TABLE veiculos(
id_veiculo int not null auto_increment,
id_motorista int not null,
marca VARCHAR(20), 
modelo VARCHAR(30),
placa VARCHAR(20),
ano VARCHAR(6),
situacao VARCHAR(255),
UNIQUE(placa),
PRIMARY KEY(id_veiculo),
FOreign key(id_motorista) references motoristas(id_motorista));

create table moto_conduz_veic(
id_coduz int not null auto_increment,
id_motorista int not null,
id_veiculo int not null,
primary key(id_coduz),
FOreign key(id_motorista) references motoristas(id_motorista),
FOreign key(id_veiculo) references veiculos(id_veiculo));

CREATE TABLE produtos( 
id int not null AUTO_INCREMENT, 
nome VARCHAR (255),
categoria VARCHAR(255),
preco FLOAT,
peso FLOAT,
quantidade INT, 
situacao VARCHAR(255)
,iscarga boolean not null,
id_cliente int not null ,
PRIMARY KEY(id),
FOREIGN KEY(id_cliente) references clientes(id));

CREATE TABLE cargas(
id_carga int not null AUTO_INCREMENT,
peso FLOAT,
situacao VARCHAR(255),
PRIMARY KEY (id_carga));

CREATE TABLE produto_carga
(id int not null AUTO_INCREMENT,
id_produto int not null,
id_carga int not null,
PRIMARY KEY(id),
foreign Key(id_produto) references produtos(id),
foreign Key(id_carga) references cargas(id_carga)
);

CREATE TABLE transporta(
id int not null auto_increment,
dt_saida DATETIME,
dt_entrega DATETIME,
cep_destino CHAR(8),
cidade_uf VARCHAR(255),
endereco VARCHAR(255),
id_veiculo int not null,
id_carga int not null,
Primary KEY (id),
FOREIGN KEY (id_veiculo) references veiculos(id_veiculo),
FOREIGN KEY (id_carga) references cargas(id_carga));
