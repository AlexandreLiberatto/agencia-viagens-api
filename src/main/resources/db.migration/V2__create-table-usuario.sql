CREATE TABLE IF NOT EXISTS Usuario (
     id bigint not null auto_increment,
     login VARCHAR(150) NOT NULL,
     password VARCHAR(255) NOT NULL,

     primary key(id)
);