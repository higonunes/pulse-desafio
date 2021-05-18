# Carrinho-checkout

## Gerenciamento do projeto
- Maven

## framework
- Java spring boot
  
## Banco de dados
- Spring JPA
- H2

## Segurança
- Spring security
- JWT

## Documentação
- Documentação feita com a especificação OpenApi (swagger)
- Ferramenta utilizada: https://stoplight.io/studio/

# Execução do projeto

Requisitos: Java 8;

- Git clone no projeto;
- Baixar as depedências do maven;
- Ajustar o perfil no arquivo de properties para spring.profiles.active = test;
- Excutar o projeto e ele iniciará com o banco de dados H2 alocado em memória temporária;
- Caso necessite persistir os dados, instalar algum banco de dados, configurar as credenciais e dialeto no arquivo application-dev.properties, incuir o drive do banco no arquivo POM.xml e mudar o spring.profiles.active = dev;
