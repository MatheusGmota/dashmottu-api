# API para DashMottu
Esse projeto é uma API RESTFul criada para a solução **Dashmottu: Mapeamento Inteligente do Pátio e Gestão das Motos**,
desenvolvida em Java junto ao Spring Boot.

## 👩‍👦‍👦 Equipe
- Felipe Seiki Hashiguti - RM98985
- Lucas Corradini Silveira - RM555118
- Matheus Gregorio Mota - RM557254

## ⚙ Configuração do Banco de Dados
No arquivo `application.properties` em `src/main/resources`, configure os dados do banco Oracle:
```
spring.datasource.url=${ORCL_URL}
spring.datasource.username=${ORCL_USERNAME}
spring.datasource.password=${ORCL_PASSWORD}
```

## ⚡ Executando Projeto
### Opção 1 - Executando pela classe Main:
> Execute a classe `DashmottuApplication`
>
> 
> ![image](https://github.com/user-attachments/assets/67003a72-ba1f-4a61-a8d0-a58863227e34)

### Opção 2 - Executando pelo terminal:
> Execute os seguintes comandos no terminal:
> ```bash
> cd dashmottu
> mvn clean package
> ```
> Em seguida execute:
> ```bash
> java -jar target/dashmottu-0.0.1-SNAPSHOT.jar
> ```

## 📡 Endpoints
### Patio
- `GET /patio` - Lista todos os pátios cadastrados.
- `GET /patio/{id}` - Obtém um pátio específico pelo seu ID.
- `POST /patio` - Cadastra um novo pátio. O corpo da requisição deve incluir `imagemPlantaUrl` e um objeto `endereco` com os campos: `cep`, `logradouro`, `numero`, `bairro`, `cidade` e `estado`.
- `POST /patio/{id}/motos?id={motoId}` - Associa uma moto existente a um pátio. O `id` do pátio é passado na URL e o `id` da moto é passado como parâmetro de consulta.
- `PUT /patio/{id}` - Atualiza os dados de um pátio existente. O corpo da requisição segue o mesmo formato do cadastro (`imagemPlantaUrl` e `endereco`).
- `DELETE /patio/{id}` - Remove um pátio do sistema. O corpo da requisição pode incluir os dados do pátio para confirmação.

### Moto
- `GET /moto` - Lista todas as motos cadastradas.
- `POST /moto` - Cadastra uma nova moto. O corpo da requisição deve incluir `codTag`, `modelo`, `placa` e `status`.
- `PUT /moto/{id}` - Atualiza os dados de uma moto existente. O corpo da requisição segue o mesmo formato do cadastro (`codTag`, `modelo`, `placa` e `status`).
- `PUT /moto?codTag={codTag}` - Atualiza a localização de uma moto. O `codTag` da moto é passado como parâmetro de consulta, e o corpo da requisição deve incluir `posicaoX` e `posicaoY`.
- `DELETE /moto/{id}` - Remove uma moto do sistema.

## Testando endpoints

## 🔧 Pré-requisitos

1. **Postman** instalado ([download aqui](https://www.postman.com/downloads/ "‌"))
2. URL base da API configurada como variável `{{dashmottu}}` no Postman
    - Exemplo: `http://localhost:8080` para ambiente local
3. Serviço da API em execução

## 🏗️ Estrutura da Collection

A collection está dividida em duas seções principais:

1. **Patio** - Operações relacionadas a pátios
2. **Moto** - Operações relacionadas a motos

## 🌐 Configuração de Ambiente

1. [Importe a collection](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-and-exporting-overview/) no Postman
2. Configure a variável de ambiente:
    - Clique em **Environments** > **Globals**
    - Adicione uma variável chamada `dashmottu` com o valor `http//localhost:8080/api`

## 🔄 Fluxo Recomendado para Testes

1. Crie um pátio (POST /patio)
2. Crie uma moto (POST /moto)
3. Associe a moto ao pátio (POST /patio/{id}/motos)
4. Atualize a localização da moto (PUT /moto?codTag={codTag})
5. Liste os pátios e motos para verificar os dados
6. Execute outras operações conforme necessário

## 👨‍💻 Tecnologias Utilizadas
- Maven
- Java
- Spring Boot
- Spring Data JPA
- Banco de dados Oracle

