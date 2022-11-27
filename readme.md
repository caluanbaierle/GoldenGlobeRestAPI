# Golden Globe Rest API

Especificação do Teste Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e vencedores da
categoria Pior Filme do Golden Raspberry Awards. Requisito do sistema:

1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a aplicação. Requisitos da API:
1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido,
   seguindo a especificação de formato definida na página 2; Requisitos não funcionais do sistema:
1. O web service RESTful deve ser implementado com base no nível 2 de maturidade de Richardson;
2. Devem ser implementados somente testes de integração. Eles devem garantir que os dados obtidos estão de acordo com os
   dados fornecidos na proposta;
3. O banco de dados deve estar em memória utilizando um SGBD embarcado (por exemplo, H2). Nenhuma instalação externa
   deve ser necessária;
4. A aplicação deve conter um readme com instruções para rodar o projeto e os testes de integração.

# Arquivos Necessarios

- Maven 3.6.3
- Java 11

# Build e Execução do Projeto

Para executar o projeto acesse o diretório (raiz) e execute:
> mvn clean install spring-boot:run

Para acessar o resultado executar um get em:
> localhost:8080/movie/product

# Testes

Para os testes Unitários acesse o diretório (raiz) e execute:
> mvn test