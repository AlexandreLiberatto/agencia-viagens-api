# 🌟 API Agência de Viagens

Este é um projeto de API para gerenciar destinos de viagens, desenvolvido com **Java**, **Spring Boot**, e **PostgreSQL**. Ele permite operações como cadastro, listagem, atualização e exclusão de destinos, com controle de acesso baseado em roles (usuários com perfil `USER` ou `ADMIN`).

---

## 🚀 Como configurar e rodar o projeto localmente

### 🛠️ Pré-requisitos

1. Certifique-se de ter o **Java 17+**, **PostgreSQL**, e **Maven** instalados.
2. Clone este repositório:

   ```bash
   git clone https://github.com/AlexandreLiberatto/agencia-viagens-api.git
   ```

3. Navegue até a pasta do projeto:

   ```bash
   cd agencia-viagens-api
   ```

---

### ⚙️ Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL com o nome `agencia`.  
   Ou use a configuração automática no arquivo `application.yml`.

2. Atualize as credenciais do banco no arquivo `src/main/resources/application.yml`:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/agencia?createDatabaseIfNotExist=true
       username: postgres # Substitua pelo seu usuário do banco
       password: 123456 # Substitua pela sua senha
   ```

---

### ▶️ Rodando a aplicação

1. Compile e inicie o projeto com o Maven:

   ```bash
   mvn spring-boot:run
   ```

2. A aplicação estará disponível em:  
   **`http://localhost:8080`**

---

### 📝 **Cadastro de Usuário**  

Antes de realizar o login, é necessário cadastrar um usuário no sistema. Siga os passos abaixo para criar um usuário no banco de dados:  

1. **Acesse o Postman**   

2. **Configuração da Requisição**  
   - Método: `POST`  
   - URL: `http://localhost:8080/usuario`  
   - Clique em:  
     - `Body`
     - `raw`  

3. **Corpo da Requisição**  
   Envie o seguinte JSON no corpo da requisição, ajustando os valores conforme necessário:  
   ```json
   {
     "login": "seuUsuario",
     "password": "suaSenha",
     "role": "ROLE_USER"
   }
   ```
   Exemplo para um usuário com privilégios de administrador:
   ```json
   {
     "login": "seuUsuario",
     "password": "suaSenha",
     "role": "ROLE_ADMIN"
   }
   ```

4. **Resposta Esperada**  
   - Status: `201 Created`  
   - Corpo da Resposta:  
     ```json
     {
       "id": 1,
       "login": "SeuUsuario",
       "role": "ROLE_USER"
     }
     ```  


--- 


## 🔑 Configuração de autenticação

A API usa autenticação baseada em roles. Crie um token no endpoint `/login` usando suas credenciais. O corpo da requisição no Postman deve seguir o formato:

```json
{
  "login": "SeuUsuario",
  "password": "SuaSenha",
  "role": "ROLE_USER" // ou "ROLE_ADMIN"
}
```

Após obter o token, insira-o no campo **Authorization** no formato:

```
Bearer {seu-token}
```


---


## 📜 Endpoints da API

### 🌐 URL Base: **`http://localhost:8080`**

### Endpoints disponíveis

| Método | Endpoint                 | Descrição                                | Permissão       |
|--------|--------------------------|------------------------------------------|-----------------|
| POST   | `/destinos`              | Cadastrar novo destino                  | `ADMIN`         |
| GET    | `/destinos`              | Listar todos os destinos                | `USER`, `ADMIN` |
| GET    | `/destinos/{id}`         | Buscar destino por ID                   | `USER`, `ADMIN` |
| PUT    | `/destinos/{id}`         | Atualizar informações de um destino     | `ADMIN`         |
| DELETE | `/destinos/{id}`         | Excluir um destino                      | `ADMIN`         |
| GET    | `/destinos/pesquisar`    | Pesquisar destinos por nome/localização | `USER`, `ADMIN` |
| POST   | `/destinos/{id}/avaliar` | Avaliar um destino                      | `USER`, `ADMIN` |


---


## 📥 Testando com Postman

Aqui está um exemplo de como configurar as requisições:

1. **Cadastrar Destino**  
   Método: `POST`  
   URL: **`http://localhost:8080/destinos`**  
   Body (JSON):

   ```json
   {
      "destinosViagens": "Curitiba",
      "nome": "Ópera de Arame",
      "localizacao": "Paraná",
      "informacoesSobreDestino": "Espetacular teatro em estrutura metálica, rodeado por natureza.",
      "estaDispinivel": true,
      "avaliacao": 9.2,
      "mediaAvaliacoes": 9.1,
      "pacotesViagens": "Standard",
      "precoPacote": 3200.0,
      "totalAvaliacoes": 9
   }
   ```
   
   ---


2. **Listar Destinos**  
   Método: `GET`  
   URL: **`http://localhost:8080/destinos`**


   ---


4. **Buscar Destino por ID**  
   Método: `GET`  
   URL: **`http://localhost:8080/destinos/{id}`**


   ---


6. **Atualizar Destino**  
   Método: `PUT`  
   URL: **`http://localhost:8080/destinos/{id}`**  
   Body (JSON):

   ```json
   {
     "destinosViagens": "Rio de Janeiro",
     "nome": "Cristo Redentor",
     "localizacao": "Rio de Janeiro",
     "informacoesSobreDestino": "Uma das sete maravilhas do mundo moderno, com vista panorâmica da cidade.",
     "estaDispinivel": true,
     "avaliacao": 9.8,
     "mediaAvaliacoes": 9.6,
     "pacotesViagens": "Premium",
     "precoPacote": 4500.0,
     "totalAvaliacoes": 12
   }
   ```


   ---


7. **Excluir Destino**  
   Método: `DELETE`  
   URL: **`http://localhost:8080/destinos/{id}`**


   ---


9. **Pesquisar Destinos Por Nome**  
   Método: `GET`  
   URL: **`http://localhost:8080/destinos/pesquisar?nome=Praia`**


   ---


10. **Pesquisar Destinos Por Localização**  
   Método: `GET`  
   URL: **`http://localhost:8080/destinos/pesquisar?localizacao=Bahia`**


---


12. **Avaliar Destino**  
   Método: `POST`  
   URL: **`http://localhost:8080/destinos/{id}/avaliar?nota=8`**  
   Parâmetro Query: `nota=8.5`


---


## 📚 Tecnologias usadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Maven**
- **Postman**

---

**✨ Dúvidas ou problemas?**  
Sinta-se à vontade para abrir uma issue neste repositório.

--- 
