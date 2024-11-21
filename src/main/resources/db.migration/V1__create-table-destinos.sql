CREATE TABLE IF NOT EXISTS Destinos (
     id bigint not null auto_increment,
     private String destinosViagens;
     nome VARCHAR(255) NOT NULL,
     localizacao VARCHAR(50) NOT NULL,
     informacoesSobreDestino VARCHAR(150) NOT NULL,
     estaDispinivel BOOLEAN NOT NULL,
     avaliacao DOUBLE NOT NULL,
     mediaAvaliacoes DOUBLE NOT NULL,
     pacotesViagens VARCHAR(50) NOT NULL,
     precoPacote DOUBLE NOT NULL,
     totalAvaliacoes INTEGER NOT NULL,

     primary key(id)
);